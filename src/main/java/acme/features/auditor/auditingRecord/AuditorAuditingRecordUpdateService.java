<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c

package acme.features.auditor.auditingRecord;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SystemConfigurationService;
import acme.entities.auditingRecords.AuditingRecord;
import acme.entities.auditingRecords.TypeMark;
import acme.entities.audits.Audit;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditingRecordUpdateService extends AbstractService<Auditor, AuditingRecord> {

	@Autowired
	protected AuditorAuditingRecordRepository	repo;

	@Autowired
	protected SystemConfigurationService		scService;


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
		if (!super.getBuffer().getErrors().hasErrors("finishTime") && !super.getBuffer().getErrors().hasErrors("startTime"))
			super.state(MomentHelper.isAfterOrEqual(object.getFinishTime(), object.getStartTime()), "startPeriod", "auditor.auditing-record.form.error.start-before-finish");
		if (!super.getBuffer().getErrors().hasErrors("finishTime") && !super.getBuffer().getErrors().hasErrors("startTime"))
			super.state(MomentHelper.isAfterOrEqual(object.getFinishTime(), MomentHelper.deltaFromMoment(object.getStartTime(), 1, ChronoUnit.HOURS)), "finishTime", "auditor.auditing-record.form.error.at-least-1-hour");
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
		final Tuple tuple = super.unbind(object, "subject", "assessment", "mark", "moreInfo");
		final String lang = super.getRequest().getLocale().getLanguage();
		tuple.put("startTime", this.scService.translateDate(object.getStartTime(), lang));
		tuple.put("finishTime", this.scService.translateDate(object.getFinishTime(), lang));
		final SelectChoices choices = SelectChoices.from(TypeMark.class, object.getMark());
		tuple.put("mark", choices.getSelected().getKey());
		tuple.put("marks", choices);
		tuple.put("masterId", masterId);
		tuple.put("draftMode", audit.isDraftMode());
		super.getResponse().setData(tuple);
	}
}
=======
//
//package acme.features.auditor.auditingRecord;
//
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import acme.entities.audits.Audit;
//import acme.entities.course.Course;
//import acme.framework.components.accounts.Principal;
//import acme.framework.components.jsp.SelectChoices;
//import acme.framework.components.models.Tuple;
//import acme.framework.services.AbstractService;
//import acme.roles.Auditor;
//
//@Service
//public class AuditorAuditingRecordUpdateService extends AbstractService<Auditor, Audit> {
//	// Internal state ---------------------------------------------------------
//
//	@Autowired
//	protected AuditorAuditingRecordRepository repo;
//
//
//	@Override
//	public void check() {
//		boolean status;
//
//		status = super.getRequest().hasData("id", int.class);
//
//		super.getResponse().setChecked(status);
//	}
//
//	@Override
//	public void authorise() {
//		Audit object;
//		int id;
//		id = super.getRequest().getData("id", int.class);
//		object = this.repo.findAuditById(id);
//		final Principal principal = super.getRequest().getPrincipal();
//		final int userAccountId = principal.getAccountId();
//		super.getResponse().setAuthorised(object.getAuditor().getUserAccount().getId() == userAccountId && object.isDraftMode());
//	}
//
//	@Override
//	public void load() {
//		Audit object;
//		int id;
//		id = super.getRequest().getData("id", int.class);
//		object = this.repo.findAuditById(id);
//		super.getBuffer().setData(object);
//	}
//
//	@Override
//	public void bind(final Audit object) {
//		assert object != null;
//		int courseId;
//		Course course;
//		courseId = super.getRequest().getData("course", int.class);
//		course = this.repo.findCourseById(courseId);
//		super.bind(object, "code", "conclusion", "strongPoints", "weakPoints");
//		object.setCourse(course);
//	}
//
//	@Override
//	public void validate(final Audit object) {
//		assert object != null;
//		if (!super.getBuffer().getErrors().hasErrors("code"))
//			super.state(this.repo.findAuditByCode(object.getCode()) == null || this.repo.findAuditByCode(object.getCode()).equals(object), "code", "auditor.audit.form.error.code");
//		//		if (!super.getBuffer().getErrors().hasErrors("conclusion"))
//		//			super.state(this.auxiliarService.validateTextImput(object.getConclusion()), "conclusion", "auditor.audit.form.error.spam");
//		//		if (!super.getBuffer().getErrors().hasErrors("strongPoints"))
//		//			super.state(this.auxiliarService.validateTextImput(object.getStrongPoints()), "strongPoints", "auditor.audit.form.error.spam");
//		//		if (!super.getBuffer().getErrors().hasErrors("weakPoints"))
//		//			super.state(this.auxiliarService.validateTextImput(object.getWeakPoints()), "weakPoints", "auditor.audit.form.error.spam");
//	}
//
//	@Override
//	public void perform(final Audit object) {
//		assert object != null;
//		this.repo.save(object);
//	}
//
//	@Override
//	public void unbind(final Audit object) {
//		assert object != null;
//		Tuple tuple;
//		final Collection<Course> courses = this.repo.findPublishedCourses();
//		final SelectChoices choices = SelectChoices.from(courses, "code", object.getCourse());
//		tuple = super.unbind(object, "code", "conclusion", "strongPoints", "weakPoints", "draftMode");
//		tuple.put("courses", choices);
//		tuple.put("course", choices.getSelected().getKey());
//		super.getResponse().setData(tuple);
//	}
//}
>>>>>>> 25e5797 Task 127: In progress
