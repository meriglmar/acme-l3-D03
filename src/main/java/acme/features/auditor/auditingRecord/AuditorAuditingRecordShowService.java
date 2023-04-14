<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c

package acme.features.auditor.auditingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SystemConfigurationService;
import acme.entities.auditingRecords.AuditingRecord;
import acme.entities.auditingRecords.TypeMark;
import acme.entities.audits.Audit;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditingRecordShowService extends AbstractService<Auditor, AuditingRecord> {

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
		super.getResponse().setAuthorised(object.getAuditor().getUserAccount().getId() == userAccountId);
	}

	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final AuditingRecord object = this.repo.findAuditingRecordById(id);
		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final AuditingRecord object) {
		assert object != null;
		final int id = super.getRequest().getData("id", int.class);
		final Audit audit = this.repo.findAuditByAuditingRecordId(id);
		final Tuple tuple = super.unbind(object, "subject", "assessment", "mark", "moreInfo");
		final String lang = super.getRequest().getLocale().getLanguage();
		tuple.put("startTime", this.scService.translateDate(object.getStartTime(), lang));
		tuple.put("finishTime", this.scService.translateDate(object.getFinishTime(), lang));
		final SelectChoices choice = SelectChoices.from(TypeMark.class, object.getMark());
		tuple.put("masterId", audit.getId());
		tuple.put("draftMode", audit.isDraftMode());
		tuple.put("mark", choice.getSelected().getKey());
		tuple.put("marks", choice);
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
//import acme.entities.auditingRecords.TypeMark;
//import acme.entities.audits.Audit;
//import acme.entities.course.Course;
//import acme.framework.components.accounts.Principal;
//import acme.framework.components.jsp.SelectChoices;
//import acme.framework.components.models.Tuple;
//import acme.framework.services.AbstractService;
//import acme.roles.Auditor;
//
//@Service
//public class AuditorAuditingRecordShowService extends AbstractService<Auditor, Audit> {
//
//	@Autowired
//	protected AuditorAuditingRecordRepository repo;
//
//	// AbstractService interface ----------------------------------------------
//
//
//	@Override
//	public void check() {
//		boolean status;
//		status = super.getRequest().hasData("id", int.class);
//		super.getResponse().setChecked(status);
//	}
//
//	@Override
//	public void authorise() {
//		Audit object;
//		final int id = super.getRequest().getData("id", int.class);
//		object = this.repo.findAuditById(id);
//		final Principal principal = super.getRequest().getPrincipal();
//		final int userAccountId = principal.getAccountId();
//		super.getResponse().setAuthorised(object.getAuditor().getUserAccount().getId() == userAccountId);
//	}
//
//	@Override
//	public void load() {
//		Audit object;
//		final int id = super.getRequest().getData("id", int.class);
//		object = this.repo.findAuditById(id);
//
//		super.getBuffer().setData(object);
//	}
//
//	@Override
//	public void unbind(final Audit object) {
//		assert object != null;
//
//		Tuple tuple;
//		final Collection<Course> courses = this.repo.findPublishedCourses();
//		SelectChoices choices;
//		final Collection<TypeMark> marks = this.repo.findMarksByAuditId(object.getId());
//		String mark;
//		if (marks.isEmpty())
//			mark = "Vacía";
//		else
//			mark = marks.toString();
//		choices = SelectChoices.from(courses, "code", object.getCourse());
//		tuple = super.unbind(object, "code", "conclusion", "strongPoints", "weakPoints", "draftMode");
//		tuple.put("course", choices.getSelected().getKey());
//		tuple.put("courses", choices);
//		tuple.put("mark", mark);
//		super.getResponse().setData(tuple);
//
//	}
//}
>>>>>>> 25e5797 Task 127: In progress
