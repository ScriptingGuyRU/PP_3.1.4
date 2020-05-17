package com.boot.pp25.service;

import com.boot.pp25.dto.RoleDto;
import com.boot.pp25.model.Role;
import com.boot.pp25.service.abstractServ.RoleServices;
import com.boot.pp25.service.abstractServ.StackExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleServices {

    @Autowired
    private StackExchangeClient stackExchangeClient;

    @Autowired
    public RoleServiceImpl(StackExchangeClient stackExchangeClient) {
        this.stackExchangeClient = stackExchangeClient;
    }

    @Override
    public Role getRoleByName(String name) {
        RoleDto roleDto = stackExchangeClient.getRoleByName(name);
        return new Role(roleDto);
    }
}
