
package acme.features.auditor.auditingRecord;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditingRecords.AuditingRecord;
import acme.entities.auditingRecords.TypeMark;
import acme.entities.audits.Audit;
import acme.framework.components.accounts.Principal;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditingRecordCreateService extends AbstractService<Auditor, AuditingRecord> {

	@Autowired
	protected AuditorAuditingRecordRepository repo;


	@Override
	public void check() {
		boolean status;
		status = super.getRequest().hasData("masterId", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		Audit object;
		int masterId;
		masterId = super.getRequest().getData("masterId", int.class);
		object = this.repo.findAuditById(masterId);
		final Principal principal = super.getRequest().getPrincipal();
		final int userAccountId = principal.getAccountId();
		super.getResponse().setAuthorised(object.getAuditor().getUserAccount().getId() == userAccountId);
	}

	@Override
	public void load() {
		final AuditingRecord object = new AuditingRecord();
		final int masterId = super.getRequest().getData("masterId", int.class);
		final Audit audit = this.repo.findAuditById(masterId);
		final Boolean correction = !audit.isDraftMode();
		object.setAssessment("");
		object.setSubject("");
		object.setCorrectionRecord(correction);
		object.setAudit(audit);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final AuditingRecord object) {
		assert object != null;
		super.bind(object, "subject", "assessment", "startTime", "finishTime", "mark", "moreInfo", "confirmation");
	}

	@Override
	public void validate(final AuditingRecord object) {
		final boolean confirmation = object.getAudit().isDraftMode() ? true : super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("startTime") && !super.getBuffer().getErrors().hasErrors("startPeriod"))
			super.state(MomentHelper.isAfterOrEqual(object.getFinishTime(), object.getStartTime()), "startPeriod", "auditor.auditing-record.form.error.post-date");
		if (!super.getBuffer().getErrors().hasErrors("startTime") && !super.getBuffer().getErrors().hasErrors("startPeriod"))
			super.state(MomentHelper.isAfterOrEqual(object.getFinishTime(), MomentHelper.deltaFromMoment(object.getStartTime(), 1, ChronoUnit.HOURS)), "finishTime", "auditor.auditing-record.form.error.not-enough-time");
		//		if (!super.getBuffer().getErrors().hasErrors("subject"))
		//			super.state(this.auxiliarService.validateTextImput(object.getSubject()), "subject", "auditor.auditing-record.form.error.spam");
		//		if (!super.getBuffer().getErrors().hasErrors("assessment"))
		//			super.state(this.auxiliarService.validateTextImput(object.getSubject()), "assessment", "auditor.auditing-record.form.error.spam");

	}

	@Override
	public void perform(final AuditingRecord object) {
		assert object != null;
		this.repo.save(object);
	}

	@Override
	public void unbind(final AuditingRecord object) {
		assert object != null;
		final Tuple tuple = super.unbind(object, "subject", "assessment", "startTime", "finishTime", "mark", "moreInfo");
		final SelectChoices choices = SelectChoices.from(TypeMark.class, object.getMark());
		tuple.put("mark", choices.getSelected().getKey());
		tuple.put("marks", choices);
		tuple.put("masterId", super.getRequest().getData("masterId", int.class));
		tuple.put("draftMode", object.getAudit().isDraftMode());
		tuple.put("confirmation", false);
		super.getResponse().setData(tuple);
	}
}
