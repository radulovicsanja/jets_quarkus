package org.jets.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NamedQuery(
        name = "Flight.GET_FLIGHTS_BY_DEPARTURE",
        query = "SELECT f FROM Flight f WHERE f.departureAirportId = :id"
)
public class Flight {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static final String GET_FLIGHTS_BY_DEPARTURE = "Flight.GET_FLIGHTS_BY_DEPARTURE";

    private String flightNumber;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;
    private Long departureAirportId; // ID aerodroma polaska
    private Long arrivalAirportId;   // ID aerodroma dolaska


    @JsonIgnore
    // Veze
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departureairportid", insertable = false, updatable = false)
    private Airport departureAirport;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrivalairportid", insertable = false, updatable = false)
    private Airport arrivalAirport;
    @JsonIgnore
    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)    @JsonManagedReference
    private List<Ticket> tickets;



    public Flight() {}

    public Flight(String flightNumber, LocalDateTime dateTime, Long departureAirportId, Long arrivalAirportId) {
        this.flightNumber = flightNumber;
        this.dateTime = dateTime;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
    }


    @Override
    public String toString() {
        return "Flight number: " + flightNumber
                + "\nDate: " + dateTime
                + "\nDeparture airport: " + departureAirportId
                + "\nArrival airport: " + arrivalAirportId;
    }


}