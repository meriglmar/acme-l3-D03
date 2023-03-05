
package acme.dashboards;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssistantDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected int				totalNumberOfTutorialsTheory;
	protected int				totalNumberOfTutorialsHandsOn;

	protected Double			averageSessionTime;
	protected Double			deviationSessionTime;
	protected Double			minimumSessionTime;
	protected Double			maximumSessionTime;

	protected Double			averageTutorialsTime;
	protected Double			deviationTutorialsTime;
	protected Double			minimumTutorialsTime;
	protected Double			maximumTutorialsTime;

}
