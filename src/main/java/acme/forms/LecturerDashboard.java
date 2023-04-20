
package acme.forms;

import java.util.Collection;

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

	protected Double			averageLearningTimeOfLectures;
	protected Double			deviationLearningTimeOfLectures;
	protected Double			minimumLearningTimeOfLectures;
	protected Double			maximumLearningTimeOfLectures;

	protected Double			averageLearningTimeOfCourses;
	protected Double			deviationLearningTimeOfCourses;
	protected Double			minimumLearningTimeOfCourses;
	protected Double			maximumLearningTimeOfCourses;

	// Derived attributes -----------------------------------------------------


	public void averageCalc(final Collection<Double> values) {
		double res = 0.0;
		if (!values.isEmpty()) {
			final Double total = values.stream().mapToDouble(Double::doubleValue).sum();
			res = total / values.size();
		}
		final double factor = Math.pow(10, 2);
		final double numRedondeadoAvg = Math.round(res * factor) / factor;
		this.averageLearningTimeOfCourses = numRedondeadoAvg;
	}

	public void deviationCalc(final Collection<Double> values) {
		Double res = 0.0;
		Double aux = 0.0;
		if (!values.isEmpty()) {
			for (final Double value : values)
				aux = Math.pow(value + this.averageLearningTimeOfCourses, 2);
			res = Math.sqrt(aux / values.size());
		}
		final double factor = Math.pow(10, 2);
		final double numRedondeadoDev = Math.round(res * factor) / factor;
		this.deviationLearningTimeOfCourses = numRedondeadoDev;
	}

	public void minimumCalc(final Collection<Double> values) {
		Double res = 0.0;
		if (!values.isEmpty())
			res = values.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
		final double factor = Math.pow(10, 2);
		final double numRedondeadoMin = Math.round(res * factor) / factor;
		this.minimumLearningTimeOfCourses = numRedondeadoMin;
	}

	public void maximumCalc(final Collection<Double> values) {
		Double res = 0.0;
		if (!values.isEmpty())
			res = values.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
		final double factor = Math.pow(10, 2);
		final double numRedondeadoMax = Math.round(res * factor) / factor;
		this.maximumLearningTimeOfCourses = numRedondeadoMax;
	}

	// Relationships ----------------------------------------------------------

}
