
package acme.dashboards;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturerDashboard implements Serializable {
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

	int							totalNumberOfTheoryLectures;
	int							totalNumberOfHandsOnLectures;

	Double						averageLearningTimeOfLecturers;
	Double						deviationLearningTimeOfLecturers;
	Double						minimumLearningTimeOfLecturers;
	Double						maximumLearningTimeOfLecturers;

	Double						averageLearningTimeOfCourses;
	Double						deviationLearningTimeOfCourses;
	Double						minimumLearningTimeOfCourses;
	Double						maximumLearningTimeOfCourses;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
