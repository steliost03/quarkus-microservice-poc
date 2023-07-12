package st.qurks.ms.poc.service;

import st.qurks.ms.poc.exception.UserNotFoundException;
import st.qurks.ms.poc.mapper.UserMapper;
import st.qurks.ms.poc.model.UserRequest;
import st.qurks.ms.poc.model.UserResponse;
import st.qurks.ms.poc.repository.UserMongoRepository;
import st.qurks.ms.poc.util.ErrorMessages;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UserService {

    private final UserMongoRepository userMongoRepo;

    private final UserMapper userMapper;

    @Inject
    UserService(UserMongoRepository userMongoRepo, UserMapper userMapper) {
        this.userMongoRepo = userMongoRepo;
        this.userMapper = userMapper;
    }

    public Uni<UserResponse> createUser(UserRequest user) {
        return userMongoRepo.insertUser(userMapper.requestToEntity(user))
                .onItem().transform(userMapper::entityToResponse);
    }

    public Uni<UserResponse> findUser(String id) {
        return userMongoRepo.findUsersByIds(id)
                .collect().first()
                .onItem().ifNull().failWith(new UserNotFoundException("find operation - " + ErrorMessages.NO_USER_FOUND))
                .onItem().transform(userMapper::entityToResponse);
    }

    public Multi<UserResponse> findUsers(List<String> ids) {
        return userMongoRepo.findUsersByIds(ids)
                .onItem().transform(userMapper::entityToResponse);
    }

    public Uni<UserResponse> deleteUser(String id) {
        return userMongoRepo.deleteUserAndReturnDeletedDoc(id)
                .onItem().ifNull().failWith(new UserNotFoundException("delete operation - " + ErrorMessages.NO_USER_FOUND))
                .onItem().transform(userMapper::entityToResponse);
    }

    public Uni<UserResponse> deleteUsers(List<String> ids) {
        return userMongoRepo.deleteMultipleUsers(ids)
                .onItem().transform(userMapper::customDeleteResultToResponse);
    }

    public Uni<UserResponse> upsertUser(String id, UserRequest user) {
        return userMongoRepo.upsertUserAndReturnDoc(id, userMapper.requestToEntity(user))
                .onItem().transform(userMapper::entityToResponse);
    }
}
