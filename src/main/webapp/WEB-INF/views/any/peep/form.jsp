<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
 	<acme:input-moment code="any.peep.form.label.moment" path="moment" readonly="true"/>
	<acme:input-textbox code="any.peep.form.label.title" path="title" readonly="true"/>	
	<acme:input-textbox code="any.peep.form.label.nick" path="nick" readonly="true"/>
	<acme:input-textbox code="any.peep.form.label.message" path="message" readonly="true"/>
	<acme:input-email code="any.peep.form.label.email" path="email" readonly="true"/>
	<acme:input-url code="any.peep.form.label.link" path="link" readonly="true"/>

	<jstl:if test="${_command == 'create' }">
		<acme:submit code="any.peep.form.button.publish" action="/any/peep/create"/>
	</jstl:if>
</acme:form>