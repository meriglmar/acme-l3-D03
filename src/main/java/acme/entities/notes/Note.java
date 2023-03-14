
package acme.entities.notes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Note extends AbstractEntity {

	/*
	 * Una nota es un mensaje publicado por un director autenticado. El sistema debe
	 * almacenar los siguientes datos sobre ellos: un momento de instanciación,
	 * un título (no en blanco, de menos de 76 caracteres), un autor (no en blanco, de
	 * menos de 76 caracteres), un mensaje (no en blanco, de menos de 101 caracteres). ),
	 * una dirección de correo electrónico opcional y un enlace opcional. El autor debe
	 * computarse de la siguiente manera: “〈nombre de usuario〉 - 〈apellido, nombre〉”,
	 * donde “〈nombre de usuario〉” denota el nombre de usuario del principal que ha publicado
	 * la nota y “〈apellido, nombre〉” denota su o su nombre completo.
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@PastOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				instantiationMoment;

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 75)
	@Pattern(regexp = "^\\w+\\s*-\\s*\\p{L}+,\\s*\\p{L}+$")
	protected String			author;

	@NotBlank
	@Length(max = 100)
	protected String			message;

	@Email
	protected String			email;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
