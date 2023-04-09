
package acme.features.lecturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.course.TypeCourse;
import acme.framework.components.datatypes.Money;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCourseCreateService extends AbstractService<Lecturer, Course> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseRepository	repository;
	protected LecturerLectureRepository	repositoryLec;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		final boolean status = true;

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Lecturer.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Course object;
		final Money money = new Money();
		money.setAmount(0.0);
		money.setCurrency("EUR");
		Lecturer lecturer;

		lecturer = this.repository.findLecturerById(super.getRequest().getPrincipal().getActiveRoleId());

		// Esto va en el show,update y todo eso porque no sabemos el id del curso todavía
		//Collection<Lecture> = this.repository.findManyLecturersByCourseId();

		object = new Course();
		object.setTitle("");
		object.setCode("");
		object.setLink("");
		object.setRetailPrice(money);
		object.setAbstractCourse("");
		object.setCourseType(TypeCourse.THEORY);
		object.setLecturer(lecturer);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Course object) {
		assert object != null;

		super.bind(object, "code", "title", "abstractCourse", "retailPrice", "link");
	}

	@Override
	public void validate(final Course object) {
		assert object != null;
	}

	@Override
	public void perform(final Course object) {
		assert object != null;
		this.repository.save(object);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;
		Tuple tuple;

		tuple = super.unbind(object, "code", "title", "abstractCourse", "retailPrice", "link");
		super.getResponse().setData(tuple);
	}
}
