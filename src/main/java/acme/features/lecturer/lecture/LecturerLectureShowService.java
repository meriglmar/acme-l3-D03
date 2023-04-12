
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
public class LecturerLectureShowService extends AbstractService<Lecturer, Lecture> {

	// Internal state --------------------------------------------------

	@Autowired
	protected LecturerLectureRepository repository;

	// AbstractShowService<Inventor, Item> interface --------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Lecture lecture;
		int id;

		id = super.getRequest().getData("id", int.class);
		lecture = this.repository.findLectureById(id);

		super.getBuffer().setData(lecture);
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "title", "abstractLecture", "body", "estimatedLearningTimeInHours", "lectureType", "link", "draftMode");
		tuple.put("confirmation", false);
		final SelectChoices choices;
		choices = SelectChoices.from(TypeCourse.class, object.getLectureType());
		tuple.put("type", choices.getSelected().getKey());
		tuple.put("types", choices);
		super.getResponse().setData(tuple);
	}

}
