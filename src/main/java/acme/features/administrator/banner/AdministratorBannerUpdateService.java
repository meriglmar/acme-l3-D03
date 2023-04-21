/*
 * AdministratorCompanyUpdateService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AdministratorBannerUpdateService extends AbstractService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorBannerRepository repo;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Banner object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repo.findOneBannerById(id);

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

		this.repo.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "moment", "startDatePeriod", "endDatePeriod", "imageLink", "eslogan", "docLink");

		super.getResponse().setData(tuple);
	}

}
