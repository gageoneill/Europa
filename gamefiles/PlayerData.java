import java.util.*; // Set, HashSet

/**
 * A record type that stores the player's data so far.
 * This may include completed levels, long-term bonuses, etc.
 * 
 * @author William Chargin
 * 
 * @version 14 October 2014: Initial version
 */
public class PlayerData  
{
    
    /**
     * The levels that the player can access.
     */
    private final Set<Class<? extends Level>> accessibleLevels = new HashSet<Class<? extends Level>>();

    /**
     * Marks the given level as accessible.
     * You should call this when the player completes the preceding level.
     */
    public void markAccessible(Class<? extends Level> clazz)
    {
        accessibleLevels.add(clazz);
    }
    
    /**
     * Marks the given level as inaccessible.
     * This might apply if the player gets knocked back, or something,
     * or you want to reset the game state.
     */
    public void markInaccessible(Class<? extends Level> clazz)
    {
        accessibleLevels.remove(clazz);
    }
    
    /**
     * Determines whether the player can access a given level.
     * 
     * @param clazz
     *      the clazz of the relevant level; for example, {@code Level01.class}
     * @return
     *      {@code true} if the player can access the level, or {@code false} otherwise
     */
    public boolean canAccess(Class<? extends Level> clazz)
    {
        return accessibleLevels.contains(clazz);
    }
}
