package demo.hao.api;

import com.codahale.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String defaultName;
    private final AtomicLong counter;

    private Set<String> names;

    public HelloWorldResource(String defaultName) {
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        names = new HashSet<>();
    }

    @GET
    @Timed
    public Response sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format("Hello, %s", name.orElse(defaultName));
        return Response
                .ok()
                .entity(new Saying(counter.incrementAndGet(), value))
                .build();
    }

    @GET
    @Path("/name/{name}")
    public Response getName(@PathParam("name") final String name) {
        if (names.contains(name)) {
            return Response
                    .ok()
                    .entity(name)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity(new Error("Unable to find name: " + name))
                .build();
    }

    @PUT
    public Response addName(@Valid Name name) {
        Response.ResponseBuilder responseBuilder;
        responseBuilder = !names.contains(name.getName())
                ? Response.status(Response.Status.CREATED)
                : Response.ok();

        names.add(name.getName());
        return responseBuilder.entity(name).build();
    }

    @DELETE
    @Path("/name/{name}")
    public Response deleteName(@PathParam("name") final String name) {
        if (names.contains(name)) {
            names.remove(name);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
