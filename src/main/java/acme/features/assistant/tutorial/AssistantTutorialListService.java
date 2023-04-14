
package acme.features.assistant.tutorial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tutorials.Tutorial;
import acme.framework.components.accounts.Principal;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

@Service
public class AssistantTutorialListService extends AbstractService<Assistant, Tutorial> {

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
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<Tutorial> objects;
		Principal principal;

		principal = super.getRequest().getPrincipal();
<<<<<<< Upstream, based on 28be164a6bca11cc0bb4935dc38b74904cb02697
		objects = this.repository.findTutorialsByAssistantId(principal.getActiveRoleId());
=======
		if (principal.getRoles().contains(Assistant.class)) {
			objects = this.repository.findManyTutorialsByAssistantId(principal.getAccountId());
			super.getBuffer().setData(objects);
		}
>>>>>>> cda6136 Task 111: correctly merged

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Tutorial object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "code", "title");

		super.getResponse().setData(tuple);
	}

}
