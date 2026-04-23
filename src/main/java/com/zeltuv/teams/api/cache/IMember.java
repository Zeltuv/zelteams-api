package com.zeltuv.teams.api.cache;

import com.zeltuv.teams.api.enums.RolePermission;

import java.util.UUID;

public interface IMember {
    String toString();
    boolean canPromote(IMember member);
    boolean canDemote(IMember member);
    boolean hasPermission(RolePermission rolePermission);
    UUID getUuid();
    String getUsername();
    void setUsername(String name);
    IRole getRole();
    void setRole(IRole role);
}
