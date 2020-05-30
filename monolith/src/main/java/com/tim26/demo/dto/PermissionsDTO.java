package com.tim26.demo.dto;

import com.tim26.demo.model.Permission;

import java.util.ArrayList;
import java.util.List;

public class PermissionsDTO {

    private List<String> permissions = new ArrayList<>();
    private List<String> blockedPermissions = new ArrayList<>();

    public PermissionsDTO(){

    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getBlockedPermissions() {
        return blockedPermissions;
    }

    public void setBlockedPermissions(List<String> blockedPermissions) {
        this.blockedPermissions = blockedPermissions;
    }
}
