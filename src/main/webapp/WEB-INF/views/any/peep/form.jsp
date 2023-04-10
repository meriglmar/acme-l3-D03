<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="any.peep.form.label.moment" path="moment"/>
	<acme:input-textbox code="any.peep.form.label.title" path="title"/>	
	<acme:input-textbox code="any.peep.form.label.nick" path="nick"/>
	<acme:input-textbox code="any.peep.form.label.message" path="message"/>
	<acme:input-textbox code="any.peep.form.label.email" path="email"/>
	<acme:input-textbox code="any.peep.form.label.link" path="link"/>
</acme:form>
