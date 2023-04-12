
package acme.features.lecturer.lecture;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.TypeCourse;
import acme.entities.lectureCourses.LectureCourse;
import acme.entities.lectures.Lecture;
import acme.framework.components.accounts.Principal;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLecturePublishService extends AbstractService<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLectureRepository repository;

	// AbstractService<Employer, Job> -------------------------------------


	@Override
	public void check() {
		boolean status;
		status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		Lecture object;
		int id;
		id = super.getRequest().getData("id", int.class);
		object = this.repository.findLectureById(id);
		final Principal principal = super.getRequest().getPrincipal();
		final int userAccountId = principal.getAccountId();
		super.getResponse().setAuthorised(object.getLecturer().getUserAccount().getId() == userAccountId && object.isDraftMode());
	}

	@Override
	public void load() {
		Lecture object;
		int id;
		id = super.getRequest().getData("id", int.class);
		object = this.repository.findLectureById(id);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Lecture object) {
		assert object != null;
		super.bind(object, "title", "abstractLecture", "body", "estimatedLearningTimeInHours", "lectureType", "link");
	}

	@Override
	public void validate(final Lecture object) {
		assert object != null;
	}

	@Override
	public void perform(final Lecture object) {
		object.setDraftMode(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;
		final Tuple tuple;
		tuple = super.unbind(object, "title", "abstractLecture", "body", "estimatedLearningTimeInHours", "lectureType", "link", "draftMode");
		final SelectChoices choices;
		choices = SelectChoices.from(TypeCourse.class, object.getLectureType());
		tuple.put("nature", choices.getSelected().getKey());
		tuple.put("natures", choices);
		boolean assigned;
		final Collection<LectureCourse> objects = this.repository.findManyLectureCourseByLecture(object);
		if (objects.size() == 0)
			assigned = false;
		else
			assigned = true;
		tuple.put("assigned", assigned);
		super.getResponse().setData(tuple);
	}
}
