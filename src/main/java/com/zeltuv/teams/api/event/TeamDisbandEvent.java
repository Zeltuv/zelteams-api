package com.zeltuv.teams.api.event;

import com.zeltuv.teams.api.cache.ITeam;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
@RequiredArgsConstructor
public class TeamDisbandEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final ITeam team;
    @Setter
    private boolean cancelled;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}