
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
public class Company extends AbstractRole {

	/*
	 * Hay un nuevo rol específico del proyecto llamado empresa, que tiene los siguientes datos
	 * de perfil: nombre (no en blanco, de menos de 76 caracteres), número de IVA (no en blanco,
	 * de menos de 26 caracteres), resumen (no en blanco, menos de 101 caracteres) y
	 * un enlace opcional con más información.
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			name;

	@NotBlank
	@Length(max = 25)
	protected String			vat;

	@NotBlank
	@Length(max = 100)
	protected String			abstract$;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
