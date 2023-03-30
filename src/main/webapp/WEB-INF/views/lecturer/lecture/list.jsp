<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="employer.application.list.label.reference" path="title" width="10%"/>
	<acme:list-column code="employer.application.list.label.status" path="abstractLecture" width="30%"/>
	<acme:list-column code="employer.application.list.label.title" path="body" width="30%"/>		
	<acme:list-column code="employer.application.list.label.title" path="estimatedLearningTimeInHours" width="10%"/>		
	<acme:list-column code="employer.application.list.label.title" path="link" width="10%"/>		
	<acme:list-column code="employer.application.list.label.title" path="lectureType" width="10%"/>		
</acme:list>