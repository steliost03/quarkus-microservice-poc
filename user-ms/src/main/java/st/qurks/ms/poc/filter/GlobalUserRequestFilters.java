package st.qurks.ms.poc.filter;

import st.qurks.ms.poc.filter.interceptor.UserFilterLogging;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static st.qurks.ms.poc.util.Constants.CALLER_HEADER_NAME;

@UserFilterLogging
public class GlobalUserRequestFilters {

    private static final Logger logger = LoggerFactory.getLogger(GlobalUserRequestFilters.class);
    private static final String CALLER_NAME_DEFAULT_VALUE = "Anonymous Caller";

    /*
    addCallerNameHeader will be called first, because the fact that it is a 'preMatching'
    filter supersedes its priority number.

    On the rest of the request filters: lower priority number => will be executed first.
     */

    @ServerRequestFilter(preMatching = true, priority = 2)
    public void addCallerNameHeaderToReqIfNotExists(ContainerRequestContext requestContext) {
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        if (!headers.containsKey(CALLER_HEADER_NAME)) {
            logger.trace("'caller name' header does exist in the incoming request. Will add it with default value.");
            headers.add(CALLER_HEADER_NAME, CALLER_NAME_DEFAULT_VALUE);
        }
    }

    @ServerRequestFilter(priority = 500)
    public void justTestingThePriority() {
        logger.trace("hello from empty filter with priority 500!");
    }

    @ServerRequestFilter(priority = 1)
    public void logRequestDetails(ContainerRequestContext requestContext) {

        UriInfo uriInfo = requestContext.getUriInfo();
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        MultivaluedMap<String,String> pathParams = uriInfo.getPathParameters();

        logger.trace("INCOMING REQUEST");
        logger.trace("---");
        logger.trace("method = {}", requestContext.getMethod());
        logger.trace("URI = {}",uriInfo.getPath());
        logger.trace("headers = {}", requestContext.getHeaders());
        logger.trace("query parameters = {}", queryParams);
        logger.trace("path parameters = {}", pathParams);
    }
}
