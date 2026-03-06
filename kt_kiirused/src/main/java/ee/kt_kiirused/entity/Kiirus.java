package ee.kt_kiirused.entity;

import jakarta.persistence.*;

@Entity
public class Kiirus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    public Long getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }
}