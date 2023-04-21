
package acme.features.auditor.audit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditingRecords.AuditingRecord;
import acme.entities.auditingRecords.TypeMark;
import acme.entities.audits.Audit;
<<<<<<< HEAD
import acme.entities.courses.Course;
=======
import acme.entities.course.Course;
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
import acme.framework.repositories.AbstractRepository;
import acme.roles.Auditor;

@Repository
public interface AuditorAuditRepository extends AbstractRepository {

	@Query("select a from Audit a where a.id = :id")
	Audit findAuditById(int id);

	@Query("select a from Audit a where a.auditor.userAccount.id = :id")
	Collection<Audit> findAuditsByAuditorId(int id);

	@Query("select a from Audit a where a.code = :code")
	Audit findAuditByCode(String code);

	@Query("select ar from AuditingRecord ar where ar.audit = :audit")
	Collection<AuditingRecord> findAuditingRecordsByAudit(Audit audit);

	@Query("select a from Auditor a where a.id = :id")
	Auditor findAuditorById(int id);

	@Query("select c from Course c where c.id = :id")
	Course findCourseById(int id);

	@Query("select ar.mark from AuditingRecord ar where ar.audit.id = :id")
	Collection<TypeMark> findMarksByAuditId(int id);

	@Query("select c from Course c where c.draftMode = false")
	Collection<Course> findPublishedCourses();

	@Query("select c from Course c")
	Collection<Course> findCourses();

<<<<<<< HEAD
}
=======
}
>>>>>>> 0610eda9b76993595f97f80f44904a32fb5ce8e2
