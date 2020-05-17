package com.boot.pp25.dto;

import com.boot.pp25.model.Role;

public class RoleDto {
    private Long role_id;
    private String name;

    public RoleDto() {
    }

    public RoleDto(Role role) {
        this.role_id = role.getRole_id();
        this.name = role.getName();
    }

    public RoleDto(Long role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
