package org.jets.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jets.entity.CurrencyResponse;

@Path("/api")
@RegisterRestClient(configKey = "currency-api")
public interface CurrencyApi {

    @GET
    @Path("/rates")
    @Produces(MediaType.APPLICATION_JSON)
    CurrencyResponse getRate(
            @QueryParam("from") String from,
            @QueryParam("to") String to
    );
}