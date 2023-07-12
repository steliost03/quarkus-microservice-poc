package st.qurks.ms.poc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import st.qurks.ms.poc.util.Constants;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse {

    private String id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private LocalDateTime createdDateTime;
    @JsonProperty(Constants.LAST_UPDATED_TIME_FIELD)
    private LocalDateTime lastUpdatedDateTime;

    private List<String> successfullyDeletedIds;
    private List<String> failedToDeleteIds;

    private Boolean docFoundAndDeleted;

    public UserResponse withDeleteSuccess(Boolean deleteSuccess) {
        this.docFoundAndDeleted = deleteSuccess;
        return this;
    }

    public UserResponse withId(String id){
        this.id = id;
        return this;
    }

    public UserResponse withUsername(String username){
        this.username = username;
        return this;
    }

    public UserResponse withEmail(String email){
        this.email = email;
        return this;
    }

    public UserResponse withFirstname(String firstname){
        this.firstname = firstname;
        return this;
    }

    public UserResponse withLastname(String lastname){
        this.lastname = lastname;
        return this;
    }

    public UserResponse withCreatedDateTime(LocalDateTime createdDateTime){
        this.createdDateTime = createdDateTime;
        return this;
    }

    public UserResponse withLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime){
        this.lastUpdatedDateTime = lastUpdatedDateTime;
        return this;
    }

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

    public List<String> getSuccessfullyDeletedIds() {
        return successfullyDeletedIds;
    }

    public void setSuccessfullyDeletedIds(List<String> successfullyDeletedIds) {
        this.successfullyDeletedIds = successfullyDeletedIds;
    }

    public List<String> getFailedToDeleteIds() {
        return failedToDeleteIds;
    }

    public void setFailedToDeleteIds(List<String> failedToDeleteIds) {
        this.failedToDeleteIds = failedToDeleteIds;
    }

    public Boolean getDocFoundAndDeleted() {
        return docFoundAndDeleted;
    }

    public void setDocFoundAndDeleted(Boolean docFoundAndDeleted) {
        this.docFoundAndDeleted = docFoundAndDeleted;
    }
}
