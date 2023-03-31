<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="lecturer.lecture.form.label.title" path="title" width="10%"/>
	<acme:list-column code="lecturer.lecture.form.label.abstractLecture" path="abstractLecture" width="10%"/>
	<acme:list-column code="lecturer.lecture.form.label.body" path="body" width="30%"/>		
	<acme:list-column code="lecturer.lecture.form.label.estimatedLearningTimeInHours" path="estimatedLearningTimeInHours" width="5%"/>		
	<acme:list-column code="lecturer.lecture.form.label.link" path="link" width="30%"/>		
	<acme:list-column code="lecturer.lecture.form.label.lectureType" path="lectureType" width="10%"/>
	<acme:list-column code="lecturer.lecture.form.label.published" path="published" width="5%"/>		
</acme:list>