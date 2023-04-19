<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<acme:form>
	<acme:input-textbox code="authenticated.auditor.form.label.firm" path="firm"/>
	<acme:input-textarea code="authenticated.auditor.form.label.professionalID" path="professionalID"/>
	<acme:input-textarea code="authenticated.auditor.form.label.certifications" path="certifications"/>
	<acme:input-textbox code="authenticated.auditor.form.label.moreInfo" path="moreInfo"/>

	<acme:submit test="${_command == 'create'}" code="authenticated.auditor.form.button.create" action="/authenticated/auditor/create"/>
	<jstl:if test="${_command == 'update'}">
		<acme:submit code="authenticated.auditor.form.button.update" action="/authenticated/auditor/update"/>
	</jstl:if> 	
</acme:form>