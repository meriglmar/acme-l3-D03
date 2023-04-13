
package acme.features.assistant.tutorial.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.sessions.Session;
import acme.entities.tutorials.Tutorial;
import acme.features.assistant.tutorial.AssistantTutorialRepository;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

@Service
public class AssistantSessionShowService extends AbstractService<Assistant, Session> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantTutorialRepository repository;

	// AbstractService<Assistant, Session> ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int id;
		Tutorial tutorial;
		Assistant assistant;

		id = super.getRequest().getData("id", int.class);
		tutorial = this.repository.findTutorialById(id);
		assistant = tutorial == null ? null : tutorial.getAssistant();
		status = super.getRequest().getPrincipal().hasRole(assistant) || tutorial != null && !tutorial.isDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		final Session object;
		int id;

		id = super.getRequest().getData("id", int.class);
		//		object = this.repository.findSessionById(id);

		super.getBuffer().setData(null);

	}

	@Override
	public void unbind(final Session object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "code", "title", "abstractSession", "goals", "estimatedTotalTime", "draftMode");

		super.getResponse().setData(tuple);
	}

}
