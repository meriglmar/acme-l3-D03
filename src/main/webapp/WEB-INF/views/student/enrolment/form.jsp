<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="student.enrolment.form.label.code" path="code" />
	<acme:input-textbox code="student.enrolment.form.label.motivation" path="motivation" />
	<acme:input-textarea code="student.enrolment.form.label.goals" path="goals"  />
	
	<jstl:choose>
		<jstl:when test="${finalisedMode == false && acme:anyOf(_command, 'create|show|update|delete')}">
			<acme:input-checkbox code="student.enrolment.form.label.finalised" path="finalised"/>
			<acme:print value="No finalizado"/>
		</jstl:when>
		<jstl:when test="${finalisedMode == true && acme:anyOf(_command, 'show|update|delete')}">
			<acme:print value="Finalizado"/>
		</jstl:when>
	</jstl:choose>	


<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && finalisedMode == false}">
			<acme:submit code="student.enrolment.form.label.update" action="/student/enrolment/update"/>
			<acme:submit code="student.enrolment.form.label.delete" action="/student/enrolment/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="student.enrolment.form.label.create" action="/student/enrolment/create"/>
		</jstl:when>		
	</jstl:choose>	
</acme:form>