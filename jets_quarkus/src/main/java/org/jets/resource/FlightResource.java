package org.jets.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jets.entity.Flight;
import org.jets.entity.Passenger;
import org.jets.exception.FlightException;
import org.jets.service.FlightService;

import java.time.LocalDateTime;
import java.util.List;

@Path("/flight")
public class FlightResource {

    @Inject
    private FlightService flightService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addFlight")
    public Response addFlight(Flight flight) {
        try {
            flightService.createFlight(flight);
        } catch (FlightException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllFlights")
    public Response getAllFlights() {
        List<Flight> flights;
        try {
            flights = flightService.getAllFlights();
        } catch (FlightException e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(flights).build();
    }

    @GET
    @Path("/getFlightsByNumber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlightsByNumber(@QueryParam("flightNumber") String flightNumber) {
        List<Flight> flights = flightService.getFlightsByNumber(flightNumber);
        return Response.ok().entity(flights).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getFlightsByDeparture")
    public Response getFlightsByDeparture(@QueryParam("airportId") Long id) {
        List<Flight> flights = flightService.getFlightsByDeparture(id);
        return Response.ok(flights).build();
    }

    @GET
    @Path("/getFlightsByArrival")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlightsByArrival(@QueryParam("airportId") Long arrivalAirportId) {
        List<Flight> flights = flightService.getFlightsByArrival(arrivalAirportId);
        return Response.ok().entity(flights).build();
    }


}