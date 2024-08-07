package ru.kpfu.itis.gabdullina.MyRestApi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.UserDto;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.UserRegistrationDto;
import ru.kpfu.itis.gabdullina.MyRestApi.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BaseMapper.class)
public interface UserMapper {

    User convertToModel(UserRegistrationDto userRegistrationDto);

    @Mapping(target = "role", source = "user.role.name")
    UserDto convertToDto(User user);
}
