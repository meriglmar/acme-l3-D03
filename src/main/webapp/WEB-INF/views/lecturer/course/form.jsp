<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="lecturer.course.form.label.code" path="code" />
	<acme:input-textbox code="lecturer.course.form.label.title" path="title" />
	<acme:input-textarea code="lecturer.course.form.label.abstractCourse" path="abstractCourse"  />
	<acme:input-money code="lecturer.course.form.label.retailPrice" path="retailPrice" />
	<acme:input-url code="lecturer.course.form.label.link" path="link"/>

<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="lecturer.course.form.label.update" action="/lecturer/course/update"/>
			<acme:submit code="lecturer.course.form.label.delete" action="/lecturer/course/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="lecturer.course.form.label.create" action="/lecturer/course/create"/>
		</jstl:when>		
	</jstl:choose>	
</acme:form>