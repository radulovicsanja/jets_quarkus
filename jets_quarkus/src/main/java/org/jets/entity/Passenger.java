package org.jets.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

// Passenger -> Ticket (manyToMany)
//Passenger -> Flight (manyToMany)
@Entity
public class Passenger {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    private Long id;
    private String firstName;

    private String lastName;

    private String passportNumber;

    private List<Ticket> tickets;

    private List<Flight> flights;
}