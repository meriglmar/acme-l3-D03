
package acme.features.authenticated.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tutorials.Tutorial;
import acme.framework.components.accounts.Authenticated;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AuthenticatedTutorialShowService extends AbstractService<Authenticated, Tutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedTutorialRepository repository;

	// AbstractService<Assistant, Tutorial> ----------------------------------------------


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

		id = super.getRequest().getData("id", int.class);
		tutorial = this.repository.findTutorialById(id);
		status = super.getRequest().getPrincipal().hasRole(Authenticated.class) || tutorial != null && !tutorial.isDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Tutorial object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findTutorialById(id);

		super.getBuffer().setData(object);

	}

	@Override
	public void unbind(final Tutorial object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "code", "title", "abstractTutorial", "goals", "draftMode", "estimatedTotalTime");

		tuple.put("course", object.getCourse().getCode());
		tuple.put("assistant", object.getAssistant().getUserAccount().getIdentity().getName());

		super.getResponse().setData(tuple);
	}

}
