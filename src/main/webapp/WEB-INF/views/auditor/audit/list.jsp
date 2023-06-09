<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="auditor.audit.list.label.code" path="code" width="50%"/>
	<acme:list-column code="auditor.audit.list.label.conclusion" path="conclusion" width="50%"/>	
</acme:list>

<acme:button code="auditor.audit.list.label.create" action="/auditor/audit/create"/>