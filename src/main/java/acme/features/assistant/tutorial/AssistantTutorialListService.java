
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
<<<<<<< Upstream, based on 1310c4f0e810dcb97b2c27b92463b787b7af16d8
<<<<<<< Upstream, based on 1310c4f0e810dcb97b2c27b92463b787b7af16d8
		objects = this.repository.findTutorialsByAssistantId(principal.getActiveRoleId());
=======
		objects = this.repository.findTutorialsByAssistant(principal.getActiveRoleId());
>>>>>>> 7268a1e Task-111: first part almost done
=======
		if (principal.getRoles().contains(Assistant.class)) {
			objects = this.repository.findManyTutorialsByAssistantId(principal.getAccountId());
			super.getBuffer().setData(objects);
		}
>>>>>>> b0b97c5 Task 111: updated

	}

	@Override
	public void unbind(final Tutorial object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "code", "title");

		super.getResponse().setData(tuple);
	}

}
