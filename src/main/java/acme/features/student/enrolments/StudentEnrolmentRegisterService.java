
package acme.features.student.enrolments;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.enrolments.Enrolment;
import acme.framework.components.accounts.Principal;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentEnrolmentRegisterService extends AbstractService<Student, Enrolment> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentRepository repository;

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
		Enrolment object;
		Principal principal;

		object = new Enrolment();

		principal = super.getRequest().getPrincipal();
		final int principalId = principal.getActiveRoleId();

		object.setStudent(this.repository.findStudentByPrincipalId(principalId));
		object.setDraftMode(true);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Enrolment object) {
		assert object != null;

		int courseId;
		Course course;

		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findOneCourseById(courseId);

		object.setCourse(course);

		super.bind(object, "code", "motivation", "goals");
	}

	@Override
	public void validate(final Enrolment object) {
		assert object != null;
	}

	@Override
	public void perform(final Enrolment object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Enrolment object) {
		assert object != null;
		Collection<Course> courses;
		SelectChoices choices;

		Tuple tuple;
		courses = this.repository.findManyCourses();
		choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "code", "motivation", "goals", "draftMode");
		tuple.put("readonly", !object.isDraftMode());
		tuple.put("workTime", 0.0);
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}
}
