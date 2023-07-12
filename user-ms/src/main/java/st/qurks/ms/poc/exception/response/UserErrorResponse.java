package st.qurks.ms.poc.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import st.qurks.ms.poc.exception.UserNotFoundException;
import st.qurks.ms.poc.exception.UserOperationException;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserErrorResponse {

    private int code;
    private String errorMessage;
    private String reason;

    public UserErrorResponse fromException(UserOperationException exception) {
        this.code = exception.getCode();
        this.errorMessage = exception.getErrorMessage();
        this.reason = exception.getReason();
        return this;
    }

    public UserErrorResponse fromException(UserNotFoundException exception) {
        this.code = HttpResponseStatus.NOT_FOUND.code();
        this.errorMessage = exception.getErrorMessage();
        this.reason = exception.getReason();
        return this;
    }

    public UserErrorResponse fromThrowable(Throwable throwable) {
        this.code = HttpResponseStatus.INTERNAL_SERVER_ERROR.code();
        this.errorMessage = Optional.ofNullable(throwable).map(t -> t.getClass().getSimpleName() + ": " + t.getMessage()).orElse(null);
        this.reason = Optional.ofNullable(throwable).map(Throwable::getCause).map(Throwable::getMessage).orElse(null);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
