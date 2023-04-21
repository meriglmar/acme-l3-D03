
package acme.features.lecturer.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SystemConfigurationService;
import acme.entities.lectures.Lecture;
import acme.entities.lectures.TypeLecture;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLectureCreateService extends AbstractService<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLectureRepository		repository;

	@Autowired
	protected SystemConfigurationService	scService;

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
		Lecture object;
		object = new Lecture();
		object.setDraftMode(true);
		final Lecturer lecturer = this.repository.findOneLecturerById(super.getRequest().getPrincipal().getActiveRoleId());
		object.setLecturer(lecturer);
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
		this.repository.save(object);
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;
		final Tuple tuple;

		tuple = super.unbind(object, "title", "abstractLecture", "body", "estimatedLearningTimeInHours", "lectureType", "link", "lecturer");
		final String lang = super.getRequest().getLocale().getLanguage();
		tuple.put("draftMode", this.scService.translateBoolean(object.isDraftMode(), lang));
		final SelectChoices choices;
		choices = SelectChoices.from(TypeLecture.class, object.getLectureType());
		tuple.put("type", choices.getSelected().getKey());
		tuple.put("types", choices);
		tuple.put("draftModeBoolean", object.isDraftMode());

		super.getResponse().setData(tuple);
	}
}
