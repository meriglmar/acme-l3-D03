
package acme.features.assistant.tutorial;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AssistantTutorialRepository extends AbstractRepository {

	@Query("select t from Tutorial t")
	List<Tutorial> findAllTutorials();

	@Query("select t from Tutorial t where t.assistant.id = :assistantId")
	List<Tutorial> findTutorialsByAssistant(@Param("assistantId") int assistantId);

	@Query("select t from Tutorial t where t.id = :id")
	Tutorial findTutorialById(@Param("id") int id);

}
