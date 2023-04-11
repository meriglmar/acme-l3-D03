<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>	
	<acme:input-select code="lecturer.lectureCourse.form.label.course" path="course" choices="${courses}"/>
	
	<jstl:choose>	 
		<jstl:when test="${_command == 'delete' && !curses.isEmpty()}">
			<acme:submit code="lecturer.lectureCourse.form.button.delete" action="/lecturer/lecture-course/delete?lectureId=${lectureId}"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="lecturer.lectureCourse.form.button.add" action="/lecturer/lecture-course/create?lectureId=${lectureId}"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>