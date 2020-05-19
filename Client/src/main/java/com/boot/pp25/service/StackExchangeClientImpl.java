package com.boot.pp25.service;

import com.boot.pp25.dto.RoleDto;
import com.boot.pp25.dto.UserDto;
import com.boot.pp25.service.abstractServ.StackExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class StackExchangeClientImpl implements StackExchangeClient {

    private RestTemplate restTemplate;
    private PasswordEncoder passwordEncoder;
    private final String ADRESS_URL = "http://localhost:8081/api/";

    @Autowired
    public StackExchangeClientImpl(RestTemplate restTemplate, PasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAll() {
        ResponseEntity<List<UserDto>> response =
                restTemplate.exchange(ADRESS_URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<UserDto>>() {
                        });
        return response.getBody();
    }

    @Override
    public UserDto getUserById(Long id) {
        UserDto userDto = restTemplate.getForObject(ADRESS_URL + id.toString(), UserDto.class);
        return userDto;
    }

    @Override
    public void addUser(UserDto userDto) {
        restTemplate.postForObject(ADRESS_URL, userDto, UserDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            restTemplate.delete(ADRESS_URL + id.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editUser(UserDto userDto) {
        try {
            restTemplate.put(ADRESS_URL, userDto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDto getUserByEmail(String s) {
        Optional<UserDto> userDto = Optional.ofNullable(
                restTemplate.getForObject(ADRESS_URL + "getAuth/" + s, UserDto.class));
        if (!userDto.isPresent()) {
            throw new RuntimeException("Ошибка в getUserById StackExchangeClient");
        }
        return userDto.get();
    }

    @Override
    public RoleDto getRoleByName(String s) {
        try {
            RoleDto roleDto = restTemplate.getForObject(ADRESS_URL + "getrole/" + s, RoleDto.class);
            return roleDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка в getRoleByName StackExchangeClient");
        }
    }

}
