
package acme.entities.enrolments;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.activities.Activity;
import acme.entities.courses.Course;
import acme.framework.data.AbstractEntity;
import acme.roles.Student;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Enrolment extends AbstractEntity {
	//Una inscripción es un registro de un estudiante en un curso. El sistema debe almacenar los siguientes datos sobre ellos: 
	//un código (patrón “[A-Z]{1,3}[0-9][0-9]{3}”, no en blanco, único), una motivación (no en blanco, menos de 76 caracteres), 
	//unos objetivos (no en blanco, menos de 101 caracteres) y un tiempo de trabajo (en horas, computado a partir de las actividades
	//correspondientes).

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}[0-9][0-9]{3}")
	protected String			code;

	@NotBlank
	@Length(max = 75)
	protected String			motivation;

	@NotBlank
	@Length(max = 100)
	protected String			goals;


	@Transient
	protected double workTime() {
		double totalWorkTime = 0;
		for (final Activity activity : this.activities)
			totalWorkTime += activity.getDuration();
		return totalWorkTime;
	}


	protected boolean			finalised;

	@NotNull
	@OneToMany(mappedBy = "enrolment")
	protected List<Activity>	activities;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	protected Student			student;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	protected Course			course;
}
