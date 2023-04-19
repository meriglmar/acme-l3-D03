
package acme.features.student.enrolments;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.courses.Course;
import acme.entities.enrolments.Enrolment;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Student;

@Repository
public interface StudentEnrolmentRepository extends AbstractRepository {

	@Query("select e from Enrolment e where e.id=:id")
	Enrolment findEnrolmentById(int id);

	@Query("select e from Enrolment e where e.code=:code")
	Enrolment findEnrolmentByCode(String code);

	@Query("select e from Enrolment e where e.student.id=:activeRoleId")
	Collection<Enrolment> findEnrolmentsByStudentId(int activeRoleId);

	@Query("select sum(TIME_TO_SEC(TIMEDIFF(a.endPeriod, a.startPeriod)) / 3600) from Activity a where a.enrolment.id=:id")
	Double findWorktimeByEnrolmentId(int id);

	@Query("select c from Course c where c.id=:courseId")
	Course findCourseById(int courseId);

	@Query("select s from Student s where s.id=:id")
	Student findStudentById(int id);

	@Query("select count(a) from Activity a where a.enrolment.id=:id")
	Integer findNumberActivitiesByEnrolmentId(int id);

	//@Query("select c from Course c where c.draftMode=false")
	@Query("select c from Course c")
	Collection<Course> findAllCourses();

}
