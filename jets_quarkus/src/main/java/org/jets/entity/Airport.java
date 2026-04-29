package org.jets.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
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
   //priprema
    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeResponse> timeResponses = new ArrayList<>();

    //kolokvijum
    @OneToMany(cascade = CascadeType.ALL)
    private List<CurrencyResponse> currencyResponses = new ArrayList<>();



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

    public List<CurrencyResponse> getCurrencyResponses() {
        return currencyResponses;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public List<Flight> getDepartureFlights() {
        return departureFlights;
    }

    public List<Flight> getArrivalFlights() {
        return arrivalFlights;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDepartureFlights(List<Flight> departureFlights) {
        this.departureFlights = departureFlights;
    }

    public void setArrivalFlights(List<Flight> arrivalFlights) {
        this.arrivalFlights = arrivalFlights;
    }

    public void setTimeResponses(List<TimeResponse> timeResponses) {
        this.timeResponses = timeResponses;
    }



}