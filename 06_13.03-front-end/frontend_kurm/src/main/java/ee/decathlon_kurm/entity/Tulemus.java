package ee.decathlon_kurm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Tulemus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ala;

    private int punktid;

    @ManyToOne
    @JoinColumn(name = "sportlane_id")
    @JsonBackReference
    private Sportlane sportlane;

    public Tulemus() {
    }

    public Long getId() {
        return id;
    }

    public String getAla() {
        return ala;
    }

    public void setAla(String ala) {
        this.ala = ala;
    }

    public int getPunktid() {
        return punktid;
    }

    public void setPunktid(int punktid) {
        this.punktid = punktid;
    }

    public Sportlane getSportlane() {
        return sportlane;
    }

    public void setSportlane(Sportlane sportlane) {
        this.sportlane = sportlane;
    }
}