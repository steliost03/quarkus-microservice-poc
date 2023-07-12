package st.qurks.ms.poc.service;

import st.qurks.ms.poc.exception.UserNotFoundException;
import st.qurks.ms.poc.mapper.UserMapper;
import st.qurks.ms.poc.model.UserRequest;
import st.qurks.ms.poc.model.UserResponse;
import st.qurks.ms.poc.repository.UserMongoRepositoryPanache;
import st.qurks.ms.poc.util.ErrorMessages;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import static st.qurks.ms.poc.util.MongoUtils.createUserOperationException;

@ApplicationScoped
public class UserServicePanache {

    private final UserMongoRepositoryPanache userReactiveMongoRepository;

    private final UserMapper userMapper;

    @Inject
    UserServicePanache(UserMongoRepositoryPanache userReactiveMongoRepository, UserMapper userMapper) {
        this.userReactiveMongoRepository = userReactiveMongoRepository;
        this.userMapper = userMapper;
    }

    public Uni<UserResponse> createUser(UserRequest user) {
        return userReactiveMongoRepository
                .persist(userMapper.requestToEntityPanacheNewId(user))
                .onItem().transform(userMapper::panacheEntityToResponse)
                .onFailure().transform(throwable ->
                        createUserOperationException(throwable, ErrorMessages.MONGO_INSERT_USER_FAILURE));
    }

    public Uni<UserResponse> findUserById(String id) {
        return userReactiveMongoRepository
                .findById(new ObjectId(id))
                .onFailure().transform(throwable ->
                        createUserOperationException(throwable, ErrorMessages.MONGO_FIND_USER_FAILURE))
                .onItem().ifNull().failWith(new UserNotFoundException("find operation - " + ErrorMessages.NO_USER_FOUND))
                .onItem().transform(userMapper::panacheEntityToResponse);
    }

    public Uni<UserResponse> updateUser(UserRequest user) {
        return userReactiveMongoRepository
                .update(userMapper.requestToEntityPanacheExistingId(user))
                .onFailure().transform(throwable ->
                        createUserOperationException(throwable, ErrorMessages.MONGO_UPDATE_USER_FAILURE))
                .onItem().ifNull().failWith(new UserNotFoundException("update operation - " + ErrorMessages.NO_USER_FOUND))
                .onItem().transform(userMapper::panacheEntityToResponse);
    }

    public Uni<UserResponse> deleteUserById(String id) {
        return userReactiveMongoRepository
                .deleteById(new ObjectId(id))
                .onFailure().transform(throwable ->
                        createUserOperationException(throwable, ErrorMessages.MONGO_DELETE_USER_FAILURE))
                .onItem().transform(resp -> new UserResponse().withDeleteSuccess(resp));
    }
}
