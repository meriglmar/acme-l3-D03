
package acme.features.lecturer.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.lectures.Lecture;
import acme.entities.lectures.TypeLecture;
import acme.framework.components.accounts.Principal;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLectureUpdateService extends AbstractService<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLectureRepository repo;

	// AbstractService<Employer, Company> -------------------------------------


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

		super.bind(object, "title", "abstractLecture", "body", "estimatedLearningTimeInHours", "lectureType", "link", "published");
	}

	@Override
	public void validate(final Lecture object) {
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("estimatedLearningTimeInHours"))
			super.state(object.getEstimatedLearningTimeInHours() >= 0.01, "estimatedLearningTimeInHours", "lecturer.lecture.form.error.estimatedLearningTimeInHours");
	}

	@Override
	public void perform(final Lecture object) {
		assert object != null;
		this.repo.save(object);
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "title", "abstractLecture", "body", "estimatedLearningTimeInHours", "lectureType", "link", "draftMode");
		final SelectChoices choices;
		choices = SelectChoices.from(TypeLecture.class, object.getLectureType());
		tuple.put("type", choices.getSelected().getKey());
		tuple.put("types", choices);
<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
=======
		//		boolean assigned;
		//		final Collection<LectureCourse> objects = this.repo.findManyLectureCourseByLecture(object);
		//		if (objects.size() == 0)
		//			assigned = false;
		//		else
		//			assigned = true;
		//		tuple.put("assigned", assigned);
>>>>>>> 087f703 Task 095: In progress
		super.getResponse().setData(tuple);
	}
}
