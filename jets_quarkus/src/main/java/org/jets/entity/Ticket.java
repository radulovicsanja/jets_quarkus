package org.jets.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    private Long id;

    private Long passengerId; // ID putnika koji je kupio kartu

    private Long flightId; // ID leta na koji se karta odnosi

    private double price; // cijena karte

    private LocalDateTime reservationDate; // datum rezervacije

    public Ticket() {}

    public Ticket(Long passengerId, Long flightId, double price, LocalDateTime reservationDate) {
        this.passengerId = passengerId;
        this.flightId = flightId;
        this.price = price;
        this.reservationDate = reservationDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPassengerId() { return passengerId; }
    public void setPassengerId(Long passengerId) { this.passengerId = passengerId; }

    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public LocalDateTime getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }
}