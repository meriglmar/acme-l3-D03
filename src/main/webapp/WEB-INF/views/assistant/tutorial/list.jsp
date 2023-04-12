<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="assistant.tutorial.list.label.code" path="code" width="20%"/>	
	<acme:list-column code="assistant.tutorial.list.label.title" path="title" width="20%"/>	
	<acme:list-column code="assistant.tutorial.list.label.abstract" path="abstract" width="20%"/>	
	<acme:list-column code="assistant.tutorial.list.label.goals" path="goals" width="20%"/>	
	<acme:list-column code="assistant.tutorial.list.label.estimatedTotalTime" path="estimatedTotalTime" width="20%"/>	
</acme:list>

<acme:button code="assistant.tutorial.list.button.create" action="/assistant/tutorial/create"/>