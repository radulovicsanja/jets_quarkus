package org.jets.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(
        name = Airport.GET_ALL_AIRPORTS,
        query = "SELECT a FROM Airport a"
)
public class Airport {

    public static final String GET_ALL_AIRPORTS = "Airport.getAllAirports";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String country;
    private String code; // npr. TGD, BEG, LHR

    @OneToMany(mappedBy = "departureAirport", cascade = CascadeType.ALL)
    private List<Flight> flights = new ArrayList<>();

    public Airport() {
    }

    public Airport(String name, String city, String country, String code) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.code = code;
    }

    // Getteri i setteri
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }

    // Pomoćne metode za dodavanje/uklanjanje letova
    public void addFlight(Flight flight) {
        flights.add(flight);
        flight.setDepartureAirport(this);
    }

    public void removeFlight(Flight flight) {
        flights.remove(flight);
        flight.setDepartureAirport(null);
    }
}