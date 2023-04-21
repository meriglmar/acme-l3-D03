
package acme.features.assistant.tutorial.session;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.sessions.Session;
import acme.entities.tutorials.Tutorial;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

public class AssistantTutorialUpdateService extends AbstractService<Assistant, Session> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantTutorialSessionRepository repository;

	// AbstractService<Assistant, Session> ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int sessionId;
		Tutorial tutorial;

		sessionId = super.getRequest().getData("id", int.class);
		tutorial = this.repository.findTutorialBySessionId(sessionId);
		status = tutorial != null && super.getRequest().getPrincipal().hasRole(tutorial.getAssistant()) && tutorial.isDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Session object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findSessionById(id);

		super.getBuffer().setData(object);

	}

	@Override
	public void bind(final Session object) {
		assert object != null;

		super.bind(object, "title", "abstractSession", "isTheorySession", "initTimePeriod", "finishTimePeriod", "link");
	}

	@Override
	public void validate(final Session object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("initTimePeriod")) {
			Date minimumInitTime;

			minimumInitTime = MomentHelper.deltaFromCurrentMoment(1, ChronoUnit.DAYS);
			super.state(MomentHelper.isAfter(object.getInitTimePeriod(), minimumInitTime), "initTimePeriod", "assistant.session.form.error.too-close");
		}

		if (!super.getBuffer().getErrors().hasErrors("finishTimePeriod")) {
			boolean maximumFinishTimeOneHour;
			boolean maximumFinishTimeFiveHours;

			maximumFinishTimeOneHour = MomentHelper.isLongEnough(object.getInitTimePeriod(), object.getFinishTimePeriod(), 1, ChronoUnit.HOURS);
			maximumFinishTimeFiveHours = MomentHelper.isLongEnough(object.getInitTimePeriod(), object.getFinishTimePeriod(), 6, ChronoUnit.HOURS);

			// Calculamos la duracion de la sesion -> 1 hora como minimo
			super.state(!maximumFinishTimeOneHour, "finishTimePeriod", "assistant.session.form.error.too-little");

			// Calculamos la duracion de la sesion -> 5 horas como maximo
			super.state(maximumFinishTimeFiveHours, "finishTimePeriod", "assistant.session.form.error.too-big");
		}
	}

	@Override
	public void perform(final Session object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Session object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "abstractSession", "isTheorySession", "initTimePeriod", "finishTimePeriod", "link");
		tuple.put("tutorialId", object.getTutorial().getId());
		tuple.put("draftMode", object.getTutorial().isDraftMode());

		super.getResponse().setData(tuple);
	}

}
