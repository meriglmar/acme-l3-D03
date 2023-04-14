
package acme.features.lecturer.lecturerDashboard;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.lectures.TypeLecture;
import acme.forms.LecturerDashboard;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerDashboardShowService extends AbstractService<Lecturer, LecturerDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		final LecturerDashboard dashboard = new LecturerDashboard();
		int userAccountId;
		userAccountId = super.getRequest().getPrincipal().getAccountId();
		final Lecturer lecturer = this.repository.findLecturerByUserAccountId(userAccountId);

		final Collection<Double> courseEstimatedLearningTime = this.repository.sumLearningTimeByCourse(lecturer);

		final Integer handsOnLectures = this.repository.lecturesByType(lecturer, TypeLecture.HANDS_ON).orElse(0);
		final Integer theoryLectures = this.repository.lecturesByType(lecturer, TypeLecture.THEORY).orElse(0);
		dashboard.setTotalNumberOfTheoryLectures(theoryLectures);
		dashboard.setTotalNumberOfHandsOnLectures(handsOnLectures);

		dashboard.averageCalc(courseEstimatedLearningTime);
		dashboard.deviationCalc(courseEstimatedLearningTime);
		dashboard.minimumCalc(courseEstimatedLearningTime);
		dashboard.maximumCalc(courseEstimatedLearningTime);

		final double avgLearningTimeLectures = this.repository.averageLearningTimeOfLectures(lecturer).orElse(0.0);
		final double devLearningTimeLectures = this.repository.deviationLearningTimeOfLectures(lecturer).orElse(0.0);
		final double minLearningTimeLectures = this.repository.minimumLearningTimeOfLectures(lecturer).orElse(0.0);
		final double maxLearningTimeLectures = this.repository.maximumLearningTimeOfLectures(lecturer).orElse(0.0);

		final double factor = Math.pow(10, 2);
		final double numRedondeadoAvg = Math.round(avgLearningTimeLectures * factor) / factor;
		final double numRedondeadoDev = Math.round(devLearningTimeLectures * factor) / factor;

		dashboard.setAverageLearningTimeOfLectures(numRedondeadoAvg);
		dashboard.setDeviationLearningTimeOfLectures(numRedondeadoDev);
		dashboard.setMinimumLearningTimeOfLectures(minLearningTimeLectures);
		dashboard.setMaximumLearningTimeOfLectures(maxLearningTimeLectures);

		super.getBuffer().setData(dashboard);
	}

	@Override
	public void unbind(final LecturerDashboard object) {
		Tuple tuple;

		tuple = super.unbind(object, "totalNumberOfTheoryLectures", "totalNumberOfHandsOnLectures", "averageLearningTimeOfLectures", "deviationLearningTimeOfLectures", "minimumLearningTimeOfLectures", "maximumLearningTimeOfLectures",
			"averageLearningTimeOfCourses", "deviationLearningTimeOfCourses", "minimumLearningTimeOfCourses", "maximumLearningTimeOfCourses");

		super.getResponse().setData(tuple);
	}

}
