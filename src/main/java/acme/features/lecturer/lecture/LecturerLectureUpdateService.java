
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
		//		if (!super.getBuffer().getErrors().hasErrors("estimatedLearningTime"))
		//			super.state(object.getEstimatedLearningTime() >= 0.01, "estimatedLearningTime", "lecturer.lecture.form.error.estimatedLearningTIme");
		//		if (!super.getBuffer().getErrors().hasErrors("title"))
		//			super.state(this.auxiliarService.validateTextImput(object.getTitle()), "title", "lecturer.lecture.form.error.spam");
		//		if (!super.getBuffer().getErrors().hasErrors("summary"))
		//			super.state(this.auxiliarService.validateTextImput(object.getSummary()), "summary", "lecturer.lecture.form.error.spam");
		//		if (!super.getBuffer().getErrors().hasErrors("body"))
		//			super.state(this.auxiliarService.validateTextImput(object.getBody()), "body", "lecturer.lecture.form.error.spam");
		//		if (!super.getBuffer().getErrors().hasErrors("nature"))
		//			super.state(!object.getNature().equals(Nature.BALANCED), "nature", "lecturer.lecture.form.error.nature");
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
		tuple = super.unbind(object, "title", "summary", "estimatedLearningTime", "body", "nature", "furtherInformationLink", "draftMode");
		final SelectChoices choices;
		choices = SelectChoices.from(TypeCourse.class, object.getLectureType());
		tuple.put("type", choices.getSelected().getKey());
		tuple.put("types", choices);
		boolean assigned;
		final Collection<LectureCourse> objects = this.repo.findManyLectureCourseByLecture(object);
		if (objects.size() == 0)
			assigned = false;
		else
			assigned = true;
		tuple.put("assigned", assigned);
		super.getResponse().setData(tuple);
	}
}
