
package acme.components;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BannerRepository extends AbstractRepository {

	@Query("SELECT COUNT(b) FROM Banner b WHERE b.startDatePeriod <= :now AND b.endDatePeriod >= :now")
	int countActiveBanners(@Param("now") Date now);

	@Query("SELECT b FROM Banner b WHERE b.startDatePeriod <= :now AND b.startDatePeriod >= :now")
	List<Banner> findActiveBanners(@Param("now") Date now, Pageable pageable);

	default Banner findRandomBanners(final Date now) {
		Banner result = null;
		final int count = this.countActiveBanners(now);

		if (count > 0) {
			final int index = ThreadLocalRandom.current().nextInt(count);
			final Pageable pageable = PageRequest.of(index, 1);
			final List<Banner> banners = this.findActiveBanners(now, pageable);
			if (!banners.isEmpty())
				result = banners.get(0);
		}
		return result;
	}
}
