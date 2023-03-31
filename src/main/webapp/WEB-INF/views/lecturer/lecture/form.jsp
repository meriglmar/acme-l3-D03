<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="lecturer.lecture.form.label.title" path="title" />
	<acme:input-textbox code="lecturer.lecture.form.label.abstractLecture" path="abstractLecture" />
	<acme:input-textarea code="lecturer.lecture.form.label.body" path="body"  />
	<acme:input-double code="lecturer.lecture.form.label.estimatedLearningTimeInHours" path="estimatedLearningTimeInHours" />
	<acme:input-url code="lecturer.lecture.form.label.link" path="link"/>
	<acme:input-checkbox code="lecturer.lecture.form.label.published" path="published"/>

	<acme:input-select path="lectureType" code="lecturer.lecture.form.label.lectureType"  choices="${choices}"  />


<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="lecturer.lecture.form.label.update" action="/lecturer/lecture/update"/>
			<acme:submit code="lecturer.lecture.form.label.delete" action="/lecturer/lecture/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="lecturer.lecture.form.label.create" action="/lecturer/lecture/create"/>
		</jstl:when>		
	</jstl:choose>	
</acme:form>