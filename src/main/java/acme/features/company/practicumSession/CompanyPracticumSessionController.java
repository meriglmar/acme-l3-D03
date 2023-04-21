
package acme.features.company.practicumSession;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.sessions.PracticumSession;
import acme.framework.controllers.AbstractController;
import acme.roles.Company;

@Controller
public class CompanyPracticumSessionController extends AbstractController<Company, PracticumSession> {

	//	@Autowired
	//	protected CompanyPracticumSessionCreateService	createService;
	//
	//	@Autowired
	//	protected CompanyPracticumSessionListService	listService;
	//
	//	@Autowired
	//	protected CompanyPracticumSessionShowService	showService;
	//
	//	@Autowired
	//	protected CompanyPracticumSessionUpdateService	updateService;
	//
	//	@Autowired
	//	protected CompanyPracticumSessionDeleteService	deleteService;
	//
	//
	//	@PostConstruct
	//	protected void initialise() {
	//		super.addBasicCommand("create", this.createService);
	//		super.addBasicCommand("list", this.listService);
	//		super.addBasicCommand("show", this.showService);
	//		super.addBasicCommand("update", this.updateService);
	//		super.addBasicCommand("delete", this.deleteService);
	//	}

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumSessionListService				listService;

	@Autowired
	protected CompanyPracticumSessionShowService				showService;

	@Autowired
	protected CompanyPracticumSessionCreateService				createService;

	@Autowired
	protected CompanyPracticumSessionCreateExceptionalService	createAddendumService;

	@Autowired
	protected CompanyPracticumSessionDeleteService				deleteService;

	@Autowired
	protected CompanyPracticumSessionUpdateService				updateService;

	@Autowired
	protected CompanyPracticumSessionPublishService				publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addCustomCommand("create-exceptional", "create", this.createAddendumService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("update", this.updateService);
		super.addCustomCommand("publish", "update", this.publishService);

	}
}
