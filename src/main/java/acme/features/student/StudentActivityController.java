
package acme.features.student;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.activities.Activity;
import acme.framework.controllers.AbstractController;
import acme.roles.Student;

@Controller
public class StudentActivityController extends AbstractController<Student, Activity> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentActivityListService	listService;

	@Autowired
	protected StudentActivityCreateService	createService;

	//	@Autowired
	//	protected StudentActivityShowService	showService;
	//
	//	@Autowired
	//	protected StudentActivityUpdateService	updateService;
	//
	//	@Autowired
	//	protected StudentActivityDeleteService	deleteService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("create", this.createService);
		//		super.addBasicCommand("show", this.showService);
		//		super.addBasicCommand("update", this.updateService);
		//		super.addBasicCommand("delete", this.deleteService);
	}
}
