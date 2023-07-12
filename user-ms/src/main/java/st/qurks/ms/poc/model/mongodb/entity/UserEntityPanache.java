package st.qurks.ms.poc.model.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import static st.qurks.ms.poc.util.Constants.MONGO_ID_FIELD;

@MongoEntity(clientName = "user-panache", collection = "UserInfo")
public class UserEntityPanache {

    @BsonId
    @BsonProperty(MONGO_ID_FIELD)
    @JsonProperty(MONGO_ID_FIELD)
    private ObjectId id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
