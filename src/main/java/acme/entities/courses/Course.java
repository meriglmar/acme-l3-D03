
package acme.entities.courses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.lectures.Lecture;
import acme.entities.lectures.TypeLecture;
import acme.framework.components.datatypes.Money;
import acme.framework.data.AbstractEntity;
import acme.roles.Lecturer;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course extends AbstractEntity {
	/*
	 * A course aggregates several lectures by the same lecturer.
	 * The system must store the following data about them:
	 * a code (pattern “[A-Z]{1,3} [0-9]{3}”, not blank, unique),
	 * a title (not blank, shorter than 76 characters),
	 * an abstract (not blank, shorter than 101 characters),
	 * an indication on whether it can be considered a theory course or a hands-on course (depending on the lectures that it aggregates),
	 * a retail price (positive or nought),
	 * and an optional link with further information. Purely theoretical courses must be rejected by the system.
	 *
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{1,3}\\d{3}$")
	protected String			code;

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstractCourse;

	@NotNull
	protected Money				retailPrice;

	@URL
	protected String			link;

	protected boolean			draftMode;


	//Los cursos puramente teóricos deben ser rechazados por el sistema
	@Transient
	public TypeCourse courseType(final List<Lecture> lectures) {
		TypeCourse res = TypeCourse.BALANCED;
		if (!lectures.isEmpty()) {
			final Map<TypeLecture, Integer> lecturesBytype = new HashMap<>();
			for (final Lecture lecture : lectures) {
				final TypeLecture type = lecture.getLectureType();
				if (lecturesBytype.containsKey(type))
					lecturesBytype.put(type, lecturesBytype.get(type) + 1);
				else
					lecturesBytype.put(type, 1);
			}
			if (lecturesBytype.containsKey(TypeLecture.HANDS_ON) && lecturesBytype.containsKey(TypeLecture.THEORY))
				if (lecturesBytype.get(TypeLecture.HANDS_ON) > lecturesBytype.get(TypeLecture.THEORY))
					res = TypeCourse.HANDS_ON;
				else if (lecturesBytype.get(TypeLecture.THEORY) > lecturesBytype.get(TypeLecture.HANDS_ON))
					res = TypeCourse.THEORY;
			if (lecturesBytype.containsKey(TypeLecture.HANDS_ON) && !lecturesBytype.containsKey(TypeLecture.THEORY))
				res = TypeCourse.HANDS_ON;
			if (!lecturesBytype.containsKey(TypeLecture.HANDS_ON) && lecturesBytype.containsKey(TypeLecture.THEORY))
				res = TypeCourse.THEORY;
		}
		return res;
	}

	//	Relationships -----------------------------------------


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Lecturer lecturer;

}
