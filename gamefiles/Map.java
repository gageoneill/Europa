import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;

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
    /**
     * ^ ^ v v < > < > B A START
     */
    public static final boolean HAUNGS_MODE = true;
    
    private FadeManager fader;
    
    /**
     * The player data for this game.
     */
    private PlayerData data;
    
    // Color constants for different level classe
    private static final Color COLOR_TUTORIAL = new Color(0x66, 0x66, 0xFF);
    private static final Color COLOR_LEVEL1 = new Color(0x33, 0xEF, 0x00);
    private static final Color COLOR_LEVEL2 = new Color(0xCC, 0xCC, 0x00);
    private static final Color COLOR_LEVEL3 = new Color(0xCC, 0x66, 0x00);
    private static final Color COLOR_FINAL = new Color(0xFF, 0x00, 0x00);
    
    private KeyS s = new KeyS();
    
    /**
     * 
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
        if (HAUNGS_MODE)
        {
            data.markAccessible(Level02.class);
            data.markAccessible(Level03.class);
            data.markAccessible(Level04.class);
            data.markAccessible(Level05.class);
            data.markAccessible(Level06.class);
            data.markAccessible(Level07.class);
            data.markAccessible(Level08.class);
            data.markAccessible(Level09.class);
            data.markAccessible(Level10.class);
            data.markAccessible(Level11.class);
            data.markAccessible(Level12.class);
            data.markAccessible(Level13.class);
        }
        
        addObject(s, 1125, 65);
        
        // Make sure to paint things in the right order.
        setPaintOrder(
            ScreenShade.class,
            IncreaseIcon.class,
            UpgradesGUI.class,
            MapIcon.class
        );
        
        // Set up the map.
        prepare();
    }
    
    public void act() {
        fader.act();
        if (Greenfoot.isKeyDown("s"))
        {
            Greenfoot.setWorld(new StatisticsDisplay(data, this));
        }
    }
    
    
    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        MapIcon icon01 = new MapIcon(75, COLOR_TUTORIAL, "i", new Level01(this), data);
        addObject(icon01, 350, 800);
        
        MapIcon icon02 = new MapIcon(75, COLOR_TUTORIAL, "ii", new Level02(this), data);
        addObject(icon02, 600, 750);
        
        MapIcon icon03 = new MapIcon(75, COLOR_TUTORIAL, "iii", new Level03(this), data);
        addObject(icon03, 850, 800);
        
        MapIcon icon04 = new MapIcon(80, COLOR_LEVEL1, "I", new Level04(this), data);
        addObject(icon04, 300, 600);
        
        MapIcon icon05 = new MapIcon(80, COLOR_LEVEL1, "II", new Level05(this), data);
        addObject(icon05, 600, 550);
        
        MapIcon icon06 = new MapIcon(80, COLOR_LEVEL1, "III", new Level06(this), data);
        addObject(icon06, 900, 600);
        
        MapIcon icon07 = new MapIcon(85, COLOR_LEVEL2, "IV", new Level07(this), data);
        addObject(icon07, 250, 400);
        
        MapIcon icon08 = new MapIcon(85, COLOR_LEVEL2, "V", new Level08(this), data);
        addObject(icon08, 600, 350);
        
        MapIcon icon09 = new MapIcon(85, COLOR_LEVEL2, "VI", new Level09(this), data);
        addObject(icon09, 950, 400);
        
        MapIcon icon10 = new MapIcon(90, COLOR_LEVEL3, "VII", new Level10(this), data);
        addObject(icon10, 200, 200);
        
        MapIcon icon11 = new MapIcon(90, COLOR_LEVEL3, "VIII", new Level11(this), data);
        addObject(icon11, 600, 175);
        
        MapIcon icon12 = new MapIcon(90, COLOR_LEVEL3, "IX", new Level12(this), data);
        addObject(icon12, 1000, 200);
        
        MapIcon icon13 = new MapIcon(100, COLOR_FINAL, "X", new Level13(this), data);
        addObject(icon13, 600, 50);
        
        UpgradeIcon upi = new UpgradeIcon(data);
        addObject(upi, 130, 50);
        
    }
}
