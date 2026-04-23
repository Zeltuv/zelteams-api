package com.zeltuv.teams.api;

import org.bukkit.Bukkit;

public class ZelTeamsAPI {

    public static ITeamPlugin getInstance(){
        return (ITeamPlugin) Bukkit.getPluginManager().getPlugin("ZelTeams");
    }
}
