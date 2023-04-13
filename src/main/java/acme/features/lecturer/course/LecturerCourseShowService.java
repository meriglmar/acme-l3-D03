
package acme.features.lecturer.course;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.course.TypeCourse;
import acme.entities.lectures.Lecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCourseShowService extends AbstractService<Lecturer, Course> {

	// Internal state --------------------------------------------------

	@Autowired
	protected LecturerCourseRepository repository;

	// AbstractShowService<Inventor, Item> interface --------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int courseId;
		Course course;
		Lecturer lecturer;

		courseId = super.getRequest().getData("id", int.class);
		course = this.repository.findOneCourseById(courseId);
		lecturer = course == null ? null : course.getLecturer();
		status = super.getRequest().getPrincipal().hasRole(lecturer) || course != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Course course;
		int id;

		id = super.getRequest().getData("id", int.class);
		course = this.repository.findOneCourseById(id);

		super.getBuffer().setData(course);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "code", "title", "abstractCourse", "retailPrice", "link");
		tuple.put("publishedMode", object.isPublished());
		final List<Lecture> lectures = this.repository.findManyLecturesByCourseId(object.getId()).stream().collect(Collectors.toList());
		final TypeCourse tipo = object.courseType(lectures);
		tuple.put("type", tipo);
		super.getResponse().setData(tuple);
	}

}
