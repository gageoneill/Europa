import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Level01 extends Level
{
    private Player player;
    
    /**
     * Constructor for objects of class Level01.
     * 
     */
    public Level01(Map map)
    {
        super(map);
        prepare();
    }
    
    @Override
    public void prepare() {
        player = new Player();
        addObject(player, 590, 602);
        
        Grow grow = new Grow();
        addObject(grow, 433, 417);
        Grow grow2 = new Grow();
        addObject(grow2, 589, 356);
        Grow grow3 = new Grow();
        addObject(grow3, 757, 427);
        Shrink shrink = new Shrink();
        addObject(shrink, 410, 296);
        Shrink shrink2 = new Shrink();
        addObject(shrink2, 594, 249);
        Shrink shrink3 = new Shrink();
        addObject(shrink3, 775, 292);
        Grow grow4 = new Grow();
        addObject(grow4, 909, 208);
        Grow grow5 = new Grow();
        addObject(grow5, 717, 150);
        Grow grow6 = new Grow();
        addObject(grow6, 482, 158);
        Grow grow7 = new Grow();
        addObject(grow7, 258, 251);
        
        KeyW w = new KeyW();
        addObject(w, 600, 100);
        KeyMouse mouse = new KeyMouse();
        addObject(mouse, -100, -100);
        
        ObjectiveMessage objective = new ObjectiveMessage("GROW FOUR SIZES");
        addObject(objective, getWidth() / 2, getHeight() * 7 / 8);
    }
    
    /**
     * Advances to the next level if the player has reached the target size.
     * 
     * @author Clay Asato
     * 
     * @version 14 October 2014: Make completion return to map
     * @version 14 October 2014: Initial version
     */
    public void act()
    {
        // When finish T1...
        if (player.getSize() >= 150)
        {
            // okay to go to T2.
            getMap().getPlayerData().markAccessible(Level02.class);
            returnToMap();
        }
    }
}
