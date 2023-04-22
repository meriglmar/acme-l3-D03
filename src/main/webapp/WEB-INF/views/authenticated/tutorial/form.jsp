<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="authenticated.tutorial.form.label.code" placeholder="ABC123" path="code" readonly="true"/>	
	<acme:input-textbox code="authenticated.tutorial.form.label.title" path="title" readonly="true"/>	
	<acme:input-textarea code="authenticated.tutorial.form.label.abstract" path="abstractTutorial" readonly="true"/>
	<acme:input-textarea code="authenticated.tutorial.form.label.goals" path="goals" readonly="true"/>
	<acme:input-textbox code="authenticated.tutorial.form.label.course" path="course" readonly="true"/>	
	<acme:input-double code="authenticated.tutorial.form.label.estimatedTotalTime" path="estimatedTotalTime" readonly="true"/>
	<acme:input-textbox code="authenticated.tutorial.form.label.assistants" path="assistant" readonly="true"/>	
	
</acme:form>