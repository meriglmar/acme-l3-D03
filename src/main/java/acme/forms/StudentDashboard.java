
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentDashboard extends AbstractForm {
	//The system must handle student dashboards with the following data: total number of theory and hands-on activities in his
	//or her workbook; average, deviation, minimum, and maximum period of the activities in his or her workbook; average, deviation,
	//minimum, and maximum learning time of the courses in which he or she has enrolled.

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected Integer			totalNumberOfTheoryActivities;
	protected Integer			totalNumberOfHandsOnActivities;

	protected Double			averagePeriodOfActivities;
	protected Double			deviationPeriodOfActivities;
	protected Double			minimumPeriodOfActivities;
	protected Double			maximumPeriodOfActivities;

	protected Double			averageLearningTimeOfCourses;
	protected Double			deviationLearningTimeOfCourses;
	protected Double			minimumLearningTimeOfCourses;
	protected Double			maximumLearningTimeOfCourses;
}
