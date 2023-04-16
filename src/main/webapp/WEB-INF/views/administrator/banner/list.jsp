<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.banner.form.label.moment" path="moment" width="20%"/>
	<acme:list-column code="administrator.banner.form.label.eslogan" path="eslogan" width="20%"/>
	<acme:list-column code="administrator.banner.form.label.startDatePeriod" path="startDatePeriod" width="30%"/>
	<acme:list-column code="administrator.banner.form.label.startDatePeriod" path="endDatePeriod" width="30%"/>	
</acme:list>

<acme:button code="administrator.banner.form.label.create" action="/administrator/banner/create"/>