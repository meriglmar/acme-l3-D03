
package acme.features.authenticated.auditor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.components.accounts.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Auditor;

@Repository
public interface AuthenticatedAuditorRepository extends AbstractRepository {

	@Query("select a from Auditor a where a.userAccount.id = :id")
	Auditor findAuditorByUserAccountId(int id);

	@Query("select u from UserAccount u where u.id = :id")
	UserAccount findUserAccountById(int id);
}
