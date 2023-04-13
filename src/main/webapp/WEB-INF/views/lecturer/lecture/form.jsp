<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="lecturer.lecture.form.label.title" path="title"/>	
	<acme:input-textbox code="lecturer.lecture.form.label.abstractLecture" path="abstractLecture"/>	
	<acme:input-double code="lecturer.lecture.form.label.estimatedLearningTimeInHours" path="estimatedLearningTimeInHours"/>	
	<acme:input-textbox code="lecturer.lecture.form.label.body" path="body"/>	
	<acme:input-select code="lecturer.lecture.form.label.lectureType" path="lectureType" choices="${types}"/>	
	<acme:input-textbox code="lecturer.lecture.form.label.link" path="link"/>
	<acme:input-textbox code="lecturer.lecture.form.label.draftMode" path="draftMode" readonly="true"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:submit code="lecturer.lecture.form.button.update" action="/lecturer/lecture/update"/>
			<acme:submit code="lecturer.lecture.form.button.delete" action="/lecturer/lecture/delete"/>
			<acme:submit code="lecturer.lecture.form.button.publish" action="/lecturer/lecture/publish"/>
			<acme:button code="lecturer.lecture.form.button.add" action="/lecturer/lecture-course/create?lectureId=${id}"/>
			<acme:button code="lecturer.lecture.form.button.deleteFromCourse" action="/lecturer/lecture-course/delete?lectureId=${id}"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == false}">
			<acme:button code="lecturer.lecture.form.button.add" action="/lecturer/lecture-course/create?lectureId=${id}"/>
			<acme:button code="lecturer.lecture.form.button.deleteFromCourse" action="/lecturer/lecture-course/delete?lectureId=${id}"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="lecturer.lecture.form.button.create" action="/lecturer/lecture/create"/>
		</jstl:when>		
	</jstl:choose>
	
</acme:form>