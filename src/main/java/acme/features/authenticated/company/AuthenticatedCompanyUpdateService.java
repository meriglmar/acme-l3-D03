
package acme.features.authenticated.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.accounts.Authenticated;
import acme.framework.components.accounts.Principal;
import acme.framework.components.models.Tuple;
import acme.framework.controllers.HttpMethod;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class AuthenticatedCompanyUpdateService extends AbstractService<Authenticated, Company> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedCompanyRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Company.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Company object;
		Principal principal;
		int userAccountId;

		principal = super.getRequest().getPrincipal();
		userAccountId = principal.getAccountId();
		object = this.repository.findOneCompanyByUserAccountId(userAccountId);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Company object) {
		assert object != null;

		super.bind(object, "name", "vat", "abstract$", "link");
	}

	@Override
	public void validate(final Company object) {
		assert object != null;
	}

	@Override
	public void perform(final Company object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Company object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "name", "vat", "abstract$", "link");

		super.getResponse().setData(tuple);
	}

	@Override
	public void onSuccess() {
		if (super.getRequest().getMethod().equals(HttpMethod.POST))
			PrincipalHelper.handleUpdate();
	}

}
