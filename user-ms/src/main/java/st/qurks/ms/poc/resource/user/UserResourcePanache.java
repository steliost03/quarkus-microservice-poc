package st.qurks.ms.poc.resource.user;

import st.qurks.ms.poc.logging.DefaultUserMSLogging;
import st.qurks.ms.poc.model.UserRequest;
import st.qurks.ms.poc.model.UserResponse;
import st.qurks.ms.poc.service.UserServicePanache;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import static st.qurks.ms.poc.util.SwaggerConstants.*;

/**
 * RESTEASY Reactive automatically handles the subscription of the Uni/Multi transformations,
 * so an explicit subscription is not needed by default.
 */
@Path("/api/v2/user")
@DefaultUserMSLogging
public class UserResourcePanache {

    @Inject
    UserServicePanache userServicePanache;

    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = USER_CREATED_SUCCESS),
            @APIResponse(responseCode = "500", description = MONGO_TECH_ERROR)
    })
    @ResponseStatus(201)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<UserResponse> createUser(
            UserRequest user) {
        return userServicePanache.createUser(user);
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
        return userServicePanache.findUserById(id);
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = OP_SUCCESS),
            @APIResponse(responseCode = "500", description = MONGO_TECH_ERROR)
    })
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<UserResponse> updateUser(
            @Valid UserRequest user) {
        return userServicePanache.updateUser(user);
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = OP_SUCCESS),
            @APIResponse(responseCode = "500", description = MONGO_TECH_ERROR)
    })
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<UserResponse> deleteUser(
            @RestPath String id) {
        return userServicePanache.deleteUserById(id);
    }
}
