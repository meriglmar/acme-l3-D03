
package acme.features.lecturer.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.course.TypeCourse;
import acme.entities.lectures.Lecture;
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
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Lecturer.class);

		super.getResponse().setAuthorised(status);
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
	}

	@Override
	public void perform(final Lecture object) {
		assert object != null;
		this.repo.save(object);
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;
		SelectChoices choices;
		Tuple tuple;
		choices = SelectChoices.from(TypeCourse.class, object.getLectureType());
		tuple = super.unbind(object, "title", "abstractLecture", "body", "estimatedLearningTimeInHours", "lectureType", "link", "published");
		tuple.put("choices", choices);
		tuple.put("publishedMode", object.isPublished());
		super.getResponse().setData(tuple);
	}
}
