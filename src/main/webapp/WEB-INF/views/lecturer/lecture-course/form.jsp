<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<jstl:choose>	 
	<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
		<h2>
			<acme:message code="lecturer.lectureCourse.form.lecture.delete.info"/>
		</h2>
	</jstl:when>
	<jstl:when test="${_command == 'create'}">
		<h2>
			<acme:message code="lecturer.lectureCourse.form.lecture.info"/>
		</h2>

	</jstl:when>		
	


</jstl:choose>

<acme:form>
	<acme:input-select code="lecturer.lectureCourse.form.label.course" path="course" choices="${courses}"/>	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'delete')}">
			<acme:submit code="lecturer.lectureCourse.form.button.delete" action="/lecturer/lecture-course/delete?lectureId=${lectureId}"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="lecturer.lectureCourse.form.button.create" action="/lecturer/lecture-course/create?lectureId=${lectureId}"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>