package org.jets.resource;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jets.entity.Airport;
import org.jets.entity.CurrencyResponse;
import org.jets.rest.client.CurrencyApi;

@Path("/currencyConversion")
public class CurrencyResponseResource {

    @Inject
    @RestClient
    CurrencyApi currencyApi;

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("/test")
    public String test() {
        System.out.println("RADI TEST");
        return "OK";
    }

    @GET
    @Path("/{airportId}/{from}/{to}/{value}")
    @Transactional
    public Response convert(
            @QueryParam("airportId") Long airportId,
            @QueryParam("from") String from,
            @QueryParam("to") String to,
            @QueryParam("value") double value
    ) {

        Airport airport = em.find(Airport.class, airportId);

        if (airport == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Airport not found")
                    .build();
        }

        CurrencyResponse currencyResponses = currencyApi.getRate(from, to);

        double converted = value * currencyResponses.getRate();

        currencyResponses.setValue(value);
        currencyResponses.setConvertedValue(converted);

        airport.getCurrencyResponses().add(currencyResponses);

        // cuvanje
        em.merge(currencyResponses);

        return Response.ok(currencyResponses).build();
    }
}