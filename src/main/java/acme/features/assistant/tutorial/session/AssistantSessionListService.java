
package acme.features.assistant.tutorial.session;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.sessions.Session;
import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

@Service
public class AssistantSessionListService extends AbstractService<Assistant, Session> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantSessionRepository repository;

	// AbstractService<Assistant, Tutorial> ----------------------------------------------


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
		final Collection<Session> objects;
		final Tutorial tutorial;

		//		objects = this.repository.findSessionsByTutorial();

		super.getBuffer().setData(null);
	}

	@Override
	public void unbind(final Session object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "abstractSession", "initTimePeriod", "finishTimePeriod");

		super.getResponse().setData(tuple);
	}

}
