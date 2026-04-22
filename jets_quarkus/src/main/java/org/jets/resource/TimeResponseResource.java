package org.jets.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.jets.entity.TimeResponse;
import org.jets.rest.client.IpApi;
import org.jets.rest.client.TimeApi;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jets.entity.Airport;

@Path("/timezone")
@PermitAll
public class TimeResponseResource {

    @Inject
    @RestClient
    IpApi ipApi;

    @Inject
    @RestClient
    TimeApi timeApi;

    @PersistenceContext
    EntityManager em;


    @GET
    @Path("/test")
    public String test() {
        System.out.println("RADI TEST");
        return "OK";
    }

    @GET
    @Transactional
    @Path("/getTimezoneByIP")
    public Response getTimezoneByIP(@QueryParam("userId") Long userId) {
        try {

            Airport airport = em.find(Airport.class, userId);

            if (airport == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("User not found")
                        .build();
            }

            String ip = ipApi.getIp();
            System.out.println("IP: " + ip);

            TimeResponse timeResponse = timeApi.getTime(ip);
            System.out.println("TIME: " + timeResponse);

            airport.getTimeResponses().add(timeResponse);

            em.merge(airport);

            return Response.ok(timeResponse).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500)
                    .entity(e.getMessage())
                    .build();
        }
    }
}