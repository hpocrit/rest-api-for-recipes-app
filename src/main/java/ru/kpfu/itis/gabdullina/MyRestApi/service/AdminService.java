package ru.kpfu.itis.gabdullina.MyRestApi.service;

import ru.kpfu.itis.gabdullina.MyRestApi.dto.UserDto;

import java.util.List;

public interface AdminService {

    void setAdmin(Long id);
    List<UserDto> getAllUsers();
}
