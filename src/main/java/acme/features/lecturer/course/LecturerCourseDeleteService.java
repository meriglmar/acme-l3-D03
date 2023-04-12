
package acme.features.lecturer.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.lectureCourses.LectureCourse;
import acme.framework.components.accounts.Principal;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCourseDeleteService extends AbstractService<Lecturer, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseRepository repo;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		Course object;
		int id;
		id = super.getRequest().getData("id", int.class);
		object = this.repo.findOneCourseById(id);
		final Principal principal = super.getRequest().getPrincipal();
		final int userAccountId = principal.getAccountId();
		super.getResponse().setAuthorised(object.getLecturer().getUserAccount().getId() == userAccountId && object.isDraftMode());
	}

	@Override
	public void load() {
		Course object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repo.findOneCourseById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Course object) {
		assert object != null;

		super.bind(object, "id", "code", "title", "abstractCourse", "retailPrice", "link");
	}

	@Override
	public void validate(final Course object) {
		assert object != null;

	}

	@Override
	public void perform(final Course object) {
		assert object != null;
		final Collection<LectureCourse> courseLectures = this.repo.findManyLectureCourseByCourse(object);
		for (final LectureCourse cl : courseLectures)
			this.repo.delete(cl);
		this.repo.delete(object);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;

		Tuple tuple;
		tuple = super.unbind(object, "code", "title", "abstractCourse", "retailPrice", "link");
		super.getResponse().setData(tuple);
	}

}
