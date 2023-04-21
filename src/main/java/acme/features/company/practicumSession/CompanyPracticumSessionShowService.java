
package acme.features.company.practicumSession;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.practicums.Practicum;
import acme.entities.sessions.PracticumSession;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumSessionShowService extends AbstractService<Company, PracticumSession> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumSessionRepository psRepository;

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
		int sessionId;
		final PracticumSession session;

		sessionId = super.getRequest().getData("id", int.class);
		session = this.psRepository.findPracticumSessionById(sessionId);
		status = session != null && super.getRequest().getPrincipal().getActiveRoleId() == session.getPracticum().getCompany().getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		PracticumSession object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.psRepository.findPracticumSessionById(id);

		super.getBuffer().setData(object);

	}

	@Override
	public void unbind(final PracticumSession object) {
		assert object != null;
		final Collection<Practicum> practica;
		final SelectChoices choices;
		final int companyId = super.getRequest().getPrincipal().getActiveRoleId();

		practica = this.psRepository.findManyPracticaByCompanyId(companyId);
		choices = SelectChoices.from(practica, "code", object.getPracticum());
		Tuple tuple;

		tuple = super.unbind(object, "title", "abstract$", "startPeriod", "finishPeriod", "draftMode", "exceptional");
		tuple.put("practicum", choices.getSelected().getKey());
		tuple.put("practica", choices);

		final int selectedId = Integer.parseInt(choices.getSelected().getKey());
		final Practicum selectedPracticum = this.psRepository.findPracticumById(selectedId);

		tuple.put("practicum.code", selectedPracticum.getCode());

		super.getResponse().setData(tuple);
	}
}
