
package acme.features.company.practicumSession;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.practicums.Practicum;
import acme.entities.sessions.PracticumSession;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumSessionListService extends AbstractService<Company, PracticumSession> {

	//	// Internal state ---------------------------------------------------------
	//
	//	@Autowired
	//	protected CompanyPracticumSessionRepository psRepository;
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
	//		int masterId;
	//		Practicum practicum;
	//
	//		masterId = super.getRequest().getData("masterId", int.class);
	//		practicum = this.psRepository.findPracticumById(masterId);
	//		status = practicum != null && super.getRequest().getPrincipal().hasRole(practicum.getCompany());
	//
	//		super.getResponse().setAuthorised(status);
	//	}
	//
	//	@Override
	//	public void load() {
	//		Collection<PracticumSession> objects;
	//		int practicumId;
	//
	//		practicumId = super.getRequest().getData("masterId", int.class);
	//		objects = this.psRepository.findPracticumSessionsByPracticumId(practicumId);
	//
	//		super.getBuffer().setData(objects);
	//	}
	//
	//	@Override
	//	public void unbind(final PracticumSession object) {
	//		assert object != null;
	//		final int masterId = super.getRequest().getData("masterId", int.class);
	//		final Practicum practicum = this.psRepository.findPracticumById(masterId);
	//		final boolean exceptional = object.isExceptional();
	//		String res = "";
	//		if (exceptional == true)
	//			res = "Yes";
	//		else
	//			res = "No ";
	//
	//		Tuple tuple;
	//
	//		tuple = super.unbind(object, "title", "startPeriod", "finishPeriod");
	//		tuple.put("exceptionalCreate", practicum.isDraftMode());
	//		tuple.put("exceptional", res);
	//		super.getResponse().setData(tuple);
	//	}
	//
	//	@Override
	//	public void unbind(final Collection<PracticumSession> objects) {
	//		assert objects != null;
	//
	//		int practicumId;
	//		Practicum practicum;
	//		final boolean showCompany;
	//		final boolean exceptionalCreate;
	//
	//		practicumId = super.getRequest().getData("masterId", int.class);
	//		practicum = this.psRepository.findPracticumById(practicumId);
	//		showCompany = super.getRequest().getPrincipal().hasRole(practicum.getCompany());
	//		exceptionalCreate = practicum.isDraftMode();
	//
	//		super.getResponse().setGlobal("masterId", practicumId);
	//		super.getResponse().setGlobal("showCompany", showCompany);
	//		super.getResponse().setGlobal("exceptionalCreate", exceptionalCreate);
	//
	//	}

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumSessionRepository psRepository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("practicumId", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		final int practicumId;
		final Practicum practicum;

		practicumId = super.getRequest().getData("practicumId", int.class);
		practicum = this.psRepository.findPracticumById(practicumId);
		status = practicum != null && super.getRequest().getPrincipal().getActiveRoleId() == practicum.getCompany().getId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<PracticumSession> object;
		int practicumId;

		practicumId = super.getRequest().getData("practicumId", int.class);
		object = this.psRepository.findManyPracticumSessionsByPracticumId(practicumId);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final PracticumSession object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "abstract$", "startPeriod", "finishPeriod", "draftMode", "exceptional");

		super.getResponse().setData(tuple);
	}
}
