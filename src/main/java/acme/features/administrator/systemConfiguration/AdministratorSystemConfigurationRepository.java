
package acme.features.administrator.systemConfiguration;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.currency.Currency;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSystemConfigurationRepository extends AbstractRepository {

	@Query("select c from Currency c where c.id = :id")
	Currency findCurrencyById(int id);

	@Query("select c from Currency c")
	Collection<Currency> findAllCurrencies();

}
