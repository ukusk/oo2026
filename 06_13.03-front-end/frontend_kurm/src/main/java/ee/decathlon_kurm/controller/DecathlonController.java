package ee.decathlon_kurm.controller;

import ee.decathlon_kurm.entity.Sportlane;
import ee.decathlon_kurm.entity.Tulemus;
import ee.decathlon_kurm.repository.SportlaneRepository;
import ee.decathlon_kurm.repository.TulemusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DecathlonController {

    @Autowired
    private SportlaneRepository sportlaneRepository;

    @Autowired
    private TulemusRepository tulemusRepository;


    // ➤ Lisa uus sportlane
    @PostMapping("/sportlased")
    public Sportlane lisaSportlane(@RequestBody Sportlane sportlane) {

        if (sportlane.getNimi() == null || sportlane.getNimi().isEmpty()) {
            throw new RuntimeException("Nimi ei tohi olla tühi!");
        }

        return sportlaneRepository.save(sportlane);
    }


    // ➤ Lisa tulemus sportlasele
    @PostMapping("/sportlased/{id}/tulemused")
    public Tulemus lisaTulemus(@PathVariable Long id,
                               @RequestBody Tulemus tulemus) {

        Sportlane sportlane = sportlaneRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sportlast ei leitud!"));

        if (tulemus.getPunktid() < 0) {
            throw new RuntimeException("Punktid ei tohi olla negatiivsed!");
        }

        tulemus.setSportlane(sportlane);

        return tulemusRepository.save(tulemus);
    }


    // ➤ Tagasta sportlase kogupunktid
    @GetMapping("/sportlased/{id}/kogusumma")
    public int kogusumma(@PathVariable Long id) {

        List<Tulemus> tulemused = tulemusRepository.findAll();

        int summa = 0;

        for (Tulemus t : tulemused) {

            if (t.getSportlane() != null &&
                    t.getSportlane().getId().equals(id)) {

                summa += t.getPunktid();
            }
        }

        return summa;
    }


    // ➤ Kõik sportlased
    @GetMapping("/sportlased")
    public List<Sportlane> koikSportlased() {

        return sportlaneRepository.findAll();
    }
}