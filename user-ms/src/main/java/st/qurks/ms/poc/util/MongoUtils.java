package st.qurks.ms.poc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import st.qurks.ms.poc.exception.UserOperationException;
import st.qurks.ms.poc.model.UserInfo;
import st.qurks.ms.poc.repository.UserMongoRepository;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoUtils {

    private static final Logger logger = LoggerFactory.getLogger(UserMongoRepository.class);

    private MongoUtils() {
    }

    public static UserOperationException createUserOperationException(Throwable throwable, String errorMessage) {
        logger.error(errorMessage, throwable);
        return new UserOperationException(
                HttpResponseStatus.INTERNAL_SERVER_ERROR.code(),
                errorMessage, throwable.getMessage());
    }

    public static Document generateUpdateFieldsExprFromUserEntity(UserInfo userInfo) {
        try {
            Document updateExpression = Document.parse(JsonUtils.objToJson(userInfo));
            return new Document("$set", updateExpression);
        } catch (JsonProcessingException e) {
            throw new UserOperationException(
                    HttpResponseStatus.INTERNAL_SERVER_ERROR.code(),
                    e.getMessage(), e.getMessage());
        }
    }

}
