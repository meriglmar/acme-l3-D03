
package acme.entities.lectureCourses;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.entities.course.Course;
import acme.entities.lectures.Lecture;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LectureCourse extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@ManyToOne(optional = false)
	@Valid
	protected Course			course;

	@ManyToOne(optional = false)
	@Valid
	protected Lecture			lecture;

}
