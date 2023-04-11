
package acme.features.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentActivityCreateService extends AbstractService<Student, Activity> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentActivityRepository		repository;
	protected StudentEnrolmentRepository	repositoryEnr;

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

	//	@Override
	//	public void load() {
	//		Activity object;
	//		final Student student;
	//
	//		enrolment = this.repository.findEnrolmentById(super.getRequest().getPrincipal().getActiveRoleId());
	//
	//		// Esto va en el show,update y todo eso porque no sabemos el id del curso todavía
	//		//Collection<Lecture> = this.repository.findManyLecturersByCourseId();
	//
	//		object = new Activity();
	//		object.setTitle("");
	//		object.setAbstractt("");
	//		object.setLink("");
	//		object.setActivityType(ActivityType.THEORY);
	//		object.setEnrolment(enrolment);
	//		super.getBuffer().setData(object);
	//	}
	//
	//	@Override
	//	public void bind(final Course object) {
	//		assert object != null;
	//
	//		super.bind(object, "code", "title", "abstractCourse", "retailPrice", "link");
	//	}
	//
	//	@Override
	//	public void validate(final Course object) {
	//		assert object != null;
	//	}
	//
	//	@Override
	//	public void perform(final Course object) {
	//		assert object != null;
	//		this.repository.save(object);
	//	}
	//
	//	@Override
	//	public void unbind(final Course object) {
	//		assert object != null;
	//		Tuple tuple;
	//
	//		tuple = super.unbind(object, "code", "title", "abstractCourse", "retailPrice", "link");
	//		super.getResponse().setData(tuple);
	//	}
}
