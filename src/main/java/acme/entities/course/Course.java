
package acme.entities.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import acme.entities.enrolment.TypeCourse;
import acme.framework.components.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course {
	//Una inscripción es un registro de un estudiante en un curso. El sistema debe almacenar los siguientes datos sobre ellos: 
	//un código (patrón “[A-Z]{1,3}[0-9][0-9]{3}”, no en blanco, único), una motivación (no en blanco, menos de 76 caracteres), 
	//unos objetivos (no en blanco, menos de 101 caracteres) y un tiempo de trabajo (en horas, computado a partir de las actividades
	//correspondientes).

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Id
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}[0-9][0-9]{3}")
	protected String			code;

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstractCourse;

	@NotBlank
	protected TypeCourse		courseType			= TypeCourse.THEORY;

	@PositiveOrZero
	protected Money				retailPrice;

	@URL
	protected String			link;
}
