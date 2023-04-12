<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="lecturer.lecture.form.label.title" path="title"  width="40%"/>
	<acme:list-column code="lecturer.lecture.form.label.abstractLecture" path="abstractLecture" width="40%" />
	<acme:list-column code="lecturer.lecture.form.label.estimatedLearningTimeInHours" path="estimatedLearningTimeInHours" width="20%" />
</acme:list>

<acme:button code="lecturer.lecture.form.button.create" action="/lecturer/lecture/create"/>