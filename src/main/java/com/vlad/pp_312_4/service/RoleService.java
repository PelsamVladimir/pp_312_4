package com.vlad.pp_312_4.service;

import com.vlad.pp_312_4.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String name);

    Role getRoleById(Long id);

    List<Role> allRoles();

}
