
package acme.entities.activities;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.enrolment.Enrolment;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Activity extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstractt;

	@NotBlank
	protected ActivityType		activityType;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				startPeriod;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				finPeriod;

	@URL
	protected String			link;

	@ManyToOne
	@NotNull
	private Enrolment			enrolment;


	//Propiedad derivada
	protected Duration timePeriod() {
		return Duration.ofDays(this.finPeriod.getTime() - this.startPeriod.getTime());
	}

}
