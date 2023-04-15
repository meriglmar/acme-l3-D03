<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="any.course.label.code" path="code" readonly="${readonly}"/>
	<acme:input-textbox code="any.course.label.title" path="title" readonly="${readonly}"/>
	<acme:input-textarea code="any.course.label.abstractCourse" path="abstractCourse" readonly="${readonly}"/>
	<acme:input-textbox code="any.course.label.draftMode" path="draftMode" readonly="${readonly}"/>	
	<acme:input-money code="any.course.label.retailPrice" path="retailPrice" readonly="${readonly}"/>
	<acme:input-url code="any.course.label.link" path="link" readonly="${readonly}"/>
</acme:form>
