
package acme.features.lecturer.lecture;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.lectures.Lecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerLectureListAllService extends AbstractService<Lecturer, Lecture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerLectureRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<Lecture> objects;
		final Lecturer lecturer = this.repository.findOneLecturerById(super.getRequest().getPrincipal().getActiveRoleId());
		objects = this.repository.findLecturesByLecturer(lecturer);
		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Lecture object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "abstractLecture", "estimatedLearningTimeInHours");
		//		boolean assigned;
		//		final Collection<LectureCourse> objects = this.repository.findManyLectureCourseByLecture(object);
		//		if (objects.size() == 0)
		//			assigned = false;
		//		else
		//			assigned = true;
		//		tuple.put("assigned", assigned);
		super.getResponse().setGlobal("showCreate", false);
		super.getResponse().setData(tuple);
	}
}
