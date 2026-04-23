package com.zeltuv.teams.api.cache;

import com.zeltuv.teams.api.ZelTeamsAPI;
import com.zeltuv.teams.api.enums.CustomStatistics;
import com.zeltuv.teams.api.enums.RolePermission;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IUser extends IOfflineUser{
    int getMaxMembers();
    void setMaxMembers(int newValue);
    void load(Player player);
    Player getPlayer();
    @NotNull Map<Player,Long> getMailbox();
    @NotNull Map<Player, Long> getAllyMailbox();
    void addStats(CustomStatistics customStatistics, int i);
    void setStats(CustomStatistics customStatistics, int i);
    void removeAllyMailbox(Player inviter);
    void removeMailbox(Player inviter);
    int calculateMaxMemberForTeam(Player player);
}
