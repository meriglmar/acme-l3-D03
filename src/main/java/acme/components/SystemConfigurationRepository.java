
package acme.components;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SystemConfigurationRepository extends AbstractRepository {

	@Query("select s from SystemConfiguration s")
	SystemConfiguration findSystemConfiguration();
}
