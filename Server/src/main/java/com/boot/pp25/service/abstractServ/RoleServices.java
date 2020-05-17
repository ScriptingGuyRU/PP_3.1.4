package com.boot.pp25.service.abstractServ;

import com.boot.pp25.dto.RoleDto;
import com.boot.pp25.model.Role;

public interface RoleServices {
    RoleDto getRoleDtoByName(String name);
    Role getRoleByName(String name);
}
