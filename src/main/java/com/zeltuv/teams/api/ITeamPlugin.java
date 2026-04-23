package com.zeltuv.teams.api;

import com.zeltuv.teams.api.manager.ITeamManager;
import org.bukkit.entity.Player;

import java.util.List;

public interface ITeamPlugin {
    ITeamManager getTeamManager();
}
