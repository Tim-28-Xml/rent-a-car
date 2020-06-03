package com.tim26.AuthenticationService.repository;

import com.tim26.AuthenticationService.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {

    Permission findByName(String name);
}
