package ee.kt_kiirused.repository;

import ee.kt_kiirused.entity.Kiirus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KiirusRepository extends JpaRepository<Kiirus, Long> {
}