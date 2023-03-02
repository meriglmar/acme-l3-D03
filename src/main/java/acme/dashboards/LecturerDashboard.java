
package acme.dashboards;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturerDashboard extends AbstractForm {
	/*
	 * 
	 * The system must handle lecturer dashboard with the following data:
	 * total number of theory and hands-on lectures;
	 * average, deviation, minimum, and maximum learning time of the lectures;
	 * average, deviation, minimum, and maximum learning time of the courses.
	 * 
	 */

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected int				totalNumberOfTheoryLectures;
	protected int				totalNumberOfHandsOnLectures;

	protected Double			averageLearningTimeOfLecturers;
	protected Double			deviationLearningTimeOfLecturers;
	protected Double			minimumLearningTimeOfLecturers;
	protected Double			maximumLearningTimeOfLecturers;

	protected Double			averageLearningTimeOfCourses;
	protected Double			deviationLearningTimeOfCourses;
	protected Double			minimumLearningTimeOfCourses;
	protected Double			maximumLearningTimeOfCourses;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
