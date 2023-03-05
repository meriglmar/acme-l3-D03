
package acme.entities.auditingRecords;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditingRecord extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	//a subject (not blank, shorter than 76 characters)
	@NotBlank
	@Length(max = 75)
	protected String			subject;

	//an assessment (not blank, shorter than 101 characters)
	@NotBlank
	@Length(max = 100)
	protected String			assessment;

	//the period during which the subject was audited (in the past, at least one hour long)
	@PastOrPresent
	protected LocalDateTime		period;

	//a mark (“A+”, “A”, “B”, “C”, “F”, or “F-“)
	protected TypeMark			mark;

	//an optional link with further information. 
	@URL
	protected String			moreInfo;

}
