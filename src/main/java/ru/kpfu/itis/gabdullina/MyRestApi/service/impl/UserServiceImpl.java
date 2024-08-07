package ru.kpfu.itis.gabdullina.MyRestApi.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.JwtReqDto;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.UserDto;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.UserRegistrationDto;
import ru.kpfu.itis.gabdullina.MyRestApi.exception.RecipeNotFoundException;
import ru.kpfu.itis.gabdullina.MyRestApi.mapper.UserMapper;
import ru.kpfu.itis.gabdullina.MyRestApi.model.Recipe;
import ru.kpfu.itis.gabdullina.MyRestApi.model.Role;
import ru.kpfu.itis.gabdullina.MyRestApi.model.User;
import ru.kpfu.itis.gabdullina.MyRestApi.repository.RoleRepository;
import ru.kpfu.itis.gabdullina.MyRestApi.repository.UserRepository;
import ru.kpfu.itis.gabdullina.MyRestApi.service.UserService;
import ru.kpfu.itis.gabdullina.MyRestApi.token.JwtTokenManager;

import java.util.List;
import java.util.Optional;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.*;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils.DATA_SOURCE_EXCEPTION;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils.USER_NOT_FOUND_EXCEPTION;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenManager jwtTokenManager;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND));
    }

    @Override
    public void saveUser(UserRegistrationDto userRegistrationDto) {
        userRegistrationDto.setPassword(encoder.encode(userRegistrationDto.getPassword()));
        User user = mapper.convertToModel(userRegistrationDto);
        Optional<Role> optionalRole = roleRepository.findByName(ROLE_USER);
        if(optionalRole.isPresent()) {
            user.setRole(optionalRole.get());
            userRepository.save(user);
        } else {
            throw new DataSourceLookupFailureException(DATA_SOURCE_EXCEPTION);

        }
    }

    @Override
    public boolean isUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void setRoleAdmin(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException(USER_NOT_FOUND_EXCEPTION);
        }

        Optional<Role> roleOptional = roleRepository.findByName(ROLE_ADMIN);
        if(roleOptional.isEmpty()) {
            throw new DataSourceLookupFailureException(DATA_SOURCE_EXCEPTION);
        }
        User user = userOptional.get();
        Role role = roleOptional.get();
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(mapper::convertToDto)
                .toList();
    }

    @Override
    public void updateUser(Long id, JwtReqDto userDto) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException(USER_NOT_FOUND_EXCEPTION);
        }

        User user = userOptional.get();
        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));

        userRepository.save(user);
    }

    @Override
    public String getUsernameById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException(USER_NOT_FOUND_EXCEPTION);
        }

        return userOptional.get().getUsername();
    }

    @Override
    public Long getIdByUsername(String username) {
        Optional<User> userOptional =  userRepository.findByUsername(username);

        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException(USER_NOT_FOUND_EXCEPTION);
        }

        return userOptional.get().getId();
    }
}
