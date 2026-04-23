package com.zeltuv.teams.api.cache;

import com.google.common.collect.Sets;
import com.zeltuv.teams.api.enums.RolePermission;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public interface IRole {

    String toString();

    void write(ConfigurationSection section);

    boolean isHigher(IRole role);

    boolean hasPermission(RolePermission rolePermission);

    int getPriority();
    String getName();
    Set<RolePermission> getPermissions();
}
