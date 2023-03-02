
package acme.roles;

import java.util.List;

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
public class Auditor extends AbstractRole {

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	//firma (no en blanco, menos de 76 caracteres), 
	//identificación profesional (no en blanco, menos de 26 caracteres)
	//una lista de certificaciones (no en blanco, menos de 101 caracteres)
	//un enlace opcional con más información.

	@NotBlank
	@Length(max = 75)
	protected String			firm;

	@NotBlank
	@Length(max = 25)
	protected String			professionalID;

	@NotBlank
	@Length(max = 100)
	protected List<String>		certifications;

	@URL
	protected String			moreInfo;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
