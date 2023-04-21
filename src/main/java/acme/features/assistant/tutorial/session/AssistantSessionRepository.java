
package acme.features.assistant.tutorial.session;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.sessions.Session;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AssistantSessionRepository extends AbstractRepository {

	@Query("select s from Session s where ")
	List<Session> findSessionsByTutorials();

}
