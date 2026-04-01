package org.jets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Passenger {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String passportNumber;

   // Veze:
    @JsonIgnore
    @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY)
    private List<Ticket> tickets; //putnik moze imati vise karata

    public Passenger() {}


    @Override
    public String toString() {
        return "FirstName: " + firstName + ", LastName: " + lastName + ", PassportNumber: " + passportNumber;
    }
}