package com.boot.pp25.service;

import com.boot.pp25.dto.UserDto;
import com.boot.pp25.service.abstractServ.RoleServices;
import com.boot.pp25.service.abstractServ.StackExchangeClient;
import com.boot.pp25.service.abstractServ.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private StackExchangeClient stackExchangeClient;

    private RoleServices roleServices;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(StackExchangeClient stackExchangeClient, PasswordEncoder passwordEncoder, RoleServices roleServices) {
        this.stackExchangeClient = stackExchangeClient;
        this.passwordEncoder = passwordEncoder;
        this.roleServices = roleServices;
    }

    @Override
    public List<UserDto> findAll() {
        return stackExchangeClient.findAll();
    }

    @Override
    public UserDto getUserById(Long id) {
        return stackExchangeClient.getUserById(id);
    }

    @Override
    public void saveUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        stackExchangeClient.addUser(userDto);
    }

    @Override
    public boolean deleteById(Long id) {
        return stackExchangeClient.deleteById(id);
    }

    @Override
    public boolean editUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return stackExchangeClient.editUser(userDto);
    }

}
