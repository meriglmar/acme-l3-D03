
package acme.entities.banners;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Banner extends AbstractEntity {

	/*
	 * A banner allows administrators to advertise products, services, or organisations.
	 * The system must store the following data about them: an instantiation/update moment (in the past),
	 * a display period (must start at any moment after the instantiation/update moment and must last for at least one week),
	 * a link to a picture that must be stored somewhere else,
	 * a slogan (not blank, shorter than 76 characters),
	 * and a link to a target web document.
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@PastOrPresent  //or Past
	@NotNull
	protected Date				moment;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				startDatePeriod;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				endDatePeriod;

	@URL
	@NotBlank
	protected String			imageLink;

	@NotBlank
	@Length(max = 75)
	protected String			eslogan;

	@URL
	@NotEmpty
	protected String			docLink;

}
