package org.jets.scheduler;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import io.quarkus.scheduler.Scheduled;
import org.jets.entity.Flight;


import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class SchedulerCancelTicket {

    @Inject
    EntityManager em;

    /**
     * Scheduler koji se pokreće svakih 10 minuta
     * i blokira rezervaciju karata 2h prije polijetanja
     */
    @Scheduled(every = "10s")
    @Transactional
    public void blockTicketsTwoHoursBefore() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threshold = now.plusHours(2);

        // Dohvati sve letove koji polijeću za manje od 2h
        List<Flight> flights = em.createQuery(
                        "SELECT f FROM Flight f WHERE f.dateTime <= :threshold", Flight.class)
                .setParameter("threshold", threshold)
                .getResultList();


        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            System.out.println("Flight " + flight.getFlightNumber() + " je unutar 2h do polijetanja - blokiran za nove rezervacije.");

        }
    }
}