
package acme.features.assistant.tutorial.session;

import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AssistantSessionRepository extends AbstractRepository {

	//	@Query("select s from Session s where ")
	//	List<Session> findSessionsByTutorials();

}
