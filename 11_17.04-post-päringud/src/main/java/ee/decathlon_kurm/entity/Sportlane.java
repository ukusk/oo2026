package ee.decathlon_kurm.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Sportlane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;

    // ➤ Riigi filter jaoks
    private String riik;

    @OneToMany(mappedBy = "sportlane", cascade = CascadeType.ALL)
    private List<Tulemus> tulemused;

    public Sportlane() {}

    public Sportlane(String nimi, String riik) {
        this.nimi = nimi;
        this.riik = riik;
    }

    public Long getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getRiik() {
        return riik;
    }

    public void setRiik(String riik) {
        this.riik = riik;
    }

    public List<Tulemus> getTulemused() {
        return tulemused;
    }

    public void setTulemused(List<Tulemus> tulemused) {
        this.tulemused = tulemused;
    }

    // ➤ Sorteerimise jaoks kogupunktid
    @Transient
    public int getKogupunktid() {

        if (tulemused == null) {
            return 0;
        }

        return tulemused.stream()
                .mapToInt(Tulemus::getPunktid)
                .sum();
    }
}