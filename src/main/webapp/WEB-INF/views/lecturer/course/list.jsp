<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="courser.course.form.label.code" path="code" width="10%"/>
	<acme:list-column code="courser.course.form.label.title" path="title" width="10%"/>
	<acme:list-column code="courser.course.form.label.abstractCourse" path="abstractCourse" width="20%"/>		
	<acme:list-column code="courser.course.form.label.retailPrice" path="retailPrice" width="10%"/>		
	<acme:list-column code="courser.course.form.label.link" path="link" width="40%"/>		
	<acme:list-column code="courser.course.form.label.courseType" path="courseType" width="10%"/>
</acme:list>