
package acme.features.auditor.auditingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditingRecords.AuditingRecord;
import acme.entities.auditingRecords.TypeMark;
import acme.entities.audits.Audit;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditingRecordUpdateService extends AbstractService<Auditor, AuditingRecord> {

	@Autowired
	protected AuditorAuditingRecordRepository repo;


	@Override
	public void check() {
		final boolean status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		final int id = super.getRequest().getData("id", int.class);
		final Audit object = this.repo.findAuditByAuditingRecordId(id);
		final int userAccountId = super.getRequest().getPrincipal().getAccountId();
		super.getResponse().setAuthorised(object.getAuditor().getUserAccount().getId() == userAccountId && object.isDraftMode());
	}

	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final AuditingRecord object = this.repo.findAuditingRecordById(id);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final AuditingRecord object) {
		assert object != null;
		super.bind(object, "subject", "assessment", "startTime", "finishTime", "mark", "moreInfo");
	}

	@Override
	public void validate(final AuditingRecord object) {
		assert object != null;
		//		if (!super.getBuffer().getErrors().hasErrors("endPeriod") && !super.getBuffer().getErrors().hasErrors("startPeriod"))
		//			super.state(MomentHelper.isAfterOrEqual(object.getEndPeriod(), object.getStartPeriod()), "startPeriod", "auditor.auditing-record.form.error.post-date");
		//		if (!super.getBuffer().getErrors().hasErrors("endPeriod") && !super.getBuffer().getErrors().hasErrors("startPeriod"))
		//			super.state(MomentHelper.isAfterOrEqual(object.getEndPeriod(), MomentHelper.deltaFromMoment(object.getStartPeriod(), 1, ChronoUnit.HOURS)), "endPeriod", "auditor.auditing-record.form.error.not-enough-time");
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
		final int masterId = super.getRequest().getData("masterId", int.class);
		final Audit audit = this.repo.findAuditById(masterId);
		final Tuple tuple = super.unbind(object, "subject", "assessment", "startTime", "finishTime", "mark", "moreInfo");
		final SelectChoices choices = SelectChoices.from(TypeMark.class, object.getMark());
		tuple.put("mark", choices.getSelected().getKey());
		tuple.put("marks", choices);
		tuple.put("masterId", masterId);
		tuple.put("draftMode", audit.isDraftMode());
		super.getResponse().setData(tuple);
	}
}
