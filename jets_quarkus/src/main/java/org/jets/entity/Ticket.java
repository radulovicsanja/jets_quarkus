package org.jets.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passengerid")
    private Passenger passenger; //karta pripada jednom putniku

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flightid")
    private Flight flight; //karta pripada jednom letu

    @OneToOne
    @JoinColumn(name = "seatid")
    @JsonIgnore
    private Seat seat;




    @JsonIgnore
    private double price;
@JsonIgnore
    private LocalDateTime reservationDate;


    public Ticket() {}

    public Ticket(Passenger passenger, Flight flight, Seat seat, double price, LocalDateTime reservationDate) {
        this.passenger = passenger;
        this.flight = flight;
        this.seat = seat;
        this.price = price;
        this.reservationDate = reservationDate;
    }



}