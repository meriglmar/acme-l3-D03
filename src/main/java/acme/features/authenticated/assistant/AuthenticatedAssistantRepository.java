
package acme.features.authenticated.assistant;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.framework.components.accounts.UserAccount;
import acme.framework.repositories.AbstractRepository;
<<<<<<< HEAD
import acme.roles.Assistant;
=======
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2

@Repository
public interface AuthenticatedAssistantRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :userAccountId")
	UserAccount findOneUserAccountById(@Param("userAccountId") int userAccountId);

<<<<<<< HEAD
	@Query("select a from Assistant a where a.userAccount.id = :id")
	Assistant findOneAssistantByUserAccountId(@Param("id") int id);

=======
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
}
