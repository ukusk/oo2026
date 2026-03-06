package ee.kt_kiirused.repository;

import ee.kt_kiirused.entity.KiirusMiilid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KiirusMiilidRepository extends JpaRepository<KiirusMiilid, Long> {
}