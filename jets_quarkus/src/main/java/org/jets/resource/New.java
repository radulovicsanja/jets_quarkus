package org.jets.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@Path("/new")
public class New {

    @GET
    @Path("/test")
    public String test() {
        System.out.println("RADI TEST");
        return "OK";
    }
}
