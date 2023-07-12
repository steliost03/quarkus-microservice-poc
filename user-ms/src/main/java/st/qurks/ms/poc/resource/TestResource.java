package st.qurks.ms.poc.resource;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/test")
public class TestResource {

    private static final Logger logger = LoggerFactory.getLogger(TestResource.class);

    @POST
    @Path("/echo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<String> echoJsonBody(String jsonBody) {
        logger.info("json input echo = {}", jsonBody);
        return Uni.createFrom().item(jsonBody);
    }
}
