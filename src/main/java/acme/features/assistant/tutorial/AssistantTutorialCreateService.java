
package acme.features.assistant.tutorial;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

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

		super.bind(object, "code", "title", "abstractTutorial", "goals", "estimatedTotalTime");
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

		Tuple tuple;
		tuple = super.unbind(object, "reference", "title", "deadline", "salary", "score", "moreInfo", "description", "draftMode");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}

}
