
<<<<<<< HEAD
package acme.features.any.peep;
=======
<<<<<<<< HEAD:src/main/java/acme/features/any/course/AnyCourseListService.java
package acme.features.any.course;
========
package acme.features.any.peep;
>>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2:src/main/java/acme/features/any/peep/AnyPeepListService.java
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import acme.components.SystemConfigurationService;
import acme.entities.peeps.Peep;
=======
<<<<<<<< HEAD:src/main/java/acme/features/any/course/AnyCourseListService.java
import acme.entities.courses.Course;
========
import acme.components.SystemConfigurationService;
import acme.entities.peeps.Peep;
>>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2:src/main/java/acme/features/any/peep/AnyPeepListService.java
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
<<<<<<< HEAD
public class AnyPeepListService extends AbstractService<Any, Peep> {
=======
public class AnyCourseListService extends AbstractService<Any, Course> {
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2

	// Internal state ---------------------------------------------------------

	@Autowired
<<<<<<< HEAD
=======
<<<<<<<< HEAD:src/main/java/acme/features/any/course/AnyCourseListService.java
	protected AnyCourseRepository repository;
========
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
	protected AnyPeepRepository				repository;

	@Autowired
	protected SystemConfigurationService	scService;
<<<<<<< HEAD

	// AbstractService<Any, Peep> ----------------------------------------------
=======
>>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2:src/main/java/acme/features/any/peep/AnyPeepListService.java

	// AbstractService interface ----------------------------------------------
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2


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
<<<<<<< HEAD
		Collection<Peep> objects;

		objects = this.repository.findAllPeeps();
=======
		Collection<Course> objects;

		objects = this.repository.findCourses();
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2

		super.getBuffer().setData(objects);
	}

	@Override
<<<<<<< HEAD
	public void unbind(final Peep object) {
=======
	public void unbind(final Course object) {
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
		assert object != null;

		Tuple tuple;

<<<<<<< HEAD
		tuple = super.unbind(object, "title", "nick", "message");
		final String lang = super.getRequest().getLocale().getLanguage();
		tuple.put("moment", this.scService.translateDate(object.getMoment(), lang));
=======
<<<<<<<< HEAD:src/main/java/acme/features/any/course/AnyCourseListService.java
		tuple = super.unbind(object, "code", "title", "retailPrice");
========
		tuple = super.unbind(object, "title", "nick", "message");
		final String lang = super.getRequest().getLocale().getLanguage();
		tuple.put("moment", this.scService.translateDate(object.getMoment(), lang));
>>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2:src/main/java/acme/features/any/peep/AnyPeepListService.java
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2

		super.getResponse().setData(tuple);
	}

}
