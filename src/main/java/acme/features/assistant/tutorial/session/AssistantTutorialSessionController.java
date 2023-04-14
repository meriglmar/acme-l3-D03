
package acme.features.assistant.tutorial.session;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.sessions.Session;
import acme.framework.controllers.AbstractController;
import acme.roles.Assistant;

public class AssistantSessionController extends AbstractController<Assistant, Session> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantSessionListService	listService;

	//	@Autowired
	//	protected AssistantSessionCreateService		createService;

	@Autowired
	protected AssistantSessionShowService	showService;

	@Autowired
	protected AssistantSessionUpdateService	updateService;

	//	@Autowired
	//	protected AssistantSessionPublishService	publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		//		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
	}

}
