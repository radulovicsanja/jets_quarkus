package org.jets.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.jets.entity.Flight;
import org.jets.entity.Passenger;
import org.jets.entity.Ticket;
import org.jets.exception.FlightException;

import java.time.LocalDateTime;
import java.util.List;

@Dependent
public class FlightService {

    @Inject
    private EntityManager em;

    List<Flight> flights = em.createQuery(
            "SELECT f FROM Flight f LEFT JOIN FETCH f.tickets", Flight.class
    ).getResultList();

    @Transactional
    public Flight createFlight(Flight flight) throws FlightException {
        if (flight == null) {
            throw new FlightException("Flight nije proslijeđen");
        }
        if (flight.getFlightNumber() == null || flight.getFlightNumber().isEmpty()) {
            throw new FlightException("Broj leta je prazan");
        }
        if (flight.getDepartureAirportId() == null) {
            throw new FlightException("Aerodrom polaska nije postavljen");
        }
        if (flight.getArrivalAirportId() == null) {
            throw new FlightException("Aerodrom dolaska nije postavljen");
        }

        if(flight.getTickets() != null) {
            for(Ticket ticket : flight.getTickets()) {
                ticket.setFlight(flight);
                // provjeri da li je passenger u bazi
                if(ticket.getPassenger() != null && ticket.getPassenger().getId() != null) {
                    ticket.setPassenger(em.find(Passenger.class, ticket.getPassenger().getId()));
                }
            }
        }

        return em.merge(flight);
    }

    @Transactional
    public List<Flight> getAllFlights() throws FlightException {
        List<Flight> flights = em.createQuery("SELECT f FROM Flight f", Flight.class)
                .getResultList();

        if (flights.isEmpty()) {
            throw new FlightException("Nema letova.");
        }
        return flights;
    }

    public List<Flight> getFlightsByNumber(String flightNumber) {
        return em.createQuery("SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber", Flight.class)
                .setParameter("flightNumber", flightNumber)
                .getResultList();
    }

    public List<Flight> getFlightsByDeparture(Long id) {
        return em.createNamedQuery(Flight.GET_FLIGHTS_BY_DEPARTURE, Flight.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Flight> getFlightsByArrival(Long arrivalAirportId) {
        return em.createQuery("SELECT f FROM Flight f WHERE f.arrivalAirportId = :airportId", Flight.class)
                .setParameter("airportId", arrivalAirportId)
                .getResultList();
    }


}