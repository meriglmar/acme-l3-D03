
package acme.entities.tutorials;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
<<<<<<< Upstream, based on 1310c4f0e810dcb97b2c27b92463b787b7af16d8
import javax.validation.Valid;
=======
>>>>>>> 7268a1e Task-111: first part almost done
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

	// Derived attributes -----------------------------------------------------


	@Transient
	public boolean isAvailable() {

		return !this.draftMode;

	}

	// Relationships ----------------------------------------------------------


	@NotNull
	@ManyToOne(optional = false)
	protected Course	course;

	@NotNull
<<<<<<< Upstream, based on 1310c4f0e810dcb97b2c27b92463b787b7af16d8
	@Valid
=======
>>>>>>> 7268a1e Task-111: first part almost done
	@ManyToOne(optional = false)
	protected Assistant	assistant;

}
