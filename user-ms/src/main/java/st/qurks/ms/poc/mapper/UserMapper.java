package st.qurks.ms.poc.mapper;

import st.qurks.ms.poc.model.UserInfo;
import st.qurks.ms.poc.model.UserRequest;
import st.qurks.ms.poc.model.UserResponse;
import st.qurks.ms.poc.model.mongodb.CustomDeleteResult;
import st.qurks.ms.poc.model.mongodb.entity.UserEntity;
import st.qurks.ms.poc.model.mongodb.entity.UserEntityPanache;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface UserMapper {

    UserResponse entityToResponse(UserEntity userEntity);

    @Mapping(target = "id", expression = "java(entity.getId().toString())")
    UserResponse panacheEntityToResponse(UserEntityPanache entity);

    UserInfo entityToInfo(UserEntity userInfo);

    UserResponse customDeleteResultToResponse(CustomDeleteResult customDeleteResult);

    @Mapping(target = "createdDateTime",
            expression = "java(java.time.LocalDateTime.now())")
    UserEntity requestToEntity(UserRequest userRequest);

    @Mapping(target = "id" , expression = "java(org.bson.types.ObjectId.get())")
    UserEntityPanache requestToEntityPanacheNewId(UserRequest userRequest);

    @Mapping(target = "id" , expression = "java(new org.bson.types.ObjectId(userRequest.getId()))")
    UserEntityPanache requestToEntityPanacheExistingId(UserRequest userRequest);


}
