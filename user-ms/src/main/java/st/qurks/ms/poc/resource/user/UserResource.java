package st.qurks.ms.poc.resource.user;

import st.qurks.ms.poc.logging.DefaultUserMSLogging;
import st.qurks.ms.poc.model.UserRequest;
import st.qurks.ms.poc.model.UserResponse;
import st.qurks.ms.poc.service.UserService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;

import static st.qurks.ms.poc.util.SwaggerConstants.*;

/**
 * RESTEASY Reactive automatically handles the subscription of the Uni/Multi transformations,
 * so an explicit subscription is not required by default.
 */
@Path("/api/v1/user")
@DefaultUserMSLogging
public class UserResource {

    @Inject
    UserService userService;

    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = USER_CREATED_SUCCESS),
            @APIResponse(responseCode = "500", description = MONGO_TECH_ERROR)
    })
    @ResponseStatus(201)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<UserResponse> createUser(
            @Valid UserRequest user) {
        return userService.createUser(user);
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = OP_SUCCESS),
            @APIResponse(responseCode = "404", description = USER_NOT_FOUND),
            @APIResponse(responseCode = "500", description = MONGO_TECH_ERROR)
    })
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<UserResponse> findUserById(
            @RestPath String id) {
        return userService.findUser(id);
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = OP_SUCCESS),
            @APIResponse(responseCode = "500", description = MONGO_TECH_ERROR)
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<UserResponse> findUsers(
            @RestQuery("id") List<String> ids) {
        return userService.findUsers(ids);
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = OP_SUCCESS),
            @APIResponse(responseCode = "404", description = USER_NOT_FOUND),
            @APIResponse(responseCode = "500", description = MONGO_TECH_ERROR)
    })
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<UserResponse> deleteUser(
            @RestPath String id) {
        return userService.deleteUser(id);
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = OP_SUCCESS),
            @APIResponse(responseCode = "500", description = MONGO_TECH_ERROR)
    })
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<UserResponse> deleteUsers(
            @RestQuery("id") List<String> ids) {
        return userService.deleteUsers(ids);
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = OP_SUCCESS),
            @APIResponse(responseCode = "500", description = MONGO_TECH_ERROR)
    })
    @PUT
    @Path("/upsert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<UserResponse> upsertUser(
            @Valid UserRequest user) {
        String id = user.getId();
        return userService.upsertUser(id, user);
    }
}
