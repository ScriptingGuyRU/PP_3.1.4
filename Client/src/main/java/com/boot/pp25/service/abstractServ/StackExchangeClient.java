package com.boot.pp25.service.abstractServ;

import com.boot.pp25.dto.RoleDto;
import com.boot.pp25.dto.UserDto;
import com.boot.pp25.model.User;

import java.util.List;

public interface StackExchangeClient {
    List<UserDto> findAll();
    UserDto getUserById(Long id);
    void addUser(UserDto userDto);
    boolean deleteById(Long id);
    boolean editUser(UserDto userDto);
    UserDto getUserByEmail(String s);
    RoleDto getRoleByName(String s);

}
