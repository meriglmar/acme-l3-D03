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

package acme.features.administrator.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AdministratorOfferUpdateService extends AbstractService<Administrator, Offer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorOfferRepository repository;

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
		Offer object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneOfferById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Offer object) {
		assert object != null;

		super.bind(object, "instantiationMoment", "heading", "summary", "startDay", "lastDay", "price", "link");
	}

	@Override
	public void validate(final Offer object) {
		assert object != null;
		if (!super.getBuffer().getErrors().hasErrors("startDay") && !super.getBuffer().getErrors().hasErrors("lastDay")) {
			final boolean dayAfterInstantiation = object.getStartDay().getTime() - object.getInstantiationMoment().getTime() >= 86400000;
			final boolean oneWeek = object.getLastDay().getTime() - object.getStartDay().getTime() >= 604800000;
			super.state(dayAfterInstantiation, "startDay", "administrator.offer.post.day-after-instantiation");
			super.state(oneWeek, "lastDay", "administrator.offer.post.one-week");
		}
		if (!super.getBuffer().getErrors().hasErrors("price")) {
			final boolean pricePositive = object.getPrice().getAmount() >= 0.0;
			super.state(pricePositive, "price", "administrator.offer.post.price-positive");
		}
	}

	@Override
	public void perform(final Offer object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Offer object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "instantiationMoment", "heading", "summary", "startDay", "lastDay", "price", "link");

		super.getResponse().setData(tuple);
	}

}
