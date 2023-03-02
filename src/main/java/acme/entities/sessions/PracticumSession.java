
package acme.entities.sessions;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;

public class PracticumSession extends AbstractEntity {

	/*
	 * El sistema debe almacenar los siguientes datos sobre las sesiones en una práctica: un título
	 * (no en blanco, menos de 76 caracteres), un resumen (no en blanco, menos de 101 caracteres),
	 * un período de tiempo (al menos una semana antes, al menos una semana de duración), y
	 * un enlace opcional con más información
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			summary;

	//	protected LocalDateTime		timePeriod;
	//	
	//	@NotNull
	//	@Temporal(TemporalType.DATE)
	//	protected Date				startDate;
	//
	//	@NotNull
	//	@Temporal(TemporalType.DATE)
	//	protected Date				finishDate;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
