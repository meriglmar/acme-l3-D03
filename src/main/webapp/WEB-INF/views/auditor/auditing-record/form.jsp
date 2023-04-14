<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="auditor.auditing-record.form.label.subject" path="subject"/>	
	<acme:input-textbox code="auditor.auditing-record.form.label.assessment" path="assessment"/>	
	<acme:input-moment code="auditor.auditing-record.form.label.startTime" path="startTime" />
	<acme:input-moment code="auditor.auditing-record.form.label.finishTime" path="finishTime" />
	<acme:input-select code="auditor.auditing-record.form.label.mark" path="mark" choices="${marks}"/>
	<acme:input-url code="auditor.auditing-record.form.label.moreInfo" path="moreInfo"/>
	<h1>${codigo}</h1>
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:submit code="auditor.auditing-record.form.button.update" action="/auditor/auditing-record/update"/>
			<acme:submit code="auditor.auditing-record.form.button.delete" action="/auditor/auditing-record/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create' && draftMode}">
			<acme:submit code="auditor.auditing-record.form.button.create" action="/auditor/auditing-record/create?masterId=${masterId}"/>
		</jstl:when>	
		<jstl:when test="${_command == 'create'&& !draftMode }">
			<acme:input-checkbox code="auditor.auditing-record.form.button.confirmation" path="confirmation"/>
			<acme:submit code="auditor.auditing-record.form.button.create-correctionRecord" action="/auditor/auditing-record/create?masterId=${masterId}"/>
		</jstl:when>	
=======
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

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-select code="auditor.audit.form.label.course" path="course" choices="${courses}"/>
	<acme:input-textbox code="auditor.audit.form.label.code" path="code"/>	
	<acme:input-textbox code="auditor.audit.form.label.conclusion" path="conclusion"/>	
	<acme:input-textbox code="auditor.audit.form.label.strongPoints" path="strongPoints"/>	
	<acme:input-textbox code="auditor.audit.form.label.weakPoints" path="weakPoints"/>	
	<jstl:if test="${_command == 'show'}">
			<acme:input-textbox code="auditor.audit.form.label.mark" path="mark" readonly="true"/>
	</jstl:if>
	
		
	<jstl:choose>	 
		<jstl:when test="${_command == 'show' && draftMode == false}">
			<acme:button code="auditor.audit.form.button.auditing-records" action="/auditor/auditing-record/list?masterId=${id}"/>			
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:button code="auditor.audit.form.button.auditing-records" action="/auditor/auditing-record/list?masterId=${id}"/>
			<acme:submit code="auditor.audit.form.button.update" action="/auditor/audit/update"/>
			<acme:submit code="auditor.audit.form.button.delete" action="/auditor/audit/delete"/>
			<acme:submit code="auditor.audit.form.button.publish" action="/auditor/audit/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="auditor.audit.form.button.create" action="/auditor/audit/create"/>
		</jstl:when>		
>>>>>>> 25e5797 Task 127: In progress
	</jstl:choose>
	
</acme:form>
