package com.bci.bcichallenge.mapper;

import com.bci.bcichallenge.dto.UserRequest;
import com.bci.bcichallenge.dto.UserResponse;
import com.bci.bcichallenge.model.Phone;
import com.bci.bcichallenge.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", source = "userRequest.name")
    @Mapping(target = "email", source = "userRequest.email")
    @Mapping(target = "password", source = "userRequest.password")
    @Mapping(target = "phones", source = "userRequest.phones")
    User toEntity(UserRequest userRequest);

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "isActive", source = "user.isActive")
    @Mapping(target = "token", source = "user.token")
    @Mapping(target = "created", source = "user.created")
    @Mapping(target = "modified", source = "user.modified")
    @Mapping(target = "lastLogin", source = "user.lastLogin")
    @Mapping(target = "phones", source = "user.phones")
    UserResponse toUserResponse(User user);

    @Mapping(target = "number", source = "phone.number")
    @Mapping(target = "citycode", source = "phone.citycode")
    @Mapping(target = "contrycode", source = "phone.contrycode")
    Phone toPhoneEntity(Phone phone);

    List<Phone> toPhoneEntityList(List<Phone> phones);
}
