package com.boot.pp25.controller;

import com.boot.pp25.dto.RoleDto;
import com.boot.pp25.dto.UserDto;
import com.boot.pp25.service.abstractServ.RoleServices;
import com.boot.pp25.service.abstractServ.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private UserService userService;
    private RoleServices roleServices;

    @Autowired
    public RestController(UserService userService, RoleServices roleServices) {
        this.userService = userService;
        this.roleServices = roleServices;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        if (hasNull(userDto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> read() {
        List<UserDto> userList = userService.findAll();

        return !userList.isEmpty() || userList != null ?
                new ResponseEntity<>(userList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> read(@PathVariable("id") Long id) {
        UserDto userDto = userService.getUserById(id);

        return userDto != null ?
                new ResponseEntity<>(userDto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/getAuth/{s}")
    public ResponseEntity<?> authenticate(@PathVariable("s") String s) {
        UserDto userDto = userService.loadUserByUsername(s);
        System.out.println(userDto);
        return userDto != null ?
                new ResponseEntity<>(userDto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/getrole/{s}")
    public ResponseEntity<?> getRoleByName(@PathVariable("s") String s) {
        RoleDto roleDto = roleServices.getRoleDtoByName(s);

        return roleDto != null ?
                new ResponseEntity<>(roleDto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserDto userDto) {
        if (hasNull(userDto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return userService.editUser(userDto)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userService.deleteById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    public boolean hasNull(UserDto userDto) {
        if (userDto.getUserName().equals("")
                || userDto.getLastName().equals("")
                || userDto.getEmail().equals("")
                || userDto.getPassword().equals("")
                || userDto.getRoles().length == 0) {
            return true;
        } else return false;
    }
}
