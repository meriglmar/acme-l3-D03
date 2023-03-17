
package acme.entities.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.audits.Audit;
import acme.framework.components.datatypes.Money;
import acme.framework.data.AbstractEntity;
import acme.roles.Lecturer;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course extends AbstractEntity {
	/*
	 * A course aggregates several lectures by the same lecturer.
	 * The system must store the following data about them:
	 * a code (pattern “[A-Z]{1,3} [0-9]{3}”, not blank, unique),
	 * a title (not blank, shorter than 76 characters),
	 * an abstract (not blank, shorter than 101 characters),
	 * an indication on whether it can be considered a theory course or a hands-on course (depending on the lectures that it aggregates),
	 * a retail price (positive or nought),
	 * and an optional link with further information. Purely theoretical courses must be rejected by the system.
	 * 
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}[0-9]{3}")
	protected String			code;

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstractCourse;

	@NotNull
	protected Money				retailPrice;

	@URL
	protected String			link;

	//	Relationships -----------------------------------------

	@NotNull
	@ManyToOne(optional = false)
	protected Lecturer			lecturer;

	@ManyToOne(optional = false)
	@NotNull
	protected Audit				audit;

}
