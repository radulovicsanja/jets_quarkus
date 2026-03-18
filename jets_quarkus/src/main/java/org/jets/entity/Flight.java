package org.jets.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Flight {

    @Id
    private Long id;

    private String flightNumber;

    private LocalDateTime dateTime;

    private Long departureAirportId; // ID aerodroma polaska

    private Long arrivalAirportId;   // ID aerodroma dolaska

    private List<Long> seatIds;      // lista ID-eva sjedista

    private List<Long> ticketIds;    // lista ID-eva karata

    private List<Long> passengerIds; // lista ID-eva putnika

    public Flight() {}

    public Flight(String flightNumber, LocalDateTime dateTime, Long departureAirportId, Long arrivalAirportId) {
        this.flightNumber = flightNumber;
        this.dateTime = dateTime;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public Long getDepartureAirportId() { return departureAirportId; }
    public void setDepartureAirportId(Long departureAirportId) { this.departureAirportId = departureAirportId; }

    public Long getArrivalAirportId() { return arrivalAirportId; }
    public void setArrivalAirportId(Long arrivalAirportId) { this.arrivalAirportId = arrivalAirportId; }

    public List<Long> getSeatIds() { return seatIds; }
    public void setSeatIds(List<Long> seatIds) { this.seatIds = seatIds; }

    public List<Long> getTicketIds() { return ticketIds; }
    public void setTicketIds(List<Long> ticketIds) { this.ticketIds = ticketIds; }

    public List<Long> getPassengerIds() { return passengerIds; }
    public void setPassengerIds(List<Long> passengerIds) { this.passengerIds = passengerIds; }

    public void setDepartureAirport(Airport airport) {
    }
}