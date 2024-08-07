package ru.kpfu.itis.gabdullina.MyRestApi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.UserDto;
import ru.kpfu.itis.gabdullina.MyRestApi.service.AdminService;
import ru.kpfu.itis.gabdullina.MyRestApi.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserService userService;

    @Override
    public void setAdmin(Long id) {
        userService.setRoleAdmin(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
