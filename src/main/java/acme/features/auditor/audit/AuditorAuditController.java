
package acme.features.auditor.audit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.audits.Audit;
import acme.framework.controllers.AbstractController;
import acme.roles.Auditor;

@Controller
public class AuditorAuditController extends AbstractController<Auditor, Audit> {

	@Autowired
	protected AuditorAuditCreateService		createService;

	//	@Autowired
	//	protected AuditorAuditDeleteService		deleteService;
	//

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


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
		//		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
		super.addCustomCommand("publish", "update", this.publishService);
	}

}
