package st.qurks.ms.poc.filter;

import st.qurks.ms.poc.filter.interceptor.UserFilterLogging;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.core.MultivaluedMap;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import static st.qurks.ms.poc.util.Constants.CALLER_HEADER_NAME;

@UserFilterLogging
public class GlobalUserResponseFilters {
    @ServerResponseFilter
    public void addCallerNameHeaderToResponse(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        String callerNameHeaderValue = requestContext.getHeaderString(CALLER_HEADER_NAME);
        headers.add(CALLER_HEADER_NAME, callerNameHeaderValue);
    }
}
