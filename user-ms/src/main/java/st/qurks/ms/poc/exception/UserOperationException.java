package st.qurks.ms.poc.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public class UserOperationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;
    private String errorMessage;
    private String reason;

    public UserOperationException() {
    }

    public UserOperationException(String errorMessage){
        this.code = HttpResponseStatus.NOT_IMPLEMENTED.code();
        this.errorMessage = errorMessage;
    }

    public UserOperationException(int code, String errorMessage, String reason) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.reason = reason;
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
