package com.zeltuv.teams.api.cache;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Interface representing a team in the plugin.
 * Provides methods for managing team members, allies, bank, inventory, and various team operations.
 *
 * <p>This interface defines the contract for team implementations, supporting:
 * <ul>
 *   <li>Member and owner management</li>
 *   <li>Ally relationships between teams</li>
 *   <li>Team bank operations (deposits and withdrawals)</li>
 *   <li>Shared team inventory (enderchest)</li>
 *   <li>Team statistics (kills, deaths)</li>
 *   <li>Team properties (name, tag, home location)</li>
 *   <li>Ranking and scoring systems</li>
 * </ul>
 * </p>
 *
 * @author Zeltuv
 */
public interface ITeam {


    /**
     * Gets the owner of the team.
     * The owner is a special member with full permissions over the team.
     *
     * @return The team owner
     */
    IOwner getOwner();

    /**
     * Gets the unique identifier for this team.
     * The team UUID is derived from the owner's UUID.
     *
     * @return The team's unique identifier
     */
    UUID getTeamUUID();

    /**
     * Checks if the specified player is the owner of this team.
     *
     * @param player The offline player to check
     * @return true if the player is the owner, false otherwise
     */
    boolean isOwner(OfflinePlayer player);

    /**
     * Checks if the specified UUID belongs to the team owner.
     *
     * @param uuid The UUID to check
     * @return true if the UUID belongs to the owner, false otherwise
     */
    boolean isOwnerByUuid(UUID uuid);

    /**
     * Gets the name of the team.
     * The name can be null if not set.
     *
     * @return The team name, or null if not set
     */
    String getName();

    /**
     * Sets the name of the team.
     *
     * @param name The new team name
     */
    void setName(String name);

    /**
     * Gets the tag of the team.
     * Tags are typically short identifiers displayed before player names.
     *
     * @return The team tag
     */
    String getTag();

    /**
     * Sets the tag of the team.
     *
     * @param tag The new team tag
     */
    void setTag(String tag);

    /**
     * Gets the display name of the team.
     * Returns a placeholder if no name is set, otherwise returns the formatted name.
     *
     * @return The formatted team name or placeholder
     */
    String getDisplayName();

    /**
     * Checks if the given name matches the team name.
     * Comparison is case-insensitive and strips color codes.
     *
     * @param name The name to compare
     * @return true if names match, false otherwise
     */
    boolean hasName(String name);

    /**
     * Checks if the given tag matches the team tag.
     * Comparison is exact (case-sensitive).
     *
     * @param tag The tag to compare
     * @return true if tags match, false otherwise
     */
    boolean hasTag(String tag);


    /**
     * Gets the home location of the team.
     * This is typically a location where team members can teleport.
     *
     * @return The team's home location, or null if not set
     */
    Location getHome();

    /**
     * Sets the home location of the team.
     * Changes are automatically saved to the database.
     *
     * @param home The new home location, or null to unset the home
     */
    void setHome(Location home);

    /**
     * Gets the list of team members (excluding the owner).
     *
     * @return A list of team members
     */
    List<IMember> getMemberOnlyList();

    /**
     * Gets all members of the team including the owner.
     *
     * @return A list of all members including the owner
     */
    List<IMember> getAllMembers();

    /**
     * Gets a specific member by their UUID.
     * This does not include the owner.
     *
     * @param uniqueId The UUID of the member to find
     * @return The member with the specified UUID, or null if not found
     */
    IMember getMember(UUID uniqueId);

    /**
     * Checks if the team has a member with the specified UUID.
     * This includes both regular members and the owner.
     *
     * @param uniqueId The UUID to check
     * @return true if the team contains the member, false otherwise
     */
    boolean hasMember(UUID uniqueId);

    /**
     * Adds a user to the team as a member.
     * The user is assigned the lowest role by default.
     * Updates both the team and user in the database.
     *
     * @param user The user to add to the team
     */
    void addMember(IUser user);

    /**
     * Removes a member from the team.
     * If the member has the team inventory open, it will be closed.
     * Updates both the team and user in the database.
     *
     * @param user The offline user to remove from the team
     */
    void removeMember(IOfflineUser user);

    /**
     * Sets the role of a team member.
     * The change is automatically saved to the database.
     *
     * @param member The member whose role to update
     * @param role The new role to assign
     */
    void setRole(IMember member, IRole role);

    /**
     * Checks if any team members are currently online.
     * This includes both regular members and the owner.
     *
     * @return true if at least one member is online, false otherwise
     */
    boolean hasOnlineMembers();

    /**
     * Gets the list of allied team UUIDs.
     *
     * @return A list of UUIDs representing allied teams
     */
    List<UUID> getAllyList();

