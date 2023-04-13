
package acme.features.assistant.tutorial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Assistant;

@Repository
public interface AssistantTutorialRepository extends AbstractRepository {

	@Query("select t from Tutorial t where t.assistant.id = :id")
	Collection<Tutorial> findTutorialsByAssistantId(@Param("id") int id);

	@Query("select t from Tutorial t where t.id = :id")
	Tutorial findTutorialById(@Param("id") int id);

	@Query("select a from Assistant a where a.id = :id")
	Assistant findAssistantById(@Param("id") int id);

}
