
package acme.entities.practicums;

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
public class Practicum extends AbstractEntity {

	/*
	 * Una práctica ayuda a poner en práctica los cursos prácticos en el contexto de una empresa
	 * real. El sistema debe almacenar los siguientes datos sobre ellos: un código
	 * (patrón “[A-Z]{1,3}[0-9][0-9]{3}”, no en blanco, único), un título (no en blanco,
	 * menos de 76 caracteres), un resumen (no en blanco, menos de 101 caracteres), algunos
	 * objetivos (no en blanco, menos de 101 caracteres) y un tiempo total estimado (en horas,
	 * calculado a partir de las sesiones correspondientes más/menos 10%)
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}[0-9][0-9]{3}")
	protected String			code;

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			summary; //Como "abstract" es una palabra reservada, pongo mejor "summary" 

	@NotBlank
	@Length(max = 100)
	protected String			goals;

	@PositiveOrZero
	protected int				estimatedTotalTime; //Me falta: calculado a partir de las sesiones correspondientes más/menos 10%

}
