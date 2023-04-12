
package acme.features.lecturer.course;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.Course;
import acme.entities.course.TypeCourse;
import acme.entities.lectures.Lecture;
import acme.framework.components.accounts.Principal;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCoursePublishService extends AbstractService<Lecturer, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseRepository repo;

	// AbstractService<Employer, Job> -------------------------------------


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
		super.bind(object, "code", "title", "abstractCourse", "retailPrice", "link");
	}

	@Override
	public void validate(final Course object) {
		assert object != null;
		final Collection<Lecture> lectures = this.repo.findManyLecturesByCourseId(object.getId());
		super.state(!lectures.isEmpty(), "nature", "lecturer.course.form.error.nolecture");
		if (!lectures.isEmpty()) {
			boolean handOnLectureInCourse;
			handOnLectureInCourse = lectures.stream().anyMatch(x -> x.getLectureType().equals(TypeCourse.HANDS_ON));
			super.state(handOnLectureInCourse, "nature", "lecturer.course.form.error.nohandson");
			boolean publishedLectures;
			publishedLectures = lectures.stream().allMatch(x -> x.isDraftMode() == false);
			super.state(publishedLectures, "nature", "lecturer.course.form.error.lecturenp");
		}
	}

	@Override
	public void perform(final Course object) {
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
