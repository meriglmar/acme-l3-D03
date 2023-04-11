
package acme.features.administrator.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.currency.Currency;
import acme.framework.components.accounts.Administrator;
import acme.framework.services.AbstractService;

@Service
public class AdministratorSystemConfigurationUpdateService extends AbstractService<Administrator, Currency> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSystemConfigurationRepository repo;

	// AbstractService interface ----------------------------------------------

}
