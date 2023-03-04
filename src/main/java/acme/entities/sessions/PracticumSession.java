
package acme.entities.sessions;

import java.time.Duration;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.practicums.Practicum;
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

	@NotNull
	@Temporal(TemporalType.DATE)
	protected Date				startPeriod;

	@NotNull
	@Temporal(TemporalType.DATE)
	protected Date				finishPeriod;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------

	//	@Transient
	//	protected boolean IsValid() {
	//		boolean result = false;
	//		final Duration period = Duration.ofMillis(this.finishPeriod.getTime() - this.startPeriod.getTime());
	//		if (period.toDays() >= 7)
	//			result = true;
	//		return result;
	//	}


	@Transient
	protected Duration timePeriod() {
		final long diffInMillis = this.finishPeriod.getTime() - this.startPeriod.getTime();
		if (diffInMillis >= 7 * 24 * 60 * 60 * 1000)
			return Duration.ofMillis(diffInMillis);
		else
			return null; // Si la diferencia es menor a 7 días, devuelve null
	}

	@Transient
	public double getDuration() {
		final Duration duration = this.timePeriod();
		return duration.toHours();
	}


	// Relationships ----------------------------------------------------------
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	protected Practicum practicum;
}
