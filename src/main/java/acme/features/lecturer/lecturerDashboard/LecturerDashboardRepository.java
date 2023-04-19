
package acme.features.lecturer.lecturerDashboard;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.lectures.TypeLecture;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;

@Repository
public interface LecturerDashboardRepository extends AbstractRepository {

	@Query("select l from Lecturer l where l.userAccount.id = :id")
	Lecturer findLecturerByUserAccountId(int id);

	// Lectures: theory or hand-on

	@Query("select count(distinct l) from Lecture l inner join LectureCourse lc on l = lc.lecture inner join Course c on lc.course = c where c.lecturer = :lecturer and l.lectureType = :type")
	Optional<Integer> lecturesByType(Lecturer lecturer, TypeLecture type);

	// Lectures: max, min, dev, avg

	@Query("select avg(l.estimatedLearningTimeInHours) from Lecture l inner join LectureCourse lc on l = lc.lecture inner join Course c on lc.course = c where c.lecturer = :lecturer")
	Optional<Double> averageLearningTimeOfLectures(Lecturer lecturer);

	@Query("select stddev(l.estimatedLearningTimeInHours) from Lecture l inner join LectureCourse lc on l = lc.lecture inner join Course c on lc.course = c where c.lecturer = :lecturer")
	Optional<Double> deviationLearningTimeOfLectures(Lecturer lecturer);

	@Query("select min(l.estimatedLearningTimeInHours) from Lecture l inner join LectureCourse lc on l = lc.lecture inner join Course c on lc.course = c where c.lecturer = :lecturer")
	Optional<Double> minimumLearningTimeOfLectures(Lecturer lecturer);

	@Query("select max(l.estimatedLearningTimeInHours) from Lecture l inner join LectureCourse lc on l = lc.lecture inner join Course c on lc.course = c where c.lecturer = :lecturer")
	Optional<Double> maximumLearningTimeOfLectures(Lecturer lecturer);

	// Courses: max,min,dev,avg

	@Query("select sum(l.estimatedLearningTimeInHours) from Course c join LectureCourse lc on c = lc.course join Lecture l on lc.lecture = l where c.lecturer = :lecturer group by c")
	Collection<Double> sumLearningTimeByCourse(Lecturer lecturer);

}
