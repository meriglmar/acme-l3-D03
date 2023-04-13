
package acme.features.auditor.audit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
import acme.entities.courses.Course;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditCreateService extends AbstractService<Auditor, Audit> {

	@Autowired
	protected AuditorAuditRepository repo;


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Audit object;
		object = new Audit();
		final Auditor auditor = this.repo.findAuditorById(super.getRequest().getPrincipal().getActiveRoleId());
		object.setAuditor(auditor);
		object.setDraftMode(true);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Audit object) {
		assert object != null;
		int courseId;
		Course course;
		courseId = super.getRequest().getData("course", int.class);
		course = this.repo.findCourseById(courseId);
		super.bind(object, "code", "conclusion", "strongPoints", "weakPoints");
		object.setCourse(course);
	}

	@Override
	public void validate(final Audit object) {
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("code"))
			super.state(this.repo.findAuditByCode(object.getCode()) == null, "code", "auditor.audit.form.error.existing-code");
	}

	@Override
	public void perform(final Audit object) {
		assert object != null;
		this.repo.save(object);
	}

	@Override
	public void unbind(final Audit object) {
		assert object != null;
		Tuple tuple;
		Collection<Course> courses;
		SelectChoices choices;
		courses = this.repo.findCourses();
=======
import acme.entities.course.Course;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditCreateService extends AbstractService<Auditor, Audit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuditorAuditRepository repo;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Audit object;
		object = new Audit();
		final Auditor auditor = this.repo.findAuditorById(super.getRequest().getPrincipal().getActiveRoleId());
		object.setAuditor(auditor);
		object.setDraftMode(true);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Audit object) {
		assert object != null;
		int courseId;
		Course course;
		courseId = super.getRequest().getData("course", int.class);
		course = this.repo.findCourseById(courseId);
		super.bind(object, "code", "conclusion", "strongPoints", "weakPoints");
		object.setCourse(course);
	}

	@Override
	public void validate(final Audit object) {
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("code"))
			super.state(this.repo.findAuditByCode(object.getCode()) == null, "code", "auditor.audit.form.error.code");
		//		if (!super.getBuffer().getErrors().hasErrors("conclusion"))
		//			super.state(this.auxiliarService.validateTextImput(object.getConclusion()), "conclusion", "auditor.audit.form.error.spam");
		//		if (!super.getBuffer().getErrors().hasErrors("strongPoints"))
		//			super.state(this.auxiliarService.validateTextImput(object.getStrongPoints()), "strongPoints", "auditor.audit.form.error.spam");
		//		if (!super.getBuffer().getErrors().hasErrors("weakPoints"))
		//			super.state(this.auxiliarService.validateTextImput(object.getWeakPoints()), "weakPoints", "auditor.audit.form.error.spam");

	}

	@Override
	public void perform(final Audit object) {
		assert object != null;
		this.repo.save(object);
	}

	@Override
	public void unbind(final Audit object) {
		assert object != null;
		Tuple tuple;
		Collection<Course> courses;
		SelectChoices choices;
		courses = this.repo.findCoursesNotAudited();
>>>>>>> 1cc1cb7 Task 126: In progress
		choices = SelectChoices.from(courses, "code", object.getCourse());
		tuple = super.unbind(object, "code", "conclusion", "strongPoints", "weakPoints", "draftMode");
		tuple.put("courses", choices);
		tuple.put("course", choices.getSelected().getKey());
		super.getResponse().setData(tuple);
	}

}
