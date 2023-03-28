<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.banner.form.label.moment" path="moment" width="20%"/>
	<acme:list-column code="administrator.banner.form.label.eslogan" path="eslogan" width="10%"/>
	<acme:list-column code="administrator.banner.form.label.docLink" path="docLink" width="70%"/>
	<acme:list-column code="administrator.banner.form.label.startPeriod" path="startPeriod" width="70%"/>
	<acme:list-column code="administrator.banner.form.label.finPeriod" path="finPeriod" width="70%"/>	
</acme:list>

<acme:button code="administrator.banner.form.label.create" action="/administrator/banner/create"/>