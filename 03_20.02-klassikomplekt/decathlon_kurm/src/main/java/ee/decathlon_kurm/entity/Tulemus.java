package ee.decathlon_kurm.entity;

import jakarta.persistence.*;

@Entity
public class Tulemus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ala;     // nt 100m, kaugushüpe jne
    private int punktid;    // punktid selle ala eest

    @ManyToOne
    @JoinColumn(name = "sportlane_id")
    private Sportlane sportlane;

    public Tulemus() {}

    public Tulemus(String ala, int punktid, Sportlane sportlane) {
        this.ala = ala;
        this.punktid = punktid;
        this.sportlane = sportlane;
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