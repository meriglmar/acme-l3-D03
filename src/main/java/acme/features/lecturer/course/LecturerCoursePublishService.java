
package acme.features.lecturer.course;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.courses.TypeCourse;
import acme.entities.lectures.Lecture;
import acme.entities.lectures.TypeLecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCoursePublishService extends AbstractService<Lecturer, Course> {

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
		boolean status;
		int courseId;
		Course course;
		Lecturer lecturer;

		courseId = super.getRequest().getData("id", int.class);
		course = this.repo.findOneCourseById(courseId);
		lecturer = course == null ? null : course.getLecturer();
		status = course != null && course.isDraftMode() && super.getRequest().getPrincipal().hasRole(lecturer);

		super.getResponse().setAuthorised(status);

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
		//No sé si tengo que añadir el courseType de Course
		super.bind(object, "code", "title", "abstractCourse", "retailPrice", "link");
	}

	@Override
	public void validate(final Course object) {
		assert object != null;

		final Collection<Lecture> lectures = this.repo.findManyLecturesByCourseId(object.getId());
		super.state(!lectures.isEmpty(), "type", "lecturer.course.form.error.nolecture");

		if (!lectures.isEmpty()) {

			boolean handOnLectureInCourse;
			handOnLectureInCourse = lectures.stream().anyMatch(x -> x.getLectureType().equals(TypeLecture.HANDS_ON));
			super.state(handOnLectureInCourse, "type", "lecturer.course.form.error.nohandson");

			boolean publishedLectures;
			publishedLectures = lectures.stream().allMatch(x -> !x.isDraftMode());
			super.state(publishedLectures, "type", "lecturer.course.form.error.lecturenp");
		}
	}

	@Override
	public void perform(final Course object) {
		assert object != null;

		object.setDraftMode(false);
		this.repo.save(object);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "code", "title", "abstractCourse", "retailPrice", "link", "draftMode");
		final List<Lecture> lectures = this.repo.findManyLecturesByCourseId(object.getId()).stream().collect(Collectors.toList());
		final TypeCourse type = object.courseType(lectures);
		tuple.put("type", type);
		super.getResponse().setData(tuple);
	}
}
