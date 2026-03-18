package org.jets.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.jets.entity.Airport;

import java.util.List;

@Dependent
public class AirportService {

    @Inject
    private EntityManager em;

    @Transactional
    public Airport createAirport(Airport airport){
        return em.merge(airport); // insert ili update
    }

    public List<Airport> getAllAirports(){
        return em.createNamedQuery(Airport.GET_ALL_AIRPORTS, Airport.class)
                .getResultList();
    }
}