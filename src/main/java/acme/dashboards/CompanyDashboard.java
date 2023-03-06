
package acme.dashboards;

import java.util.Map;

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

	protected static final long		serialVersionUID	= 1L;

	// Attributes ----------------------------------------------------------

	protected Map<String, Integer>	totalPracticumsTheoryCoursesByMonthLastYear;
	protected Map<String, Integer>	totalPracticumsHandsOnCoursesByMonthLastYear;

	protected double				avgPracticumSessionTime;
	protected double				devPracticumSessionTime;
	protected double				minPracticumSessionTime;
	protected double				maxPracticumSessionTime;

	protected double				avgPracticumTime;
	protected double				devPracticumTime;
	protected double				minPracticumTime;
	protected double				maxPracticumTime;

	// Derived attributes --------------------------------------------------

	// Relationships -------------------------------------------------------

}
