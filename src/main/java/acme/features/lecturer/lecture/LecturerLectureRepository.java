
package acme.features.lecturer.lecture;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.course.Course;
import acme.entities.lectureCourses.LectureCourse;
import acme.entities.lectures.Lecture;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;

@Repository
public interface LecturerLectureRepository extends AbstractRepository {

	@Query("select a from Lecture a")
	Collection<Lecture> findAllLectures();

	@Query("SELECT i FROM Lecture i WHERE i.id = :id")
	Lecture findLectureById(int id);

	@Query("SELECT i FROM Lecturer i WHERE i.id = :id")
	Lecturer findLecturerById(int id);

	@Query("select l from Lecturer l where l.id = :id")
	Lecturer findOneLecturerById(int id);

	@Query("select c from Course c where c.id = :id")
	Course findOneCourseById(int id);

	@Query("select c from Course c inner join LectureCourse lc on c = lc.course inner join Lecture l on lc.lecture = l where l.id = :id")
	Collection<Course> findManyCoursesByLectureId(int id);

	@Query("select l from Lecture l where l.id = :id")
	Lecture findOneLectureById(int id);

	@Query("select l from Lecture l inner join LectureCourse lc on l = lc.lecture inner join Course c on lc.course = c where c.id = :masterId")
	Collection<Lecture> findManyLecturesByCourseId(int masterId);

	@Query("select lc from LectureCourse lc where lc.lecture = :lecture")
	Collection<LectureCourse> findManyLectureCourseByLecture(Lecture lecture);

	@Query("select l from Lecture l where l.lecturer = :lecturer")
	Collection<Lecture> findLecturesByLecturer(Lecturer lecturer);

}
