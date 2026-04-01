package org.jets.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.jets.entity.Airport;
import org.jets.service.AirportService;

@Path("/airport")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AirportResource {

    @Inject
    private AirportService airportService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addAirport")
    public Response addAirport(Airport airport) {
        System.out.println("Airport object received: " + airport);
        Airport savedAirport = airportService.createAirport(airport);
        return Response.ok(savedAirport).build();
    }

    @GET
    @Path("/getAllAirports")
    public Response getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return Response.ok().entity(airports).build();
    }
}