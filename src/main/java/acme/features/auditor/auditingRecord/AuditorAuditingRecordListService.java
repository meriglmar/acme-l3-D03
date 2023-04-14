
package acme.features.auditor.auditingRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditingRecords.AuditingRecord;
import acme.entities.audits.Audit;
import acme.framework.components.accounts.Principal;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Auditor;

@Service
public class AuditorAuditingRecordListService extends AbstractService<Auditor, AuditingRecord> {

	@Autowired
<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
	protected AuditorAuditingRecordRepository repo;


	@Override
	public void check() {
		boolean status;
		status = super.getRequest().hasData("masterId", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		final int masterId = super.getRequest().getData("masterId", int.class);
		final Audit object = this.repo.findAuditById(masterId);
		final Principal principal = super.getRequest().getPrincipal();
		final int userAccountId = principal.getAccountId();
		super.getResponse().setAuthorised(object.getAuditor().getUserAccount().getId() == userAccountId);
	}

	@Override
	public void load() {
		Collection<AuditingRecord> objects;
		int masterId;
		masterId = super.getRequest().getData("masterId", int.class);
		objects = this.repo.findAuditingRecordsByAuditId(masterId);
		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final AuditingRecord object) {
		assert object != null;
		final int masterId = super.getRequest().getData("masterId", int.class);
		final Audit audit = this.repo.findAuditById(masterId);
		final Tuple tuple = super.unbind(object, "subject", "assessment");
		tuple.put("draftMode", audit.isDraftMode());
		super.getResponse().setData(tuple);
	}

	@Override
	public void unbind(final Collection<AuditingRecord> object) {
		assert object != null;
		boolean createButton = false;
		final int masterId = super.getRequest().getData("masterId", int.class);
		final Audit audit = this.repo.findAuditById(masterId);
		if (super.getRequest().getPrincipal().getAccountId() == audit.getAuditor().getUserAccount().getId())
			createButton = true;
		super.getResponse().setGlobal("createButton", createButton);
		super.getResponse().setGlobal("draftMode", audit.isDraftMode());
=======
	protected AuditorAuditingRecordRepository repository;


	@Override
	public void check() {
		boolean status;
		status = super.getRequest().hasData("masterId", int.class);
		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		final int masterId = super.getRequest().getData("masterId", int.class);
		final Audit object = this.repository.findAuditById(masterId);
		final Principal principal = super.getRequest().getPrincipal();
		final int userAccountId = principal.getAccountId();
		super.getResponse().setAuthorised(object.getAuditor().getUserAccount().getId() == userAccountId);
	}

	@Override
	public void load() {
		Collection<AuditingRecord> objects;
		int masterId;
		masterId = super.getRequest().getData("masterId", int.class);
		objects = this.repository.findAuditingRecordsByAuditId(masterId);
		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final AuditingRecord object) {
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "subject", "assessment");
		tuple.put("draftMode", object.getAudit().isDraftMode());
		super.getResponse().setData(tuple);
	}

	@Override
	public void unbind(final Collection<AuditingRecord> object) {
		assert object != null;
		boolean createButton = false;
		final int masterId = super.getRequest().getData("masterId", int.class);
		final Audit audit = this.repository.findAuditById(masterId);
		if (super.getRequest().getPrincipal().getAccountId() == audit.getAuditor().getUserAccount().getId())
			createButton = true;
		super.getResponse().setGlobal("createButton", createButton);
>>>>>>> 25e5797 Task 127: In progress
		super.getResponse().setGlobal("masterId", masterId);
	}

}