    /**
     * Gets all allied teams as Team instances.
     * Invalid or closed teams are automatically removed from the ally list.
     *
     * @return A list of allied Team instances
     */
    List<ITeam> getAllyTeams();

    /**
     * Adds an ally relationship with another team.
     * The relationship is bidirectional (both teams are added to each other's ally list).
     *
     * @param team The team to ally with
     */
    void addAlly(ITeam team);

    /**
     * Removes an ally relationship with another team.
     * The relationship is bidirectional (both teams are removed from each other's ally list).
     * Changes are automatically saved to the database.
     *
     * @param team The team to remove as an ally
     */
    void removeAlly(ITeam team);

    /**
     * Checks if this team is allied with the specified team.
     *
     * @param team The team to check
     * @return true if the teams are allied, false otherwise
     */
    boolean isAlliedWith(ITeam team);

    /**
     * Gets the current bank balance of the team.
     *
     * @return The team's bank balance
     */
    double getBankBalance();

    /**
     * Sets the bank balance of the team.
     * Changes are automatically saved to the database.
     *
     * @param bankBalance The new bank balance
     */
    void setBankBalance(double bankBalance);

    /**
     * Deposits money into the team bank.
     * Withdraws the specified amount from the player's balance and adds it to the team bank.
     * The operation fails if:
     * <ul>
     *   <li>Economy is disabled in config</li>
     *   <li>The deposit would exceed the team bank limit</li>
     *   <li>The player doesn't have enough money</li>
     * </ul>
     * Changes are automatically saved to the database.
     *
     * @param player The player making the deposit
     * @param amount The amount to deposit
     * @return true if the deposit was successful, false otherwise
     */
    boolean depositToBank(Player player, double amount);

    /**
     * Withdraws money from the team bank.
     * Deposits the specified amount to the player's balance and deducts it from the team bank.
     * The operation fails if:
     * <ul>
     *   <li>Economy is disabled in config</li>
     *   <li>The team doesn't have enough money</li>
     * </ul>
     * Changes are automatically saved to the database.
     *
     * @param player The player making the withdrawal
     * @param amount The amount to withdraw
     * @return true if the withdrawal was successful, false otherwise
     */
    boolean withdrawFromBank(Player player, double amount);

    /**
     * Gets the team's shared inventory (enderchest).
     *
     * @return The team inventory
     */
    Inventory getInventory();

    /**
     * Converts the team inventory to a map representation.
     * The map contains slot indices as keys and ItemStacks as values.
     *
     * @return A map of slot indices to ItemStacks
     */
    Map<Integer, ItemStack> getInventoryAsMap();

    /**
     * Gets the enderchest contents as a map.
     * This is an alias for {@link #getInventoryAsMap()}.
     *
     * @return A map of slot indices to ItemStacks
     */
    Map<Integer, ItemStack> getEnderchestAsMap();

    /**
     * Opens the team enderchest for a player.
     * The player is tracked as viewing the team's inventory.
     *
     * @param player The player to open the enderchest for
     */
    void openEnderchest(Player player);

    /**
     * Reloads the team inventory.
     * Closes the inventory for all viewers and recreates it with the same contents.
     * This is useful after configuration changes.
     */
    void reloadInventory();

    /**
     * Gets the total kills achieved by all team members.
     * This includes kills by both active members and the owner.
     *
     * @return The total number of kills
     */
    int getTotalKills();

    /**
     * Gets the total deaths suffered by all team members.
     * This includes deaths of both active members and the owner.
     *
     * @return The total number of deaths
     */
    int getTotalDeaths();

    /**
     * Gets the team's rank.
     * A rank of -1 indicates the team is unranked.
     *
     * @return The team's rank, or -1 if unranked
     */
    int getRank();


    /**
     * Gets the team's score.
     * A score of -1 indicates the team has no score.
     *
     * @return The team's score, or -1 if no score is set
     */
    double getScore();

    /**
     * Checks if friendly fire is enabled for the team.
     *
     * @return true if friendly fire is enabled, false otherwise
     */
    boolean isFriendlyFireEnabled();

    /**
     * Sets whether friendly fire is enabled for the team.
     *
     * @param friendlyFireEnabled true to enable friendly fire, false to disable
     */
    void setFriendlyFireEnabled(boolean friendlyFireEnabled);

    /**
     * Checks if the team is closed.
     * Closed teams are typically teams that have been disbanded or are inactive.
     *
     * @return true if the team is closed, false otherwise
     */
    boolean isClosed();

    void setClosed(boolean newValue);

    void updateRank(int rank);

    void updateScore(double score);

    boolean isTagChanged();

    /**
     * Saves the team data to the database.
     * This queues the team for asynchronous saving via the worker system.
     */
    void save();
}