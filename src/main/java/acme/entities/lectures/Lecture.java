
package acme.entities.lectures;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.course.TypeCourse;
import acme.entities.lectureCourses.LectureCourse;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lecture extends AbstractEntity {

	/*
	 * A lecture is a document that a lecturer uses to get some knowledge across.
	 * The system must store the following data about them: a title (not blank,
	 * shorter than 76 characters), an abstract (not blank, shorter than 101 characters),
	 * an estimated learning time (in hours, positive, not nought), a body
	 * (not blank, shorter than 101 characters), an indication on whether it can
	 * be considered theoretical or hands-on, and an optional link with further information.
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstractLecture;

	@NotBlank
	@Length(max = 100)
	protected String			body;

	@Digits(integer = 3, fraction = 2)
	@NotNull
	@Positive
	protected Double			estimatedLearningTimeInHours;

	@NotNull
	@Enumerated(EnumType.STRING)
	protected TypeCourse		lectureType;

	@URL
	protected String			link;

	protected boolean			published;

	@ManyToOne
	protected LectureCourse		course;

	//No sé si está bien
	//	@NotNull
	//	@Valid
	//	@ManyToOne(optional = false)
	//	protected Lecturer			lecturer;

}
