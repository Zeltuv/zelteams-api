package com.zeltuv.teams.api.manager;

import com.zeltuv.teams.api.cache.IOfflineUser;
import com.zeltuv.teams.api.cache.ITeam;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ITeamManager {
    Optional<ITeam> getTeam(Player player);
    Optional<ITeam> getOfflinePlayerTeam(UUID uuid);
    Optional<ITeam> getTeamByName(String name);
    Optional<ITeam> getByTag(String tag);
    boolean hasTeam(Player player);
    boolean isTeamNameIllegal(String name);
    boolean isTeamTagIllegal(String tag);
    boolean isNameInUse(String name);
    boolean isTagInUse(String tag);
    boolean tagAlreadyExists(String tag, boolean ignoreCase);
    Optional<IOfflineUser> getOfflineOrOnlineUser(UUID uuid);
    void saveUser(IOfflineUser user);
    void disbandTeam(ITeam team);
    List<Player> getAllyChatToggle();
    List<Player> getTeamChatToggle();
    Map<UUID, ITeam> getCachedTeams();
}
