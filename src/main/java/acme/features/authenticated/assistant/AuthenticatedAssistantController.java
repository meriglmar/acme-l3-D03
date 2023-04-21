
package acme.features.authenticated.assistant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.components.accounts.Authenticated;
import acme.framework.controllers.AbstractController;
import acme.roles.Assistant;

@Controller
public class AuthenticatedAssistantController extends AbstractController<Authenticated, Assistant> {

	// Internal state ---------------------------------------------------------

	@Autowired
<<<<<<< HEAD
	protected AuthenticatedAssistantCreateService	createService;

	@Autowired
	protected AuthenticatedAssistantUpdateService	updateService;
=======
	protected AuthenticatedAssistantCreateService createService;
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("create", this.createService);
<<<<<<< HEAD
		super.addBasicCommand("update", this.updateService);
=======
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
	}
}
