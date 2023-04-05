<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="student.enrolment.form.label.code" path="code" width="10%"/>
	<acme:list-column code="student.enrolment.form.label.motivation" path="motivation" width="10%"/>
	<acme:list-column code="student.enrolment.form.label.goals" path="goals" width="20%"/>		
	<acme:list-column code="student.enrolment.form.label.finalised" path="finalised" width="5%"/>		
</acme:list>