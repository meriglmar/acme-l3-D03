
package acme.entities.sessions;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.tutorials.Tutorial;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Session extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstract_;

	protected Boolean			theorySession;

	protected LocalDateTime		initTimePeriod;

	protected LocalDateTime		finishTimePeriod;

	@URL
	protected String			link;

	// Relationships ----------------------------------------------------------

	@NotNull
	@ManyToOne(optional = false)
	protected Tutorial			tutorial;

}
