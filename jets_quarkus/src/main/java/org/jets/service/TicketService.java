package org.jets.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.jets.entity.Flight;
import org.jets.entity.Passenger;
import org.jets.entity.Seat;
import org.jets.entity.Ticket;
import org.jets.exception.FlightException;
import org.jets.exception.TicketException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Dependent
public class TicketService {

    @Inject
    private EntityManager em;

    @Transactional
    public Ticket createTicket(Ticket ticket) throws TicketException {
        if (ticket == null) {
            throw new TicketException("Ticket nije prosleđen");
        }

        // Flight
        if (ticket.getFlight() == null || ticket.getFlight().getId() == null) {
            throw new TicketException("Flight nije postavljen ili nema ID");
        }
        Flight flight = em.getReference(Flight.class, ticket.getFlight().getId());

        // SCeduler PROVJERA: zabrana kupovine 2h prije polijetanja
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, flight.getDateTime());
        if (duration.toHours() < 2) {
            throw new TicketException("Nije moguće rezervisati kartu manje od 2 sata prije polijetanja.");
        }

        // Passenger
        if (ticket.getPassenger() == null || ticket.getPassenger().getId() == null) {
            throw new TicketException("Passenger nije postavljen ili nema ID");
        }
        Passenger passenger = em.getReference(Passenger.class, ticket.getPassenger().getId());

        // Seat
        if (ticket.getSeat() == null || ticket.getSeat().getId() == null) {
            throw new TicketException("Seat nije postavljen ili nema ID");
        }
        Seat seat = em.getReference(Seat.class, ticket.getSeat().getId());

        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        ticket.setSeat(seat);

        if (ticket.getReservationDate() == null) {
            ticket.setReservationDate(LocalDateTime.now());
        }

        return em.merge(ticket);
    }

    public List<Ticket> getAllTickets(){
        return em.createQuery("SELECT t FROM Ticket t", Ticket.class)
                .getResultList();
    }

    @Transactional
    // Pretraga: po flight-u
    public List<Ticket> getTicketsByFlightId(Long flightId){
        return em.createQuery(
                        "SELECT t FROM Ticket t " +
                                "JOIN FETCH t.flight " +
                                "JOIN FETCH t.passenger " +
                                "JOIN FETCH t.seat " +
                                "WHERE t.flight.id = :id", Ticket.class)
                .setParameter("id", flightId)
                .getResultList();
    }

    // Pretraga: po passenger-u
    public List<Ticket> getTicketsByPassengerId(Long passengerId){
        return em.createQuery(
                        "SELECT t FROM Ticket t " +
                                "JOIN FETCH t.flight " +
                                "JOIN FETCH t.passenger " +
                                "JOIN FETCH t.seat " +
                                "WHERE t.passenger.id = :id", Ticket.class)
                .setParameter("id", passengerId)
                .getResultList();
    }

    // Pretraga: po sjedistu
    public List<Ticket> getTicketsBySeatId(Long seatId){
        return em.createQuery(
                        "SELECT t FROM Ticket t " +
                                "JOIN FETCH t.flight " +
                                "JOIN FETCH t.passenger " +
                                "JOIN FETCH t.seat " +
                                "WHERE t.seat.id = :id", Ticket.class)
                .setParameter("id", seatId)
                .getResultList();
    }
}