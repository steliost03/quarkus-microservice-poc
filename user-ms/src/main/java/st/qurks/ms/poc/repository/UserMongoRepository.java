package st.qurks.ms.poc.repository;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import st.qurks.ms.poc.config.MongoConfiguration;
import st.qurks.ms.poc.mapper.UserMapper;
import st.qurks.ms.poc.model.mongodb.CustomDeleteResult;
import st.qurks.ms.poc.model.mongodb.entity.UserEntity;
import st.qurks.ms.poc.util.ErrorMessages;
import io.quarkus.mongodb.MongoClientName;
import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.conversions.Bson;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static st.qurks.ms.poc.util.Constants.*;
import static st.qurks.ms.poc.util.MongoUtils.createUserOperationException;
import static st.qurks.ms.poc.util.MongoUtils.generateUpdateFieldsExprFromUserEntity;

@ApplicationScoped
public class UserMongoRepository {
    @Inject
    @MongoClientName("user-mongoclient")
    ReactiveMongoClient mongoClient;

    @Inject
    MongoConfiguration mongoConfig;

    @Inject
    UserMapper userMapper;

    public Uni<UserEntity> insertUser(UserEntity user) {
        return getUserCollection().insertOne(user).replaceWith(user)
                .onFailure()
                .transform(throwable ->
                        createUserOperationException(throwable, ErrorMessages.MONGO_INSERT_USER_FAILURE));
    }

    public Multi<UserEntity> findUsersByIds(String id) {
        return findUsersByIds(Collections.singletonList(id));
    }

    public Multi<UserEntity> findUsersByIds(List<String> ids) {
        return getUserCollection().find(in(MONGO_ID_FIELD, ids))
                .onFailure()
                .transform(throwable ->
                        createUserOperationException(throwable, ErrorMessages.MONGO_FIND_USER_FAILURE));
    }

    public Uni<UserEntity> deleteUserAndReturnDeletedDoc(String id) {
        return getUserCollection().findOneAndDelete(eq(MONGO_ID_FIELD, id))
                .onFailure()
                .transform(throwable ->
                        createUserOperationException(throwable, ErrorMessages.MONGO_DELETE_USER_FAILURE));
    }

    public Uni<DeleteResult> deleteUser(String id) {
        return getUserCollection().deleteOne(eq(MONGO_ID_FIELD, id))
                .onFailure()
                .transform(throwable ->
                        createUserOperationException(throwable, ErrorMessages.MONGO_DELETE_USER_FAILURE));
    }
    public Uni<CustomDeleteResult> deleteMultipleUsers(List<String> ids) {
        CustomDeleteResult finalDeleteResult = new CustomDeleteResult();

        Multi<DeleteResult> deleteResults = Multi.createFrom().iterable(ids)
                .onItem().transformToUniAndMerge(this::deleteUser);

        return deleteResults.collect()
                .asList()
                .map(deletes -> finalDeleteResult.appendFromDeleteResult(ids, deletes));
    }

    public Uni<UserEntity> upsertUserAndReturnDoc(String id, UserEntity user) {

        Bson filter = Filters.eq(MONGO_ID_FIELD, id);

        Bson update = Updates.combine(
                generateUpdateFieldsExprFromUserEntity(userMapper.entityToInfo(user)),
                Updates.set(LAST_UPDATED_TIME_FIELD, LocalDateTime.now()),
                Updates.setOnInsert(MONGO_ID_FIELD, id),
                Updates.setOnInsert(CREATED_TIME_FIELD, LocalDateTime.now()));

        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.AFTER);

        return getUserCollection().findOneAndUpdate(filter, update, options)
                .onFailure()
                .transform(throwable -> createUserOperationException(throwable, ErrorMessages.MONGO_UPSERT_USER_FAILURE));
    }

    private ReactiveMongoCollection<UserEntity> getUserCollection() {
        return mongoClient
                .getDatabase(mongoConfig.userDatabase())
                .getCollection(mongoConfig.userCollection(), UserEntity.class);
    }
}
