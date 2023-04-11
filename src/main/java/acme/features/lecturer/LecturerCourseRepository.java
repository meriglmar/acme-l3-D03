
package acme.features.lecturer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.course.Course;
import acme.entities.lectures.Lecture;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;

@Repository
public interface LecturerCourseRepository extends AbstractRepository {

	@Query("select a from Course a")
	Collection<Course> findAllCourses();

	@Query("SELECT i FROM Course i WHERE i.id = :id")
	Course findCourseById(int id);

	@Query("SELECT i FROM Lecturer i WHERE i.id = :id")
	Lecturer findLecturerById(int id);

	@Query("select lc.lecture from LectureCourse lc where lc.course.id = :courseId")
	Collection<Lecture> findManyLecturersByCourseId(int courseId);

}
