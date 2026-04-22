package org.jets.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient(configKey = "ip-api")
public interface IpApi {

    @GET
    String getIp();
}