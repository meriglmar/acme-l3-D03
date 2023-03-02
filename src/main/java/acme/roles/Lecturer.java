
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Lecturer extends AbstractRole {

	/*
	 * There is a new project-specific role called lecturer,
	 * which has the following profile data:
	 * alma mater (not blank, shorter than 76 characters),
	 * a resume (not blank, shorter than 101 characters),
	 * list of qualifications (not blank, shorter than 101 characters),
	 * and an optional link with further information.
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			almaMater;

	@NotBlank
	@Length(max = 100)
	protected String			resume;

	@NotBlank
	@Length(max = 100)
	protected String			qualifications;

	@URL
	protected String			urlInfo;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
