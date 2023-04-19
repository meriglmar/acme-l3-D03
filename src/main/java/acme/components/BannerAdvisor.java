
package acme.components;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import acme.entities.banners.Banner;

@ControllerAdvice
public class BannerAdvisor {

	@Autowired
	protected BannerRepository repository;


	@ModelAttribute("banner")
	public Banner getAdvertisement() {
		Banner result;

		try {
			result = this.repository.findRandomBanners(new Date());
		} catch (final Throwable oops) {
			result = null;
		}

		return result;
	}
}
