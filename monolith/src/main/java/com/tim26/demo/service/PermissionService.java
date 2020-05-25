package com.tim26.demo.service;

import com.tim26.demo.model.Permission;
import com.tim26.demo.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findByName(String name){
        return permissionRepository.findByName(name);
    }
}
