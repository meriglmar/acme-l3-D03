
package acme.features.administrator.banner;

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
		final Date moment = MomentHelper.getCurrentMoment();
		object = new Banner();
		object.setMoment(moment);
		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Banner object) {
		assert object != null;

		super.bind(object, "moment", "startDatePeriod", "endDatePeriod", "imageLink", "eslogan", "docLink");
	}

	@Override
	public void validate(final Banner object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("startDatePeriod") && !super.getBuffer().getErrors().hasErrors("endDatePeriod")) {
			boolean validMoment;
			validMoment = object.getMoment().compareTo(object.getStartDatePeriod()) < 0;
			super.state(validMoment, "startDatePeriod", "administrator.banner.status.error.current");
			boolean validPeriod;
			validPeriod = object.getEndDatePeriod().getTime() - object.getStartDatePeriod().getTime() >= 604800000;
			super.state(validPeriod, "endDatePeriod", "administrator.banner.status.error.week");
		}
	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "moment", "startDatePeriod", "endDatePeriod", "imageLink", "eslogan", "docLink");

		super.getResponse().setData(tuple);
	}

}
