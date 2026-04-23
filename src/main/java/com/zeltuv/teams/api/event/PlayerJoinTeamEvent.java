package com.zeltuv.teams.api.event;

import com.zeltuv.teams.api.cache.ITeam;
import com.zeltuv.teams.api.cache.IUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
@RequiredArgsConstructor
public class PlayerJoinTeamEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final IUser user;
    private final ITeam team;
    private boolean cancelled;

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}