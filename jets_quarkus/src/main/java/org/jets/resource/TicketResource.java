package org.jets.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jets.entity.Ticket;
import org.jets.exception.FlightException;
import org.jets.exception.TicketException;
import org.jets.service.TicketService;

import java.util.List;

@Path("/ticket")
public class TicketResource{

    @Inject
    private TicketService ticketService;


    @POST
    @Path("/addTicket")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTicket(Ticket ticket){
        try {
            ticketService.createTicket(ticket);
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
        return Response.ok().build();
    }

    @GET
    @Path("/getAllTickets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTickets(){
        List<Ticket> tickets = ticketService.getAllTickets();
        return Response.ok(tickets).build();
    }

    // QueryParam
    @GET
    @Path("/getTicketsByFlight")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicketsByFlight(@QueryParam("flightId") Long flightId) {
        List<Ticket> tickets = ticketService.getTicketsByFlightId(flightId);
        if (tickets.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("Nema karata za dati let").build();
        }
        return Response.ok(tickets).build();
    }

    // QueryParam za passenger
    @GET
    @Path("/getByPassenger")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicketsByPassenger(@QueryParam("passengerId") Long id){
        List<Ticket> tickets = ticketService.getTicketsByPassengerId(id);
        if(tickets.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("Nema karata za datog putnika").build();
        }
        return Response.ok(tickets).build();
    }

    // QueryParam za seat
    @GET
    @Path("/getBySeat")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicketsBySeat(@QueryParam("seatId") Long id){
        List<Ticket> tickets = ticketService.getTicketsBySeatId(id);
        if(tickets.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("Nema karata za dato sjediste").build();
        }
        return Response.ok(tickets).build();
    }
}