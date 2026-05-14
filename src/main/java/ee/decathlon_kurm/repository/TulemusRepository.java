package ee.decathlon_kurm.repository;

import ee.decathlon_kurm.entity.Tulemus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TulemusRepository extends JpaRepository<Tulemus, Long> {
}