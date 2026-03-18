package org.jets.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Seat {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    private Long id;
    private String seatNumber;     // npr. 12A

    private String seatClass;      // economy, business

    private String seatPosition;   // window, middle, aisle

    private boolean reserved;
}