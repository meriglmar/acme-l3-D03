
package acme.features.any.banner;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;

@Service
public class AnyBannerShowService extends AbstractService<Any, Banner> {

	@Autowired
	protected AnyBannerRepository bannerRepository;


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
		List<Banner> objects;
		Date currentMoment;

		currentMoment = MomentHelper.getCurrentMoment();
		objects = this.bannerRepository.findActiveBanners(currentMoment);
		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "moment", "startPeriod", "finPeriod", "imageLink", "eslogan", "docLink");

		super.getResponse().setData(tuple);
	}

	//	public Banner getRandomActiveBanner() {
	//		final List<Banner> activeBanners = this.bannerRepository.findActiveBanners(new Date());
	//		if (activeBanners.isEmpty())
	//			return null;
	//		final int randomIndex = new Random().nextInt(activeBanners.size());
	//		return activeBanners.get(randomIndex);
	//	}

}
