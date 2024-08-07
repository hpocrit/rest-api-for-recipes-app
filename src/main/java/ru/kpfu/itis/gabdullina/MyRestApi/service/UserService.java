package ru.kpfu.itis.gabdullina.MyRestApi.service;

import ru.kpfu.itis.gabdullina.MyRestApi.dto.JwtReqDto;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.UserDto;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.UserRegistrationDto;
import ru.kpfu.itis.gabdullina.MyRestApi.model.User;

import java.util.List;

public interface UserService {

    User getUserByUsername(String username);

    void saveUser(UserRegistrationDto user);

    boolean isUserExist(String username);

    void setRoleAdmin(Long id);

    List<UserDto> getAllUsers();

    void updateUser(Long id, JwtReqDto userDto);

    String getUsernameById(Long id);

    Long getIdByUsername(String username);
}
