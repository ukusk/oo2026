package ee.uku.veateated.repository;

import ee.uku.veateated.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}