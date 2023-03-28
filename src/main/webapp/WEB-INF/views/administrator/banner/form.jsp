<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="administrator.banner.form.label.imageLink" path="imageLink"/>	
	<acme:input-textbox code="administrator.banner.form.label.eslogan" path="eslogan" />
	<acme:input-textbox code="administrator.banner.form.label.docLink" path="docLink"/>
	<acme:input-moment code="administrator.banner.form.label.startPeriod" path="startPeriod"/>
	<acme:input-moment code="administrator.banner.form.label.finPeriod" path="finPeriod"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="administrator.banner.form.label.confirmation" path="confirmation"/>
		<acme:submit code="administrator.banner.form.label.create" action="/administrator/banner/create"/>
	</jstl:if>
</acme:form>