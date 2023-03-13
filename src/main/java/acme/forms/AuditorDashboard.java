
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditorDashboard extends AbstractForm {

	protected static final long	serialVersionUID	= 1L;

	//total number of audits that they have written for theory and hand-on courses
	protected int				totalNumberOfTheoryAudits;
	protected int				totalNumberOfHandsOnAudits;

	//average, deviation, minimum, and maximum number of auditing records in their audits
	protected Double			averageNumberOfAuditingRecords;
	protected Double			deviationNumberOfAuditingRecords;
	protected Double			minimumNumberOfAuditingRecords;
	protected Double			maximumNumberOfAuditingRecords;

	//average, deviation, minimum, and maximum time of the period lengths in their auditing records.
	protected Double			averagePeriodLength;
	protected Double			deviationPeriodLength;
	protected Double			minimumPeriodLength;
	protected Double			maximumPeriodLength;

}
