package com.tim26.demo.repository;

import com.tim26.demo.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {

    Permission findByName(String name);
}
