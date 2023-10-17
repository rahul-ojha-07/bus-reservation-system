package com.example.brs.repository.user;

import com.example.brs.entity.user.Role;
import com.example.brs.entity.user.UserRoles;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(UserRoles role);
}
