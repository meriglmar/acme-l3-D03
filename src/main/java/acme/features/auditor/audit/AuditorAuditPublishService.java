
package acme.features.auditor.audit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.entities.course.Course;
import acme.framework.components.accounts.Principal;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditPublishService extends AbstractService<Auditor, Audit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuditorAuditRepository repo;

	// AbstractService<Employer, Job> -------------------------------------


	@Override
	public void check() {
		final boolean status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		final int id = super.getRequest().getData("id", int.class);
		final Audit object = this.repo.findAuditById(id);
		final Principal principal = super.getRequest().getPrincipal();
		final int userAccountId = principal.getAccountId();
		super.getResponse().setAuthorised(object.getAuditor().getUserAccount().getId() == userAccountId && object.isDraftMode());
	}

	@Override
	public void load() {
		Audit object;
		int id;
		id = super.getRequest().getData("id", int.class);
		object = this.repo.findAuditById(id);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Audit object) {
		assert object != null;
		super.bind(object, "code", "conclusion", "strongPoints", "weakPoints");
	}

	@Override
	public void validate(final Audit object) {
		assert object != null;
	}

	@Override
	public void perform(final Audit object) {
		object.setDraftMode(false);
		this.repo.save(object);
	}

	@Override
	public void unbind(final Audit object) {
		assert object != null;
		Tuple tuple;
		final Collection<Course> courses = this.repo.findPublishedCourses();
		final SelectChoices choices = SelectChoices.from(courses, "code", object.getCourse());
		tuple = super.unbind(object, "code", "conclusion", "strongPoints", "weakPoints", "draftMode");
		tuple.put("courses", choices);
		tuple.put("course", choices.getSelected().getKey());
		super.getResponse().setData(tuple);
	}
}
