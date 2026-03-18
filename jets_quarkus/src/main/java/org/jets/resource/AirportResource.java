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
    @Path("/addAirport")
    public String addAirport(Airport airport) {
        airportService.createAirport(airport);
        return "Airport added successfully";
    }

    @GET
    @Path("/getAllAirports")
    public Response getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return Response.ok().entity(airports).build();
    }
}