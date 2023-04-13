<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="lecturer.course.form.label.code" path="code" />
	<acme:input-textbox code="lecturer.course.form.label.title" path="title" />
	<acme:input-textarea code="lecturer.course.form.label.abstractCourse" path="abstractCourse"  />
	<acme:input-money code="lecturer.course.form.label.retailPrice" path="retailPrice" />
	<acme:input-url code="lecturer.course.form.label.link" path="link"/>
	<acme:input-textbox code = "lecturer.course.form.label.courseType" path="courseType" readonly="true"/>
		
	<acme:button code="lecturer.course.form.button.lectures" action="/lecturer/lecture/list?masterId=${id}"/>
		
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && publishedMode == false}">
			<acme:print value="No publicado por ahora"/>
			<acme:submit code="lecturer.course.form.label.update" action="/lecturer/course/update"/>
			<acme:submit code="lecturer.course.form.label.delete" action="/lecturer/course/delete"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'create|show|update|delete') && publishedMode == false}">
			<acme:input-checkbox code="lecturer.lecture.form.label.published" path="published"/>
			<acme:submit code="lecturer.course.form.label.create" action="/lecturer/course/create"/>
		</jstl:when>
		<jstl:when test="${publishedMode == true && acme:anyOf(_command, 'show|update|delete')}">
			<acme:print value="Publicado"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>