
package acme.features.assistant.tutorial.session;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.sessions.Session;
import acme.framework.controllers.AbstractController;
import acme.roles.Assistant;

public class AssistantTutorialSessionController extends AbstractController<Assistant, Session> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantTutorialSessionListService	listService;

	@Autowired
	protected AssistantTutorialSessionCreateService	createService;

	@Autowired
	protected AssistantTutorialSessionShowService	showService;

	@Autowired
	protected AssistantSessionUpdateService			updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
	}

}
