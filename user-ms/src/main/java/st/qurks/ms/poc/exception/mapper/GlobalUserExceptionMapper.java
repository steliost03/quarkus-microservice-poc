package st.qurks.ms.poc.exception.mapper;

import st.qurks.ms.poc.exception.UserNotFoundException;
import st.qurks.ms.poc.exception.UserOperationException;
import st.qurks.ms.poc.exception.response.UserErrorResponse;
import st.qurks.ms.poc.logging.DefaultUserMSLogging;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@DefaultUserMSLogging
public class GlobalUserExceptionMapper {

    @ServerExceptionMapper
    public RestResponse<UserErrorResponse> mapUserOperationException(UserOperationException exception, ContainerRequestContext requestContext) {
        UserErrorResponse errorResponse = new UserErrorResponse().fromException(exception);
        return ResponseBuilder.create(RestResponse.Status.fromStatusCode(errorResponse.getCode()), errorResponse)
                .build();
    }

    @ServerExceptionMapper
    public RestResponse<UserErrorResponse> mapUserNotFoundException(UserNotFoundException exception, ContainerRequestContext requestContext) {
        UserErrorResponse errorResponse = new UserErrorResponse().fromException(exception);
        return ResponseBuilder.create(RestResponse.Status.fromStatusCode(errorResponse.getCode()), errorResponse)
                .build();
    }

    @ServerExceptionMapper
    public RestResponse<UserErrorResponse> mapThrowable(Throwable throwable, ContainerRequestContext requestContext){
        UserErrorResponse errorResponse =  new UserErrorResponse().fromThrowable(throwable);
        return ResponseBuilder.create(RestResponse.Status.fromStatusCode(errorResponse.getCode()),errorResponse)
                .build();
    }
}
