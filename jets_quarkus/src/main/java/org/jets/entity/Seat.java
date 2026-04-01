package org.jets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String seatNumber;     // npr. 12A

    private String seatClass;      // economy, business

    private String seatPosition;   // window, middle, aisle

    private boolean reserved;

    @ManyToOne
    @JoinColumn(name = "flightid")
    private Flight flight;

    // Veza:
    @OneToOne(mappedBy = "seat", fetch = FetchType.LAZY)
    private Ticket ticket;


    public Seat() {}
}