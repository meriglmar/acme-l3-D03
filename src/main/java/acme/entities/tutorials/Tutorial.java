
package acme.entities.tutorials;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import acme.entities.courses.Course;
import acme.framework.data.AbstractEntity;
import acme.roles.Assistant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tutorial extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}[0-9][0-9]{3}")
	protected String			code;

	@NotBlank
	@Size(max = 75)
	protected String			title;

	@NotBlank
	@Size(max = 100)
	protected String			abstractTutorial;

	@NotBlank
	@Size(max = 100)
	protected String			goals;

	protected Double			estimatedTotalTime;

	protected boolean			draftMode;

<<<<<<< Upstream, based on 28be164a6bca11cc0bb4935dc38b74904cb02697
=======
	// Derived attributes -----------------------------------------------------


	public Double estimatedTotalTime(final Collection<Session> sessions) {
		double res = 0.0;
		if (!sessions.isEmpty())
			for (final Session sesion : sessions) {
				final Date start = sesion.getInitTimePeriod();
				final Date end = sesion.getFinishTimePeriod();
				double horas = 0.0;
				double minutos = 0.0;
				horas = Math.abs(end.getTime() / 3600000 - start.getTime() / 3600000);
				minutos = Math.abs(end.getTime() / 60000 - start.getTime() / 60000) % 60;
				final double porcentajeMinutos = minutos / 60;
				res += horas + porcentajeMinutos;
			}
		return res;
	}

>>>>>>> cda6136 Task 111: correctly merged
	// Relationships ----------------------------------------------------------

	@NotNull
	@ManyToOne(optional = false)
	protected Course			course;

	@NotNull
	@ManyToOne(optional = false)
	protected Assistant			assistant;

}
