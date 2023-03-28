
package acme.features.any.banner;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyBannerRepository extends AbstractRepository {

	@Query("SELECT b FROM Banner b WHERE b.startPeriod <= :now AND b.finPeriod >= :now")
	List<Banner> findActiveBanners(@Param("now") Date now);
}
