
package acme.features.lecturer.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.lectures.Lecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLectureDeleteService extends AbstractService<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLectureRepository repo;

	// AbstractService interface ----------------------------------------------


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

		this.repo.delete(object);
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "abstractLecture", "body", "estimatedLearningTimeInHours", "lectureType", "link");

		super.getResponse().setData(tuple);
	}

}
