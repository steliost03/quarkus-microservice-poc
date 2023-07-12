package st.qurks.ms.poc.util;

public class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String MONGO_INSERT_USER_FAILURE = "Failed to insert user to MongoDB.";
    public static final String MONGO_FIND_USER_FAILURE = "Error occured on mongo 'find' operation.";
    public static final String MONGO_DELETE_USER_FAILURE = "Error occured on mongo 'delete' operation";
    public static final String MONGO_UPSERT_USER_FAILURE = "Error occured on mongo 'upsert' operation.";
    public static final String MONGO_UPDATE_USER_FAILURE = "Error occured on mongo 'update' operation.";
    public static final String NO_USER_FOUND = "No user found.";
}
