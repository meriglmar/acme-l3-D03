
package acme.entities.systemConfiguration;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SystemConfiguration extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long			serialVersionUID			= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	protected TypeSystemConfiguration	currentSystemConfiguration	= TypeSystemConfiguration.EUR;

}
