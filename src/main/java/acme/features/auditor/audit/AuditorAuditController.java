
package acme.features.auditor.audit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.audits.Audit;
import acme.framework.controllers.AbstractController;
import acme.roles.Auditor;

@Controller
public class AuditorAuditController extends AbstractController<Auditor, Audit> {

<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
=======
	@Autowired
	protected AuditorAuditCreateService	createService;

	//	@Autowired
	//	protected AuditorAuditDeleteService		deleteService;
	//
>>>>>>> 1cc1cb7 Task 126: In progress
	@Autowired
<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
	protected AuditorAuditCreateService		createService;

	@Autowired
	protected AuditorAuditDeleteService		deleteService;

	@Autowired
	protected AuditorAuditListService		listService;

	@Autowired
	protected AuditorAuditPublishService	publishService;

	@Autowired
	protected AuditorAuditShowService		showService;

	@Autowired
	protected AuditorAuditUpdateService		updateService;
=======
	protected AuditorAuditListService	listService;
	//
	//	@Autowired
	//	protected AuditorAuditPublishService	publishService;
	//
	@Autowired
	protected AuditorAuditShowService	showService;
	//
	//	@Autowired
	//	protected AuditorAuditUpdateService		updateService;
>>>>>>> 1cc1cb7 Task 126: In progress


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("create", this.createService);
<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
		super.addBasicCommand("delete", this.deleteService);
=======
		//		super.addBasicCommand("delete", this.deleteService);
>>>>>>> 1cc1cb7 Task 126: In progress
		super.addBasicCommand("list", this.listService);
<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
		super.addCustomCommand("publish", "update", this.publishService);
=======
		//		super.addCustomCommand("publish", "update", this.publishService);
		super.addBasicCommand("show", this.showService);
		//		super.addBasicCommand("update", this.updateService);
>>>>>>> 1cc1cb7 Task 126: In progress
	}

}
