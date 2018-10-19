import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A level in the Europa game.
 * 
 * @author William Chargin
 * 
 * @version 14 October 2014: Add map linkage and reset functionality
 * @version 3 October 2014: Initial version
 */
public class Level extends World
{
    /**
     * The map associated with this level.
     */
    private final Map map;

    /**
     * Returns the {@link Map} associated with this game.
     * 
     * @return
     *      the game map
     */
    public Map getMap()
    {
        return map;
    }
    
    /**
     * Constructor for objects of class Level.
     * 
     */
    public Level(Map map)
    {
        super(1200, 900, 1, false); 
        this.map = map;
    }
    
    /**
     * Sets up the world, for the first time or after a world reset.
     * Does nothing by default; subclasses should override to add objects.
     */
    public void prepare()
    {
    }
    
    /**
     * Removes all objects from the world, then calls {@link #prepare()}.
     */
    public void resetWorld()
    {
        // Remove all objects.
        removeObjects(getObjects(null));
        
        // Re-prepare the world.
        prepare();
    }
    
    /**
     * Returns to the map world, and resets the level in case it is restarted.
     */
    public void returnToMap()
    {
        // Return to the map.
        Greenfoot.setWorld(map);
        
        // Reset the world
        removeObjects(getObjects(null));
        prepare();
    }
}
