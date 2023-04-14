<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="auditor.auditing-record.list.label.subject" path="subject"  width="50%"/>
	<acme:list-column code="auditor.auditing-record.list.label.assessment" path="assessment"  width="50%"/>
</acme:list>
<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
<acme:button test = "${draftMode == true && createButton}" code="auditor.auditing-record.list.button.create" action="/auditor/auditing-record/create?masterId=${masterId}"/>
<acme:button test = "${draftMode == false && createButton}" code="auditor.auditing-record.list.button.create-correctionRecord" action="/auditor/auditing-record/create?masterId=${masterId}"/>
=======
<acme:button test = "${createButton && draftMode}" code="auditor.auditing-record.list.button.create" action="/auditor/auditing-record/create?masterId=${masterId}"/>
<acme:button test = "${createButton && !draftMode}" code="auditor.auditing-record.list.button.create-exceptional" action="/auditor/auditing-record/create?masterId=${masterId}"/>
>>>>>>> 25e5797 Task 127: In progress

