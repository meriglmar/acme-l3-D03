
package acme.entities.audits;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Audit extends AbstractEntity {

	//Una auditoría es un documento con registros de auditoría sobre un curso publicado. 
	//El sistema debe almacenar los siguientes datos sobre ellos: 
	//un código (patrón “[A-Z]{1,3}[0-9][0-9]{3}”, no en blanco, único)
	//una conclusión (no en blanco, menos de 101 caracteres)
	//algunos puntos fuertes (no en blanco, menos de 101 caracteres)
	//algunos puntos débiles (no en blanco, menos de 101 caracteres)
	//una nota (computada como la moda de las notas en los registros de auditoría correspondientes; empates debe romperse arbitrariamente si es necesario).

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}[0-9][0-9]{3}")
	protected String			code;

	@NotBlank
	@Length(max = 100)
	protected String			conclusion;

	@NotBlank
	@Length(max = 100)
	protected String			strongPoints;

	@NotBlank
	@Length(max = 100)
	protected String			weakPoints;

	// Derived attributes -----------------------------------------------------
	//Para hacer este tiene que estar el de Merme
	@PositiveOrZero
	protected int				mark; //una nota (computada como la moda de las notas en los registros de auditoría correspondientes; empates debe romperse arbitrariamente si es necesario).

}
