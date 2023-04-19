
package acme.features.authenticated.audit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audits.Audit;
import acme.framework.components.accounts.Authenticated;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AuthenticatedAuditListService extends AbstractService<Authenticated, Audit> {

	@Autowired
	protected AuthenticatedAuditRepository auditRepository;


	@Override
	public void check() {
		final boolean status = super.getRequest().hasData("masterId", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		final int masterId = super.getRequest().getData("masterId", int.class);
		final Collection<Audit> objects = this.auditRepository.findAuditByCourseId(masterId);
		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Audit object) {
		assert object != null;
		final Tuple tuple = super.unbind(object, "code", "conclusion");
		super.getResponse().setData(tuple);
	}

}
