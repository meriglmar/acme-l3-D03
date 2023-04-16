
package acme.features.company.practicum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.practicums.Practicum;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumCreateService extends AbstractService<Company, Practicum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumRepository practicumRepository;

	//	@Autowired
	//	protected AuxiliarService				auxiliarService;

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
		Practicum object;
		Company company;

		company = this.practicumRepository.findCompanyById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Practicum();
		object.setDraftMode(true);
		object.setCompany(company);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Practicum object) {
		assert object != null;

		int courseId;
		Course course;

		courseId = super.getRequest().getData("course", int.class);
		course = this.practicumRepository.findCourseById(courseId);

		super.bind(object, "code", "title", "abstract$", "goals");
		object.setCourse(course);
	}

	@Override
	public void validate(final Practicum object) {
		assert object != null;
		//
		//		if (!super.getBuffer().getErrors().hasErrors("code"))
		//			super.state(this.repository.findPracticumByCode(object.getCode()) == null, "code", "company.practicum.form.error.code");
		//
		//		if (!super.getBuffer().getErrors().hasErrors("title"))
		//			super.state(this.auxiliarService.validateTextImput(object.getTitle()), "title", "company.practicum.form.error.spam");
		//
		//		if (!super.getBuffer().getErrors().hasErrors("abstract$"))
		//			super.state(this.auxiliarService.validateTextImput(object.getAbstract$()), "abstract$", "company.practicum.form.error.spam");
		//
		//		if (!super.getBuffer().getErrors().hasErrors("goals"))
		//			super.state(this.auxiliarService.validateTextImput(object.getGoals()), "goals", "company.practicum.form.error.spam");
	}

	@Override
	public void perform(final Practicum object) {
		assert object != null;

		this.practicumRepository.save(object);
	}

	@Override
	public void unbind(final Practicum object) {
		assert object != null;

		Collection<Course> courses;
		SelectChoices choices;
		Tuple tuple;

		courses = this.practicumRepository.findAllCourses();
		choices = SelectChoices.from(courses, "code", object.getCourse());

		tuple = super.unbind(object, "code", "title", "abstract$", "goals");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}

}