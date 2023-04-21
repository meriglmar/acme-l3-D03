
package acme.features.company.practicumSession;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.practicums.Practicum;
import acme.entities.sessions.PracticumSession;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumSessionCreateService extends AbstractService<Company, PracticumSession> {

	//	// Internal state ---------------------------------------------------------
	//
	//	@Autowired
	//	protected CompanyPracticumPracticumSessionRepository psRepository;
	//
	//	//	@Autowired
	//	//	protected AuxiliarService					auxiliarService;
	//
	//	// AbstractService interface ----------------------------------------------
	//
	//
	//	@Override
	//	public void check() {
	//		boolean status;
	//
	//		status = super.getRequest().hasData("masterId", int.class);
	//
	//		super.getResponse().setChecked(status);
	//	}
	//
	//	@Override
	//	public void authorise() {
	//		boolean status;
	//		int practicumId;
	//		Practicum practicum;
	//
	//		practicumId = super.getRequest().getData("masterId", int.class);
	//		practicum = this.psRepository.findPracticumById(practicumId);
	//		status = practicum != null && super.getRequest().getPrincipal().hasRole(practicum.getCompany());
	//
	//		super.getResponse().setAuthorised(status);
	//	}
	//
	//	@Override
	//	public void load() {
	//		PracticumPracticumSession object;
	//		int practicumId;
	//		Practicum practicum;
	//
	//		practicumId = super.getRequest().getData("masterId", int.class);
	//		practicum = this.psRepository.findPracticumById(practicumId);
	//
	//		object = new PracticumPracticumSession();
	//		object.setPracticum(practicum);
	//
	//		super.getBuffer().setData(object);
	//	}
	//
	//	@Override
	//	public void bind(final PracticumPracticumSession object) {
	//		assert object != null;
	//
	//		super.bind(object, "title", "abstract$", "startPeriod", "finishPeriod", "optionalLink", "confirmation");
	//	}
	//
	//	@Override
	//	public void validate(final PracticumPracticumSession object) {
	//		assert object != null;
	//
	//		boolean confirmation;
	//
	//		confirmation = object.getPracticum().isDraftMode() ? true : super.getRequest().getData("confirmation", boolean.class);
	//		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	//		//
	//		//		if (!super.getBuffer().getErrors().hasErrors("title"))
	//		//			super.state(this.auxiliarService.validateTextImput(object.getTitle()), "title", "company.practicum-session.form.error.spam");
	//		//
	//		//		if (!super.getBuffer().getErrors().hasErrors("abstract$"))
	//		//			super.state(this.auxiliarService.validateTextImput(object.getAbstract$()), "abstract$", "company.practicum-session.form.error.spam");
	//		//
	//		//		if (!super.getBuffer().getErrors().hasErrors("startPeriod")) {
	//		//			Date minimumStartDate;
	//		//			minimumStartDate = MomentHelper.deltaFromCurrentMoment(7, ChronoUnit.DAYS);
	//		//			super.state(MomentHelper.isAfterOrEqual(object.getStartPeriod(), minimumStartDate), "startPeriod", "company.practicum-session.form.error.start-period");
	//		//		}
	//		//
	//		//		if (!super.getBuffer().getErrors().hasErrors("endPeriod")) {
	//		//			Date minimumEndDate;
	//		//			minimumEndDate = MomentHelper.deltaFromMoment(object.getStartPeriod(), 7, ChronoUnit.DAYS);
	//		//			super.state(MomentHelper.isAfterOrEqual(object.getEndPeriod(), minimumEndDate), "endPeriod", "company.practicum-session.form.error.end-period");
	//		//		}
	//		//
	//		//		if (!super.getBuffer().getErrors().hasErrors("furtherInformationLink"))
	//		//			super.state(this.auxiliarService.validateTextImput(object.getFurtherInformationLink()), "furtherInformationLink", "company.practicum-session.form.error.spam");
	//	}
	//
	//	@Override
	//	public void perform(final PracticumPracticumSession object) {
	//		assert object != null;
	//
	//		this.psRepository.save(object);
	//	}
	//
	//	@Override
	//	public void unbind(final PracticumPracticumSession object) {
	//		assert object != null;
	//
	//		Tuple tuple;
	//
	//		tuple = super.unbind(object, "title", "abstract$", "startPeriod", "finishPeriod", "optionalLink");
	//		tuple.put("masterId", super.getRequest().getData("masterId", int.class));
	//		tuple.put("draftMode", object.getPracticum().isDraftMode());
	//		tuple.put("confirmation", false);
	//
	//		super.getResponse().setData(tuple);
	//	}

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumSessionRepository psRepository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {

		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		PracticumSession object;

		object = new PracticumSession();
		object.setDraftMode(true);
		object.setExceptional(false);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final PracticumSession object) {
		assert object != null;

		int practicumId;
		Practicum practicum;

		practicumId = super.getRequest().getData("practicum", int.class);
		practicum = this.psRepository.findPracticumById(practicumId);

		super.bind(object, "title", "abstract$", "optionalLink", "startPeriod", "finishPeriod");

		object.setPracticum(practicum);

	}

	@Override
	public void validate(final PracticumSession object) {
		assert object != null;

		//Date Validations

		final Date startPeriod = super.getRequest().getData("startPeriod", Date.class);
		final Date finishPeriod = super.getRequest().getData("finishPeriod", Date.class);
		final Date availableStart = MomentHelper.deltaFromCurrentMoment(7, ChronoUnit.DAYS);
		final Date availableEnd = MomentHelper.deltaFromMoment(startPeriod, 7, ChronoUnit.DAYS);

		final boolean validStart = startPeriod.getTime() >= availableStart.getTime();
		super.state(validStart, "startPeriod", "company.practicum-session.validation.startPeriod.error.WeekAhead");

		final boolean validEnd = finishPeriod.getTime() >= availableEnd.getTime();
		super.state(validEnd, "finishPeriod", "company.practicum-session.validation.finishPeriod.error.WeekLong");

	}

	@Override
	public void perform(final PracticumSession object) {
		assert object != null;

		this.psRepository.save(object);
	}

	@Override
	public void unbind(final PracticumSession object) {
		assert object != null;
		final Collection<Practicum> practica;
		final SelectChoices choices;
		final int companyId = super.getRequest().getPrincipal().getActiveRoleId();

		practica = this.psRepository.findManyPrivatePracticaByCompanyId(companyId);
		choices = SelectChoices.from(practica, "code", object.getPracticum());
		Tuple tuple;

		tuple = super.unbind(object, "title", "abstract$", "startPeriod", "finishPeriod", "draftMode", "exceptional", "optionalLink");
		tuple.put("practicum", choices.getSelected().getKey());
		tuple.put("practica", choices);

		super.getResponse().setData(tuple);
	}

}
