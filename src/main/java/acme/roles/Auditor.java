
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auditor extends AbstractRole {

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	//firma (no en blanco, menos de 76 caracteres), identificación profesional 
	//(no en blanco, menos de 26 caracteres), una lista de certificaciones (no en 
	//blanco, menos de 26 caracteres) de 101 caracteres), y un enlace opcional con 
	//más información.

	@NotBlank
	@Length(max = 76)
	protected String			firm;

	@NotBlank
	@Length(max = 26)
	protected String			professionalID;

	@NotBlank
	@Length(max = 101)
	protected String			certifications;

	protected String			moreInfo;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
