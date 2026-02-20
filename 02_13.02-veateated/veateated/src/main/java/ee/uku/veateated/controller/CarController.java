package ee.uku.veateated.controller;

import ee.uku.veateated.entity.Car;
import ee.uku.veateated.repository.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @GetMapping
    public List<Car> getCars() {
        return carRepository.findAll();
    }


    @PostMapping
    public String addCar(@RequestBody Car car) {

        if (car.getBrand() == null || car.getBrand().isEmpty())
            return "Viga: mark puudub";

        if (car.getModel() == null || car.getModel().isEmpty())
            return "Viga: mudel puudub";

        if (car.getYear() < 1886)
            return "Viga: aasta ei saa olla väiksem kui 1886";

        if (car.getPrice() <= 0)
            return "Viga: hind peab olema positiivne";

        if (car.getYear() > 2025)
            return "Viga: auto ei saa olla tulevikust";

        carRepository.save(car);
        return "Auto lisatud!";
    }
}