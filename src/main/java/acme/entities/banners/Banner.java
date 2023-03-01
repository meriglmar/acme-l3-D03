
package acme.entities.banners;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Banner extends AbstractEntity {
	//un momento de instanciación/actualización (en el pasado), un período de visualización 
	//(debe comenzar en cualquier momento después del momento de instanciación/actualización y 
	//debe durar al menos una semana), un enlace a una imagen que debe almacenarse en otro lugar, 
	//un eslogan (no en blanco, de menos de 76 caracteres) y un enlace a un documento web de destino.

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date				moment;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				startPeriod;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				finPeriod;

	@URL
	@NotBlank
	protected String			imageLink;

	@NotBlank
	@Length(max = 76)
	protected String			eslogan;

	@URL
	@NotBlank
	protected String			docLink;


	//Debe durar al menos una semana
	public Duration periodOfTime() {
		return Duration.ofDays(this.finPeriod.getTime() - this.startPeriod.getTime());
	}

}
