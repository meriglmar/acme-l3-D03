
package acme.features.authenticated.audit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditingRecords.TypeMark;
import acme.entities.audits.Audit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditRepository extends AbstractRepository {

	@Query("select a from Audit a where a.course.id = :masterId")
	Collection<Audit> findAuditByCourseId(int masterId);

	@Query("select a from Audit a where a.id = :id")
	Audit findAuditById(int id);

	@Query("select ar.mark from AuditingRecord ar where ar.audit.id = :id")
	Collection<TypeMark> findMarksByAuditId(int id);
}
