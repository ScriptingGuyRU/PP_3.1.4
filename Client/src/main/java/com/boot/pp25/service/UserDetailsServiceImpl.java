package com.boot.pp25.service;

import com.boot.pp25.dto.UserDto;
import com.boot.pp25.model.User;
import com.boot.pp25.service.abstractServ.RoleServices;
import com.boot.pp25.service.abstractServ.StackExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service("userDetailsServiceImpl")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private StackExchangeClient stackExchangeClient;

    private RoleServices roleServices;

    @Autowired
    public UserDetailsServiceImpl(StackExchangeClient stackExchangeClient, RoleServices roleServices) {
        this.stackExchangeClient = stackExchangeClient;
        this.roleServices = roleServices;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDto userDto = stackExchangeClient.getUserByEmail(s);

        User user = new User(userDto);
        user.setRoles(Arrays.stream(userDto.getRoles()).map(roleServices::getRoleByName).collect(Collectors.toSet()));

        System.out.println(user.toString());
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Oops, this user not founded");
        }
    }
}
