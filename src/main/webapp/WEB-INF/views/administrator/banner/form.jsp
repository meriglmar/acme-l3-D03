<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>	
	<acme:input-moment code="administrator.banner.form.label.moment" path="moment" readonly="true"/>	
	<acme:input-textbox code="administrator.banner.form.label.imageLink" path="imageLink"/>	
	<acme:input-textbox code="administrator.banner.form.label.eslogan" path="eslogan" />
	<acme:input-textbox code="administrator.banner.form.label.docLink" path="docLink" />
	<acme:input-moment code="administrator.banner.form.label.startDatePeriod" path="startDatePeriod"/>
	<acme:input-moment code="administrator.banner.form.label.endDatePeriod" path="endDatePeriod"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="administrator.banner.form.label.update" action="/administrator/banner/update"/>
			<acme:submit code="administrator.banner.form.label.delete" action="/administrator/banner/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="administrator.banner.form.label.create" action="/administrator/banner/create"/>
		</jstl:when>		
	</jstl:choose>
	
</acme:form>