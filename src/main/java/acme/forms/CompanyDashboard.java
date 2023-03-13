
package acme.forms;

import java.util.List;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDashboard extends AbstractForm {

	/*
	 * El sistema debe manejar cuadros de mando de la empresa con los siguientes datos:
	 * número total de prácticas con respecto a cursos teóricos o prácticos agrupados por mes
	 * durante el último año; promedio, desviación, duración mínima y máxima de las
	 * sesiones en sus prácticas; duración promedio, desviación, mínimo y máximo
	 * del período de sus prácticas.
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------

	protected List<String>		pTLastYearByMonthNumber;

	protected Double			avgPracticumSessionTime;
	protected Double			devPracticumSessionTime;
	protected Double			minPracticumSessionTime;
	protected Double			maxPracticumSessionTime;

	protected Double			avgPracticumTime;
	protected Double			devPracticumTime;
	protected Double			minPracticumTime;
	protected Double			maxPracticumTime;

	// Derived attributes --------------------------------------------------

	// Relationships -------------------------------------------------------

}
