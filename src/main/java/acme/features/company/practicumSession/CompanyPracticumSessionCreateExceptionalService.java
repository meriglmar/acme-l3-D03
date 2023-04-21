
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
public class CompanyPracticumSessionCreateExceptionalService extends AbstractService<Company, PracticumSession> {

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
		object.setExceptional(true);

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

		//Confirmation validation
		boolean confirmation;

		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");

		//Unique addendum validation
		final Collection<Practicum> practica;
		Collection<PracticumSession> exceptionals;
		final SelectChoices choices;
		final int companyId = super.getRequest().getPrincipal().getActiveRoleId();

		practica = this.psRepository.findManyPublishedPracticaByCompanyId(companyId);
		choices = SelectChoices.from(practica, "code", object.getPracticum());

		final int selectedId = Integer.parseInt(choices.getSelected().getKey());
		exceptionals = this.psRepository.findExceptionalSessionsByPracticumId(selectedId);

		final boolean valid = exceptionals.size() == 0;
		super.state(valid, "practicum", "company.practicum-session.validation.practicum.error.ExceptionalAlreadyExists");
	}

	@Override
	public void perform(final PracticumSession object) {
		assert object != null;
		object.setDraftMode(false);
		this.psRepository.save(object);
	}

	@Override
	public void unbind(final PracticumSession object) {
		assert object != null;
		final Collection<Practicum> practica;
		final SelectChoices choices;
		final int companyId = super.getRequest().getPrincipal().getActiveRoleId();

		practica = this.psRepository.findManyPublishedPracticaByCompanyId(companyId);
		choices = SelectChoices.from(practica, "code", object.getPracticum());
		Tuple tuple;

		tuple = super.unbind(object, "title", "abstract$", "startPeriod", "finishPeriod", "draftMode", "exceptional", "optionalLink");
		tuple.put("practicum", choices.getSelected().getKey());
		tuple.put("practica", choices);
		tuple.put("confirmation", false);

		super.getResponse().setData(tuple);
	}
}
