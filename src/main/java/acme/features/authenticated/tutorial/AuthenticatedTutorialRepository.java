
package acme.features.authenticated.tutorial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.courses.Course;
import acme.entities.tutorials.Tutorial;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Assistant;

@Repository
public interface AuthenticatedTutorialRepository extends AbstractRepository {

	@Query("select t from Tutorial t where t.course.draftMode = false")
	Collection<Tutorial> findTutorialsPublishedCourses();

	@Query("select t from Tutorial t where t.id = :id")
	Tutorial findTutorialById(@Param("id") int id);

	@Query("select c from Course c")
	Collection<Course> findAllCourses();

	@Query("select a from Assistant a")
	Collection<Assistant> findAllAssistants();

}
