
package acme.forms;

import java.util.Map;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard extends AbstractForm {

	protected static final long		serialVersionUID	= 1L;

	//total number of principals with each role
	protected Integer				totalNumberOfAssistants;
	protected Integer				totalNumberOfAuditors;
	protected Integer				totalNumberOfCompanies;
	protected Integer				totalNumberOfConsumers;
	protected Integer				totalNumberOfLecturers;
	protected Integer				totalNumberOfProviders;
	protected Integer				totalNumberOfStudents;

	//ratio of peeps with both an email address and a link
	protected double				ratioEmailAndLinkPeeps;

	//ratios of critical and non-critical bulletins
	protected double				ratioCriticalBulletins;
	protected double				ratioNonCriticalBulletins;

	//average, minimum, maximum, and standard deviation of the budget in the offers grouped by currency
	protected Map<String, Double>	averageBudgetByCurrency;
	protected Map<String, Double>	minimumBudgetByCurrency;
	protected Map<String, Double>	maximumBudgetByCurrency;
	protected Map<String, Double>	deviationBudgetByCurrency;

	//average, minimum, maximum, and standard deviation of the number of notes posted over the last 10 weeks.
	protected double				averageNotesInLast10Weeks;
	protected double				minimumNotesInLast10Weeks;
	protected double				maximumNotesInLast10Weeks;
	protected double				deviationNotesInLast10Weeks;

}
