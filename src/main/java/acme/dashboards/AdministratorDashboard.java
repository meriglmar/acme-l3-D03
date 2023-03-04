
package acme.dashboards;

import java.util.Map;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard extends AbstractForm {
	//The system must handle administrator dashboards with the following indicators:
	//average, minimum, maximum, and standard deviation of the budget in the offers grouped by currency
	//average, minimum, maximum, and standard deviation of the number of notes posted over the last 10 weeks.

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	//total number of principals with each role
	private Integer				totalNumberOfAssistants;
	private Integer				totalNumberOfAuditors;
	private Integer				totalNumberOfCompanies;
	private Integer				totalNumberOfConsumers;
	private Integer				totalNumberOfLecturers;
	private Integer				totalNumberOfProviders;
	private Integer				totalNumberOfStudents;

	//ratio of peeps with both an email address and a link
	private double				ratioEmailAndLinkPeeps;

	//ratios of critical and non-critical bulletins
	private double				ratioCriticalBulletins;
	private double				ratioNonCriticalBulletins;

	// Average, minimum, maximum, and standard deviation of the budget in the offers grouped by currency.
	private Map<String, Double>	averageBudgetByCurrency;
	private Map<String, Double>	minimumBudgetByCurrency;
	private Map<String, Double>	maximumBudgetByCurrency;
	private Map<String, Double>	deviationBudgetByCurrency;

	// Average, minimum, maximum, and standard deviation of the number of notes posted over the last 10 weeks.
	private double				averageNotesInLast10Weeks;
	private double				minimumNotesInLast10Weeks;
	private double				maximumNotesInLast10Weeks;
	private double				deviationNotesInLast10Weeks;
}
