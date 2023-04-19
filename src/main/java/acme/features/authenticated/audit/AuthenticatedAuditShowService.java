
package acme.features.authenticated.audit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditingRecords.TypeMark;
import acme.entities.audits.Audit;
import acme.framework.components.accounts.Authenticated;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AuthenticatedAuditShowService extends AbstractService<Authenticated, Audit> {

	@Autowired
	protected AuthenticatedAuditRepository repo;


	@Override
	public void check() {
		final boolean status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		final int id = super.getRequest().getData("id", int.class);
		final Audit object = this.repo.findAuditById(id);
		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Audit object) {
		assert object != null;
		final Collection<TypeMark> marks = this.repo.findMarksByAuditId(object.getId());
		String mark;
		if (marks.isEmpty())
			mark = "Vacía";
		else
			mark = object.mark(marks).toString();
		final Tuple tuple = super.unbind(object, "code", "conclusion", "strongPoints", "weakPoints");
		tuple.put("auditor", object.getAuditor().getFirm());
		tuple.put("mark", mark);
		super.getResponse().setData(tuple);
	}

}
