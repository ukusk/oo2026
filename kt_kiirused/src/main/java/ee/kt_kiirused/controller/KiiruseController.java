package ee.kt_kiirused.controller;

import ee.kt_kiirused.entity.Kiirus;
import ee.kt_kiirused.entity.KiirusMiilid;
import ee.kt_kiirused.repository.KiirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import ee.kt_kiirused.repository.KiirusMiilidRepository;

@RestController
public class KiiruseController {

    @Autowired
    private KiirusRepository kiirusRepository;

    //3s punkt
    @Autowired
    private KiirusMiilidRepository kiirusMiilidRepository;

    // lisa kiirus
    @PostMapping("/kiirus")
    public String addSpeed(@RequestBody Kiirus kiirus){

        if(kiirus.getValue() < 0){
            return "Kiirus ei saa olla negatiivne";
        }

        if(kiirus.getValue() > 10000){
            return "Kiirus liiga suur";
        }

        kiirusRepository.save(kiirus);

        return "Salvestatud";
    }


    // vt kõiki
    @GetMapping("/kiirus")
    public List<Kiirus> getAll(){
        return kiirusRepository.findAll();
    }

    // vt keskmist
    @GetMapping("/kiirus/keskmine")
    public double keskmine() {

        List<Kiirus> kiirused = kiirusRepository.findAll();

        int sum = 0;

        for(Kiirus k : kiirused){
            sum += k.getValue();
        }

        return (double) sum / kiirused.size();
    }

    // vt mph to km conversionit
    // salvestab miilid oma id-ga
    @GetMapping("/kiirus/mph")
    public List<Double> mph() {

        List<Kiirus> kiirused = kiirusRepository.findAll();

        List<Double> mphList = new ArrayList<>();

        for(Kiirus k : kiirused){

            double mph = k.getValue() * 0.621371;

            KiirusMiilid km = new KiirusMiilid();
            km.setValue(mph);

            kiirusMiilidRepository.save(km);

            mphList.add(mph);
        }

        return mphList;
    }


    // suurendab +1 võrra
    @PutMapping("/kiirus/increase")
    public List<Kiirus> increase(){

        List<Kiirus> kiirused = kiirusRepository.findAll();

        for(Kiirus k : kiirused){
            k.setValue(k.getValue() + 1);
            kiirusRepository.save(k);
        }

        return kiirused;
    }

}

