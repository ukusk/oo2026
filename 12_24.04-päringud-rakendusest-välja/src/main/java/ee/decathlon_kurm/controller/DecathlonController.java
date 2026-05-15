package ee.decathlon_kurm.controller;

import ee.decathlon_kurm.dto.Asukoht;
import ee.decathlon_kurm.dto.Kohtunik;
import ee.decathlon_kurm.entity.Sportlane;
import ee.decathlon_kurm.entity.Tulemus;
import ee.decathlon_kurm.repository.SportlaneRepository;
import ee.decathlon_kurm.repository.TulemusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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
                .orElseThrow(() -> new RuntimeException("Sportlast ei leitud!"));

        if (tulemus.getPunktid() < 0) {
            throw new RuntimeException("Punktid ei tohi olla negatiivsed!");
        }

        tulemus.setSportlane(sportlane);

        return tulemusRepository.save(tulemus);
    }

    // ➤ Tagasta sportlase kogupunktid
    @GetMapping("/sportlased/{id}/kogusumma")
    public int kogusumma(@PathVariable Long id) {

        Sportlane sportlane = sportlaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sportlast ei leitud!"));

        return sportlane.getTulemused()
                .stream()
                .mapToInt(Tulemus::getPunktid)
                .sum();
    }

    // ➤ Kõik sportlased + pagination + filter + sort
    @GetMapping("/sportlased")
    public Page<Sportlane> koikSportlased(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String riik,
            @RequestParam(defaultValue = "desc") String suund
    ) {

        // Sorteerib nime järgi
        Sort sort = Sort.by("nimi");

        if (suund.equalsIgnoreCase("asc")) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        // Filter riigi järgi
        if (riik != null && !riik.isEmpty()) {
            return sportlaneRepository.findByRiik(riik, pageable);
        }

        // Kõik sportlased
        return sportlaneRepository.findAll(pageable);
    }

    // MOCKAPI KOHTUNIKUD
    @GetMapping("/kohtunikud")
    public List<Kohtunik> saaKohtunikud() {

        RestTemplate restTemplate = new RestTemplate();

        Kohtunik[] kohtunikud = restTemplate.getForObject(
                "https://6a067913c83ba8ad9b3d82ee.mockapi.io/kohtunikud",
                Kohtunik[].class
        );

        return Arrays.asList(kohtunikud);
    }

    // MOCKAPI ASUKOHAD
    @GetMapping("/asukohad")
    public List<Asukoht> saaAsukohad() {

        RestTemplate restTemplate = new RestTemplate();

        Asukoht[] asukohad = restTemplate.getForObject(
                "https://6a067913c83ba8ad9b3d82ee.mockapi.io/asukohad",
                Asukoht[].class
        );

        return Arrays.asList(asukohad);
    }
}