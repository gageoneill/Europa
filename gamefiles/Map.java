import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)



/**
 * The level map; click on a level to access it.
 * 
 * @author William Chargin
 * 
 * @version 14 October 2014: Added clickable map icons
 * @version 9 October 2014: Initial version
 */
public class Map extends World
{
    
    private FadeManager fader;
    
    /**
     * The player data for this game.
     */
    private PlayerData data;
    
    // Color constants for different level classe
    private static final Color COLOR_TUTORIAL = Color.GREEN;
    
    /**
     * Gets the player data for this game.
     * 
     * @return
     *      a reference to the {@code PlayerData} object
     */
    public PlayerData getPlayerData()
    {
        return data;
    }
    
    public Map()
    {
        super(1200, 900, 1);
        
        // Create the fade manager.
        fader = new FadeManager(this);
        fader.fadeIn(30);
        
        // Create the global player data.
        data = new PlayerData();
        data.markAccessible(Level01.class);
        data.markAccessible(Level02.class);
        data.markAccessible(Level03.class);
        
        // Make sure to paint things in the right order.
        setPaintOrder(
            ScreenShade.class,
            MapIcon.class
        );
        
        // Set up the map.
        prepare();
    }
    
    public void act() {
        fader.act();
    }
    
    
    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        MapIcon icon01 = new MapIcon(75, COLOR_TUTORIAL, "T1", new Level01(this), data);
        addObject(icon01, 464, 794);
        
        MapIcon icon02 = new MapIcon(75, COLOR_TUTORIAL, "T2", new Level02(this), data);
        addObject(icon02, 664, 754);
        
        MapIcon icon03 = new MapIcon(75, COLOR_TUTORIAL, "T3", new Level03(this), data);
        addObject(icon03, 864, 794);
    }
}
