import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * A level in the Europa game.
 * 
 * @author William Chargin
 * 
 * @verison 23 October 2014: Added level completed message and apply to heritage (Mateus)
 * @version 14 October 2014: Add map linkage and reset functionality
 * @version 3 October 2014: Initial version
 */
public abstract class Level extends World
{
    
    /**
     * The map associated with this level.
     */
    private final Map map;
    private EndLevel el;
    private FadeManager fm;
    public SizeBar sb;
    public SizeBarContainer sbc = new SizeBarContainer();
    private boolean flag = true;
    protected boolean goToNext;
    protected boolean levelComplete;
    int sizeGoal;
    String shieldKey = "A";
    final Shield s = new Shield();

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
    
    /** Convenience method. */
    public PlayerData getPlayerData()
    {
        return map.getPlayerData();
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
        goToNext = false;
        levelComplete = false;
        
        Player p = getPlayer();
        if (p != null)
        {
            p.setSize(50);
        }
        
        SizeBar sb = new SizeBar(sizeGoal);
        addObject(sb,600,850);

        addObject(sbc,600,855);
        sbc.drawContainer();
        
        //creates shield object
        addObject(s,0,0);
        s.resetSize();
        //tells world draw the shield above everything
        setPaintOrder(Shield.class, SizeBar.class);
        
        el = new EndLevel();
        el.getImage().setTransparency(0);
        addObject(el, getWidth() / 2, getHeight()/2);
        
        fm = new FadeManager(this);
        fm.fadeIn(30);
        flag = true;

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
    
    /**
     * Runs in every frame to make the fades os the level
     */
    public void act(){
        if(levelComplete){
            el.getImage().setTransparency(255);
            if (flag){
                fm.fadeOut(150);
                flag = false;
            }
        }
        checkForShield();
        fm.act();
        if(fm.isFadedOut()){
            goToNext = true;
        }
        testMode();
        checkReturnToMap();
    }
    
    public abstract double getCompletionPercentage();
    public abstract double getBossHPPercent();
    public abstract int getMiniBossX();
    public abstract int getMiniBossY();
    
    public Player getPlayer()
    {
        List players = getObjects(Player.class);
        return players.isEmpty() ? null : (Player) players.get(0);
    }
    /**
     * checks if the level is complete
     * @author Gage O'Neill
     */
    public boolean checkLevelComplete()
    {
        if(levelComplete == true){
            return true;
        }else{
            return false;
        }
    }
    /**
     *  item timer
     */
    public int getItemTimer()
    {
        return 400;
    }
    /**
     * method checkForShield() has the world check everything the shield needs every frame
     * @author Clay Asato
     */
    public void checkForShield()
    {
        if (this instanceof Level01 || this instanceof Level02) {
            // Can't shield in levels 1 or 2.
            return;
        }
        //declare variable for player
        Player p = getPlayer();
        //makes the max size of the shield = p // NOT IMPLEMENTED YET
        int shieldMaxSize = p.getSize();
        // TRUE/FALSE value for if the key (its f now) is pressed.
        boolean keyPressed = Greenfoot.isKeyDown(shieldKey);
        
        s.setMaxSize(300);
        //System.out.println(keyPressed);
        if(keyPressed)
        {
            s.setShieldActive();
        }
        else
        {
            s.setShieldInactive();
        }
        int playerXPos = p.getX();
        int playerYPos = p.getY();
        s.receivePlayerPositions(playerXPos, playerYPos);
        /*if(s.active)
        {
            p.setVisibility(false);
        }
        else
        {
            p.setVisibility(true);
        }*/
    }
    public boolean isShieldActive()
    {
        return s.active;
    }
    
    /**
     * dropEnemy method will spawn an enemy on demand, takes the enemy type, location, and the tracking variable.
     * @author Clay Asato
     */
    public void dropEnemy(int enemyType, int xLoc, int yLoc, boolean isTracking)
    {
        NPC e;
        switch (enemyType)
        {
            case 1:
            e = new Enemy1();
            break;
            case 2:
            e = new Enemy2();
            break;
            case 3:
            e = new Enemy3(isTracking);
            break;
            case 4:
            e = new Enemy4(isTracking);
            break;
            default:
            e = new Enemy1();
            break;
        }
        
        addObject(e, xLoc, yLoc);
    }
    
    public void testMode()
    {
        if (Greenfoot.isKeyDown("H"))
        {
            Player p = getPlayer();
            p.setSize(sizeGoal);
        }
    }
    public void checkReturnToMap()
    {
        if (Greenfoot.isKeyDown("escape"))
        {
            returnToMap();
        }
    }

}
