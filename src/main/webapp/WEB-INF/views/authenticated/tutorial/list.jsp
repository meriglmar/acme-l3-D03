<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="authenticated.tutorial.list.label.code" path="code" width="20%"/>	
	<acme:list-column code="authenticated.tutorial.list.label.title" path="title" width="20%"/>	
	<acme:list-column code="authenticated.tutorial.list.label.abstract" path="abstractTutorial" width="20%"/>	
	<acme:list-column code="authenticated.tutorial.list.label.goals" path="goals" width="20%"/>
</acme:list>
