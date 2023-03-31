
package acme.features.administrator.banner;

import java.time.Duration;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;

@Service
public class AdministratorBannerCreateService extends AbstractService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorBannerRepository repository;

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
		Banner object;
		Date moment;

		moment = MomentHelper.getCurrentMoment();

		object = new Banner();
		object.setMoment(moment);

		object.setStartPeriod(moment);
		object.setFinPeriod(moment);
		object.setImageLink("");
		object.setEslogan("");
		object.setDocLink("");

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Banner object) {
		assert object != null;

		super.bind(object, "moment", "startPeriod", "finPeriod", "imageLink", "eslogan", "docLink");
	}

	@Override
	public void validate(final Banner object) {
		assert object != null;

		boolean status;
		final boolean status2;

		if (!super.getBuffer().getErrors().hasErrors("startPeriod") && !super.getBuffer().getErrors().hasErrors("endPeriod")) {
			final Duration sevenDays = Duration.ofDays(7);
			status = object.periodOfTime().compareTo(sevenDays) > 0;
			status2 = object.getStartPeriod().compareTo(object.getMoment()) > 0;
			super.state(status, "finPeriod", "administrator.banner.status.error");
			super.state(status2, "startPeriod", "administrator.banner.status.error2");
		}

	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

		Date moment;

		moment = MomentHelper.getCurrentMoment();
		object.setMoment(moment);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "moment", "startPeriod", "finPeriod", "imageLink", "eslogan", "docLink");
		//		tuple.put("confirmation", true);
		//		tuple.put("readonly", true);

		super.getResponse().setData(tuple);
	}

}
