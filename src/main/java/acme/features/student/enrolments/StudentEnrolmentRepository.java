
package acme.features.student.enrolments;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.course.Course;
import acme.entities.enrolments.Enrolment;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Student;

@Repository
public interface StudentEnrolmentRepository extends AbstractRepository {

	@Query("select a from Enrolment a")
	Collection<Enrolment> findAllEnrolments();

	@Query("SELECT i FROM Enrolment i WHERE i.id = :id")
	Enrolment findEnrolmentById(int id);

	@Query("SELECT i FROM Student i WHERE i.id = :id")
	Student findStudentById(int id);

	@Query("SELECT i FROM Course i WHERE i.id = :id")
	Course findCourseById(int id);

	@Query("select c from Course c")
	Collection<Course> findAllCourses();
}
