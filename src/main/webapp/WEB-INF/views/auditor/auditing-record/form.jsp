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
	</jstl:choose>
	
</acme:form>
