<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="employer.application.form.label.reference" path="title" />
	<acme:input-textbox code="employer.application.form.label.moment" path="abstractLecture" />
	<acme:input-textarea code="employer.application.form.label.statement" path="body"  />
	<acme:input-double code="employer.application.form.label.skills" path="estimatedLearningTimeInHours" />
	<acme:input-url code="employer.application.form.label.qualifications" path="link"/>

	<acme:input-select path="lectureType" code="employer.application.form.label.new-status"  choices="${choices}"  />


<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:submit code="employer.duty.form.button.update" action="/employer/duty/update"/>
			<acme:submit code="employer.duty.form.button.delete" action="/employer/duty/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="lecturer.lecture.create" action="/lecturer/lecture/create"/>
		</jstl:when>		
	</jstl:choose>	
</acme:form>