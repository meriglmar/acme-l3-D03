
package acme.features.student.enrolments;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.enrolments.Enrolment;
import acme.framework.components.accounts.Principal;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MessageHelper;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentEnrolmentFinaliseService extends AbstractService<Student, Enrolment> {
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
		student = this.repository.findStudentById(principal.getActiveRoleId());
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
		SimpleDateFormat formatter;
		String format;
		Integer cvv;
		String expireDateString;
		Date expireDate;

		expireDateString = super.getRequest().getData("expireDate", String.class).trim().concat(" 00:00");
		format = MessageHelper.getMessage("default.format.moment", null, "yyyy/MM/dd", super.getRequest().getLocale());
		formatter = new SimpleDateFormat(format);
		formatter.setLenient(false);

		expireDate = formatter.parse(expireDateString, new ParsePosition(0));
		cvv = super.getRequest().getData("cvv", Integer.class);

		super.state(object.getCardLowerNibble() != null && object.getCardLowerNibble().matches("^([0-9]{16})$"), "cardLowerNibble", "student.enrolment.form.error.invalid-card-number");
		super.state(!"".equals(object.getCardHolder()), "cardHolder", "student.enrolment.form.error.invalid-card-holder");
		super.state(cvv != null, "cvv", "student.enrolment.form.error.invalid-cvv");
		super.state(expireDate != null, "expireDate", "student.enrolment.form.error.invalid-expireDate-format");
		if (expireDate != null)
			super.state(!MomentHelper.isAfterOrEqual(MomentHelper.getCurrentMoment(), expireDate), "expireDate", "student.enrolment.form.error.invalid-expireDate-value");
		if (cvv != null)
			super.state(String.valueOf(cvv).length() == 3, "cvv", "student.enrolment.form.error.invalid-cvv");

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

		workTime = this.repository.findWorktimeByEnrolmentId(object.getId());
		workTime = workTime != null ? workTime : 0.0;

		Tuple tuple;

		tuple = super.unbind(object, "cardLowerNibble", "cardHolder", "draftMode");
		tuple.put("readonly", false);

		super.getResponse().setData(tuple);
	}
}
