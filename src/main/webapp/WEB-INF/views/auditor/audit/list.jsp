<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="auditor.audit.form.label.code" path="code" width="20%"/>
	<acme:list-column code="auditor.audit.form.label.conclusion" path="conclusion" width="10%"/>	
</acme:list>

<acme:button code="auditor.audit.form.label.create" action="/auditor/audit/create"/>