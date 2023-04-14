
package acme.features.auditor.auditingRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.auditingRecords.AuditingRecord;
import acme.framework.controllers.AbstractController;
import acme.roles.Auditor;

@Controller
public class AuditorAuditingRecordController extends AbstractController<Auditor, AuditingRecord> {

<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
	@Autowired
	protected AuditorAuditingRecordCreateService	createService;

	@Autowired
	protected AuditorAuditingRecordDeleteService	deleteService;

	@Autowired
	protected AuditorAuditingRecordListService		listService;

	@Autowired
	protected AuditorAuditingRecordShowService		showService;

	@Autowired
	protected AuditorAuditingRecordUpdateService	updateService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
=======
	//	@Autowired
	//	protected AuditorAuditingRecordCreateService		createService;
	//
	//	@Autowired
	//	protected AuditorAuditingRecordDeleteService		deleteService;
	//
	@Autowired
	protected AuditorAuditingRecordListService listService;

	//
	//	@Autowired
	//	protected AuditorAuditingRecordPublishService	publishService;
	//
	//	@Autowired
	//	protected AuditorAuditingRecordShowService		showService;
	//
	//	@Autowired
	//	protected AuditorAuditingRecordUpdateService		updateService;


	@PostConstruct
	protected void initialise() {
		//		super.addBasicCommand("create", this.createService);
		//		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("list", this.listService);
		//		super.addBasicCommand("show", this.showService);
		//		super.addBasicCommand("update", this.updateService);
		//		super.addCustomCommand("publish", "update", this.publishService);
>>>>>>> 25e5797 Task 127: In progress
	}

}
