
package acme.entities.activities;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.enrolments.Enrolment;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Activity extends AbstractEntity {

<<<<<<< Upstream, based on a50f42936ac0cac1e0cdcfaa908e61c464506f7c
	// Every enrolment has a workbook that is composed of activities.  
	//The system must store the fol-lowing data about them: a title (not blank, shorter than 76 characters), 
	//an abstract (not blank, shorter than 101 characters), an indication on whether it can be considered a theory activity or
	//a hands-on activity, a time period (either in the past or the future), and an optional link with further information. 

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstractt;

	@NotNull
	protected ActivityType		activityType;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				startPeriod;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				endPeriod;

	@URL
	protected String			link;

	//Propiedades derivadas


	protected Duration timePeriod() {
		return Duration.ofMillis(this.endPeriod.getTime() - this.startPeriod.getTime());
=======
	// Every enrolment has a workbook that is composed of activities.
	//The system must store the fol-lowing data about them: a title (not blank, shorter than 76 characters),
	//an abstract (not blank, shorter than 101 characters), an indication on whether it can be considered a theory activity or
	//a hands-on activity, a time period (either in the past or the future), and an optional link with further information.

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

	//Propiedades derivadas


	protected Duration timePeriod() {
		return Duration.ofMillis(this.finPeriod.getTime() - this.startPeriod.getTime());
>>>>>>> fadf59d Task 111: updating files
	}

	public double getDuration() {
		final Duration duration = this.timePeriod();
		return duration.toHours();
	}

	//Relationships


	@Valid
	@ManyToOne(optional = false)
	@NotNull
	protected Enrolment enrolment;

}
