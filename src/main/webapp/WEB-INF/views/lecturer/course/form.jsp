<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="lecturer.lecture.form.label.title" path="code" />
	<acme:input-textbox code="lecturer.lecture.form.label.abstractLecture" path="title" />
	<acme:input-textarea code="lecturer.lecture.form.label.body" path="abstractCourse"  />
	<acme:input-money code="lecturer.lecture.form.label.estimatedLearningTimeInHours" path="retailPrice" />
	<acme:input-url code="lecturer.lecture.form.label.link" path="link"/>

<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="lecturer.lecture.form.label.update" action="/lecturer/lecture/update"/>
			<acme:submit code="lecturer.lecture.form.label.delete" action="/lecturer/lecture/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="lecturer.lecture.form.label.create" action="/lecturer/course/create"/>
		</jstl:when>		
	</jstl:choose>	
</acme:form>