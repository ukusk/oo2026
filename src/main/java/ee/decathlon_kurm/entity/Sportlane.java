package ee.decathlon_kurm.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Sportlane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nimi;

    @OneToMany(mappedBy = "sportlane", cascade = CascadeType.ALL)
    private List<Tulemus> tulemused;

    public Sportlane() {}

    public Sportlane(String nimi) {
        this.nimi = nimi;
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

    public List<Tulemus> getTulemused() {
        return tulemused;
    }

    public void setTulemused(List<Tulemus> tulemused) {
        this.tulemused = tulemused;
    }
}