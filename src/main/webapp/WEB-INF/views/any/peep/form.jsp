<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<jstl:choose>
		<jstl:when test="${_command == 'show' }">
		 	<acme:input-textbox code="any.peep.form.label.moment" placeholder="yyyy/MM/dd HH:mm" path="moment"/>
		</jstl:when>
	</jstl:choose>
	<acme:input-textbox code="any.peep.form.label.title" path="title"/>	
	<acme:input-textbox code="any.peep.form.label.nick" path="nick"/>
	<acme:input-textbox code="any.peep.form.label.message" path="message"/>
	<acme:input-textbox code="any.peep.form.label.email" path="email"/>
	<acme:input-textbox code="any.peep.form.label.link" path="link"/>

	<jstl:choose>
		<jstl:when test="${_command == 'create' }">
			<acme:submit code="any.peep.form.button.publish" action="/any/peep/create"/>
		</jstl:when>
		<jstl:when test="${_command == 'show' }">
			<acme:submit code="any.peep.form.button.update" action="/any/peep/update"/>
		</jstl:when>
	</jstl:choose>
</acme:form>