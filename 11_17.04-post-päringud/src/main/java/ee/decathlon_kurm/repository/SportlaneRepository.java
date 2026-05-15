package ee.decathlon_kurm.repository;

import ee.decathlon_kurm.entity.Sportlane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportlaneRepository extends JpaRepository<Sportlane, Long> {

    Page<Sportlane> findByRiik(String riik, Pageable pageable);
}