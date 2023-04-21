
package acme.features.assistant.tutorial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import acme.entities.courses.Course;
import acme.entities.sessions.Session;
=======
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Assistant;

@Repository
public interface AssistantTutorialRepository extends AbstractRepository {

	@Query("select t from Tutorial t where t.assistant.id = :id")
<<<<<<< HEAD
	Collection<Tutorial> findManyTutorialsByAssistantId(@Param("id") int id);
=======
	Collection<Tutorial> findTutorialsByAssistantId(@Param("id") int id);
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2

	@Query("select t from Tutorial t where t.id = :id")
	Tutorial findTutorialById(@Param("id") int id);

<<<<<<< HEAD
	@Query("select s from Session s where s.tutorial = :tutorial")
	Collection<Session> findTutorialSessionsByTutorial(@Param("tutorial") Tutorial tutorial);

=======
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
	@Query("select a from Assistant a where a.id = :id")
	Assistant findAssistantById(@Param("id") int id);

	@Query("select c from Course c where c.id = :id")
	Course findCourseById(@Param("id") int id);

	@Query("select c from Course c")
	Collection<Course> findAllCourses();

}
