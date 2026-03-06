package ee.kt_kiirused.entity;

import jakarta.persistence.*;

@Entity
public class KiirusMiilid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double value;

    public Long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(double value) {
        this.value = value;
    }
}