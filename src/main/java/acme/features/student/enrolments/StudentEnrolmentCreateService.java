
package acme.features.student.enrolments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.course.Course;
import acme.entities.enrolments.Enrolment;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentEnrolmentCreateService extends AbstractService<Student, Enrolment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		final boolean status = true;

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Student.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Enrolment object;
		Course course;
		int courseId;
		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findCourseById(courseId);

		Student student;
		student = this.repository.findStudentById(super.getRequest().getPrincipal().getActiveRoleId());

		final List<Activity> activities = new ArrayList<Activity>();

		object = new Enrolment();
		object.setCode("");
		object.setMotivation("");
		object.setGoals("");
		object.setFinalised(false);
		object.setNibble(0);
		object.setHolder("");
		object.setCourse(course);
		object.setStudent(student);
		object.setActivities(activities);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Enrolment object) {
		assert object != null;
		int courseId;
		Course course;
		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findCourseById(courseId);
		super.bind(object, "code", "motivation", "goals", "finalised", "holder", "nibble");
		object.setCourse(course);
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
		courses = this.repository.findAllCourses();
		choices = SelectChoices.from(courses, "code", object.getCourse());
		//course = this.repository.findCourseById(42);
		//		int courseId;
		//		courseId = super.getRequest().getData("course", int.class);
		//		course = this.repository.findCourseById(courseId);
		Student student;
		student = this.repository.findStudentById(super.getRequest().getPrincipal().getActiveRoleId());
		Tuple tuple;
		tuple = super.unbind(object, "code", "motivation", "goals", "finalised", "holder", "nibble", "activities");
		tuple.put("finalisedMode", object.isFinalised());
		//tuple.put("course_id", course.getId());
		tuple.put("student_id", student.getId());

		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}
}
