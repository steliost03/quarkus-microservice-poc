package st.qurks.ms.poc.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorMessage;
    private String reason;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public UserNotFoundException(String errorMessage, String reason) {
        this.errorMessage = errorMessage;
        this.reason = reason;
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
