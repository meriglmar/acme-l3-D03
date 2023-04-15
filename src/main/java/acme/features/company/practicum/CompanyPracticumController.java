
package acme.features.company.practicum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.practicums.Practicum;
import acme.framework.controllers.AbstractController;
import acme.roles.Company;

@Controller
public class CompanyPracticumController extends AbstractController<Company, Practicum> {

	// Internal state ---------------------------------------------------------

	@Autowired
<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
	protected CompanyPracticumListService		listService;
=======
	protected CompanyPracticumListService	listService;
>>>>>>> 0df8f5f Task 118: In progress

	@Autowired
<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
	protected CompanyPracticumCreateService		createService;
=======
	protected CompanyPracticumCreateService	createService;
	//
	//	@Autowired
	//	protected CompanyPracticumUpdateService		updateService;
	//
	//	@Autowired
	//	protected CompanyPracticumDeleteService		deleteService;
>>>>>>> 0df8f5f Task 118: In progress

	@Autowired
	protected CompanyPracticumShowService		showService;

	@Autowired
	protected CompanyPracticumUpdateService		updateService;

	@Autowired
	protected CompanyPracticumDeleteService		deleteService;

	@Autowired
	protected CompanyPracticumPublishService	publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);
		super.addCustomCommand("publish", "update", this.publishService);
=======
		//		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		//		super.addBasicCommand("update", this.updateService);
		//		super.addBasicCommand("delete", this.deleteService);
		//		super.addCustomCommand("publish", "update", this.publishService);
>>>>>>> 0df8f5f Task 118: In progress
	}

}
