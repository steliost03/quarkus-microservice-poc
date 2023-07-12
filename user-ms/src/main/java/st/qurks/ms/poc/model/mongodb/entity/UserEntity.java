package st.qurks.ms.poc.model.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import st.qurks.ms.poc.util.Constants;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDateTime;

import static st.qurks.ms.poc.util.Constants.MONGO_ID_FIELD;

public class UserEntity {

    @BsonId
    @BsonProperty(MONGO_ID_FIELD)
    @JsonProperty(MONGO_ID_FIELD)
    private String id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    @BsonProperty(Constants.CREATED_TIME_FIELD)
    @JsonProperty(Constants.CREATED_TIME_FIELD)
    private LocalDateTime createdDateTime;
    @BsonProperty(Constants.LAST_UPDATED_TIME_FIELD)
    @JsonProperty(Constants.LAST_UPDATED_TIME_FIELD)
    private LocalDateTime lastUpdatedDateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }
}
