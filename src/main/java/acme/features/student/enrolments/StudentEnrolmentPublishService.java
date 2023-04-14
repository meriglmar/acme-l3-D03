
package acme.features.student.enrolments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.enrolments.Enrolment;
import acme.framework.components.accounts.Principal;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentEnrolmentPublishService extends AbstractService<Student, Enrolment> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentEnrolmentRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int id;
		Enrolment enrolment;
		final Principal principal;
		Student student;

		id = super.getRequest().getData("id", int.class);
		enrolment = this.repository.findEnrolmentById(id);
		principal = super.getRequest().getPrincipal();
		student = this.repository.findStudentByPrincipalId(principal.getActiveRoleId());
		status = student != null && enrolment.getStudent().equals(student) && enrolment.isDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id;
		Enrolment object;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findEnrolmentById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Enrolment object) {
		assert object != null;

		super.bind(object, "cardLowerNibble", "cardHolder");
	}

	@Override
	public void validate(final Enrolment object) {
		assert object != null;

		super.state(object.getCardLowerNibble() != null && object.getCardLowerNibble().matches("^([0-9]{16})$"), "cardLowerNibble", "student.enrolment.form.error.invalid-card-number");
		super.state(!"".equals(object.getCardHolder()), "cardHolder", "student.enrolment.form.error.invalid-card-holder");

		super.state(object.isDraftMode(), "draftMode", "student.enrolment.form.error.finalised");
	}

	@Override
	public void perform(final Enrolment object) {
		assert object != null;

		object.setDraftMode(false);
		object.setCardLowerNibble(object.getCardLowerNibble().substring(12, 16));

		this.repository.save(object);
	}

	@Override
	public void unbind(final Enrolment object) {
		assert object != null;
		Double workTime;

		workTime = this.repository.finWorkTimeByEnrolmentId(object.getId());
		workTime = workTime != null ? workTime : 0.0;

		Tuple tuple;

		tuple = super.unbind(object, "cardLowerNibble", "draftMode");
		tuple.put("readonly", false);

		super.getResponse().setData(tuple);
	}
}
