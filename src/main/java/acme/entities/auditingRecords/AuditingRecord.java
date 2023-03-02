
package acme.entities.auditingRecords;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditingRecord extends AbstractEntity {

	//El sistema debe almacenar los siguientes datos sobre los registros de auditoría de un curso:
	//una materia (no en blanco, de menos de 76 caracteres)
	//una evaluación (no en blanco, de menos de 101 caracteres)
	//el período durante el cual la materia fue auditada ( en el pasado, al menos una hora de duración)
	//una marca ("A+", "A", "B", "C", "F" o "F-")
	//un enlace opcional con más información.

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			subject;

	@NotBlank
	@Length(max = 100)
	protected String			assessment;

	//Esta falta ponerle que sea una fecha anterior a la actual
	//Al menos una hora de duración
	protected String			period;

	protected TypeMark			mark;

	@URL
	protected String			moreInfo;

}
