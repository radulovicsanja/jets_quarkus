package org.jets.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.jets.entity.TimeResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/time/current")
@RegisterRestClient(configKey = "time-api")
public interface TimeApi {

    @GET
    @Path("/ip")
    TimeResponse getTime(@QueryParam("ipAddress") String ip);
}