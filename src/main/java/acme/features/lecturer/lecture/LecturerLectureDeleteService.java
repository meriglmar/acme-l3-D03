
package acme.features.lecturer.lecture;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SystemConfigurationService;
import acme.entities.lectureCourses.LectureCourse;
import acme.entities.lectures.Lecture;
import acme.entities.lectures.TypeLecture;
import acme.framework.components.accounts.Principal;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLectureDeleteService extends AbstractService<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLectureRepository		repo;

	@Autowired
	protected SystemConfigurationService	scService;

	// AbstractService interface ----------------------------------------------


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
		object = this.repo.findLectureById(id);
		final Principal principal = super.getRequest().getPrincipal();
		final int userAccountId = principal.getAccountId();
		super.getResponse().setAuthorised(object.getLecturer().getUserAccount().getId() == userAccountId && object.isDraftMode());
	}

	@Override
	public void load() {
		Lecture object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repo.findLectureById(id);

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
		assert object != null;
		final Collection<LectureCourse> courseLectures = this.repo.findManyLectureCourseByLecture(object);
		for (final LectureCourse cl : courseLectures)
			this.repo.delete(cl);
		this.repo.delete(object);
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;
		Tuple tuple;

		final SelectChoices choices;
		tuple = super.unbind(object, "title", "abstractLecture", "body", "estimatedLearningTimeInHours", "lectureType", "link", "lecturer");
		final String lang = super.getRequest().getLocale().getLanguage();
		tuple.put("draftMode", this.scService.translateBoolean(object.isDraftMode(), lang));
		choices = SelectChoices.from(TypeLecture.class, object.getLectureType());
		tuple.put("type", choices.getSelected().getKey());
		tuple.put("types", choices);
		tuple.put("draftModeBoolean", object.isDraftMode());

		super.getResponse().setData(tuple);
	}

}
