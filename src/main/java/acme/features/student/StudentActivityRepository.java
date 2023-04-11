
package acme.features.student;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.entities.enrolments.Enrolment;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Student;

@Repository
public interface StudentActivityRepository extends AbstractRepository {

	@Query("select a from Activity a")
	Collection<Activity> findAllActivities();

	@Query("SELECT i FROM Course i WHERE i.id = :id")
	Activity findCourseById(int id);

	@Query("SELECT i FROM Student i WHERE i.id = :id")
	Student findStudentById(int id);

	@Query("SELECT i FROM Enrolment i WHERE i.id = :id")
	Enrolment findEnrolmentById(int id);

	//	@Query("select lc.lecture from LectureCourse lc where lc.course.id = :courseId")
	//	Collection<Lecture> findManyLecturersByCourseId(int courseId);
}
