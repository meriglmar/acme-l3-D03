
package acme.features.lecturer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}
