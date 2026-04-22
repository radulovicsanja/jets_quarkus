package org.jets.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    @JsonProperty("name")
    private String name;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;

    @JsonProperty("code")
    private String code; // npr. TGD, BEG, LHR

    @OneToMany(mappedBy = "departureAirport", fetch = FetchType.LAZY)
    private List<Flight> departureFlights;

    @OneToMany(mappedBy = "arrivalAirport", fetch = FetchType.LAZY)
    private List<Flight> arrivalFlights;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeResponse> timeResponses = new ArrayList<>();


    public Airport(String name, String city, String country, String code) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.code = code;
    }

    public Airport() {

    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public List<TimeResponse> getTimeResponses() {
        return timeResponses;
    }


}