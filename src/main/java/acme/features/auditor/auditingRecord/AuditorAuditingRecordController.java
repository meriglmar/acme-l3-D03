
package acme.features.auditor.auditingRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.auditingRecords.AuditingRecord;
import acme.framework.controllers.AbstractController;
import acme.roles.Auditor;

@Controller
public class AuditorAuditingRecordController extends AbstractController<Auditor, AuditingRecord> {

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
	}

}
