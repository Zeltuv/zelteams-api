package com.zeltuv.teams.api.cache;

import com.zeltuv.teams.api.enums.CustomStatistics;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface IOfflineUser {
    void setTeamUUID(UUID team);
    UUID getTeamUUID();
    int getStats(CustomStatistics customStatistics);
    UUID getOfflinePlayer();
    String getName();
    UUID getTeam();
    Map<CustomStatistics, Integer> getCustomStatistics();
}
