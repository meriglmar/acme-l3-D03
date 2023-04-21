
package acme.features.assistant.tutorial;

<<<<<<< HEAD
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.tutorials.Tutorial;
import acme.framework.components.jsp.SelectChoices;
=======
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.tutorials.Tutorial;
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

<<<<<<< HEAD
@Service
=======
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
public class AssistantTutorialCreateService extends AbstractService<Assistant, Tutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantTutorialRepository repository;

	// AbstractService<Assistant, Tutorial> ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Assistant.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Tutorial object;
		Assistant assistant;

		assistant = this.repository.findAssistantById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Tutorial();
		object.setDraftMode(true);
		object.setAssistant(assistant);

		super.getBuffer().setData(object);

	}

	@Override
	public void bind(final Tutorial object) {
		assert object != null;

<<<<<<< HEAD
		int courseId;
		Course course;

		courseId = super.getRequest().getData("course", int.class);
		course = this.repository.findCourseById(courseId);

		super.bind(object, "code", "title", "abstractTutorial", "goals");
		object.setCourse(course);
=======
		super.bind(object, "code", "title", "abstractTutorial", "goals", "estimatedTotalTime");
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
	}

	@Override
	public void validate(final Tutorial object) {
		assert object != null;
	}

	@Override
	public void perform(final Tutorial object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Tutorial object) {
		assert object != null;

<<<<<<< HEAD
		Collection<Course> courses;
		SelectChoices choices;

		courses = this.repository.findAllCourses();
		choices = SelectChoices.from(courses, "code", object.getCourse());

		Tuple tuple;
		tuple = super.unbind(object, "code", "title", "abstractTutorial", "goals");
=======
		Tuple tuple;
		tuple = super.unbind(object, "reference", "title", "deadline", "salary", "score", "moreInfo", "description", "draftMode");
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}

}
