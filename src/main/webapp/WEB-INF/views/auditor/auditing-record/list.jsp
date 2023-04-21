<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="auditor.auditing-record.list.label.subject" path="subject"  width="40%"/>
	<acme:list-column code="auditor.auditing-record.list.label.assessment" path="assessment"  width="40%"/>
	<acme:list-column code="auditor.auditing-record.list.label.exceptional" path="exceptional"  width="20%"/>
</acme:list>
<acme:button test = "${draftMode == true && createButton}" code="auditor.auditing-record.list.button.create" action="/auditor/auditing-record/create?masterId=${masterId}"/>
<acme:button test = "${draftMode == false && createButton}" code="auditor.auditing-record.list.button.create-correctionRecord" action="/auditor/auditing-record/create?masterId=${masterId}"/>

