package ee.proovikt.proovikt.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Film film;

    private LocalDate rentDate;

    private LocalDate returnDate;

    private double price;

    private double lateFee;

    public Long getId() {
        return id;
    }

    public Film getFilm() {
        return film;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public double getPrice() {
        return price;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }
}