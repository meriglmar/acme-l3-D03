
package acme.dashboards;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.components.datatypes.Money;
import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyExchange extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Query attributes -------------------------------------------------------

	@NotNull
	@Valid
	public Money				currencyOriginal;

	@NotBlank
	public String				targetCurrency;

	// Response attributes ----------------------------------------------------

	@Valid
	public Money				currencyChanged;

	public Date					date;

}
