
package acme.features.lecturer.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCourseListService extends AbstractService<Lecturer, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseRepository repo;

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
		Collection<Course> objects;
		objects = this.repo.findManyCoursesByLecturerId(super.getRequest().getPrincipal().getActiveRoleId());
		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;

		Tuple tuple;

<<<<<<< HEAD
		tuple = super.unbind(object, "code", "title", "abstractCourse", "retailPrice", "link", "lecturer", "published");
		tuple.put("draftMode", object.isDraftMode());
=======
		tuple = super.unbind(object, "code", "title", "abstractCourse", "retailPrice");
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
		super.getResponse().setData(tuple);
	}

}
