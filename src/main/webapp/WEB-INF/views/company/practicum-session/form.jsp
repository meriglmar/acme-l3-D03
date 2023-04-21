<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%-- <%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="company.practicum-session.form.label.title" path="title"/>
	<acme:input-textbox code="company.practicum-session.form.label.abstract$" path="abstract$"/>
	<acme:input-moment code="company.practicum-session.form.label.start-period" path="startPeriod"/>
	<acme:input-moment code="company.practicum-session.form.label.finish-period" path="finishPeriod"/>
	<acme:input-url code="company.practicum-session.form.label.optionalLink" path="optionalLink"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:submit code="company.practicum-session.form.button.update" action="/company/practicum-session/update"/>
			<acme:submit code="company.practicum-session.form.button.delete" action="/company/practicum-session/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create' && draftMode == true}">
			<acme:submit code="company.practicum-session.form.button.create" action="/company/practicum-session/create?masterId=${masterId}"/>
		</jstl:when>
		<jstl:when test="${_command == 'create' && draftMode == false}">
			<acme:input-checkbox code="company.practicum-session.form.label.confirmation" path="confirmation"/>
			<acme:submit code="company.practicum-session.form.button.create-exceptional" action="/company/practicum-session/create?masterId=${masterId}"/>
		</jstl:when>		
	</jstl:choose>		
</acme:form> --%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="company.practicum-session.form.label.title" path="title"/>
	<acme:input-textarea code="company.practicum-session.form.label.abstract$" path="abstract$"/>
	<acme:input-moment code="company.practicum-session.form.label.startPeriod" path="startPeriod"/>
	<acme:input-moment code="company.practicum-session.form.label.finishPeriod" path="finishPeriod"/>
	<acme:input-textbox code="company.practicum-session.form.label.optionalLink" path="optionalLink"/>
		
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:input-select code="company.practicum-session.form.label.practicum" path="practicum" choices="${practica}"/>
			<acme:submit code="company.practicum-session.form.button.update" action="/company/practicum-session/update"/>
			<acme:submit code="company.practicum-session.form.button.delete" action="/company/practicum-session/delete"/>
			<acme:submit code="company.practicum-session.form.button.publish" action="/company/practicum-session/publish"/>
			
		</jstl:when>
		
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == false}">
			<acme:input-textbox code="company.practicum-session.form.label.practicum" path="practicum.code"/>			
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:input-select code="company.practicum-session.form.label.practicum" path="practicum" choices="${practica}"/>
			<acme:submit code="company.practicum-session.form.button.create" action="/company/practicum-session/create"/>
		</jstl:when>
		<jstl:when test="${_command == 'create-exceptional'}">
			<acme:input-select code="company.practicum-session.form.label.practicum" path="practicum" choices="${practica}"/>
			<acme:input-checkbox code="company.practicum-session.form.label.confirmation" path="confirmation"/>
			<acme:submit code="company.practicum-session.form.button.create-exceptional" action="/company/practicum-session/create-exceptional"/>
		</jstl:when>		
	</jstl:choose>
	<jstl:if test="${exceptional == true}">
		<acme:message code="company.practicum-session.form.message.exceptional"/>
	</jstl:if>
</acme:form>