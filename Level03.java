import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level01 here.
 * 
 * @author William Chargin
 * 
 * @version 7 October 2014: Initial version
 */
public class Level03 extends Level
{

    /**
     * Constructor for objects of class Level01.
     * 
     */
    public Level03(Map map)
    {
        super(map);
        prepare();
    }
    
    @Override
    public void prepare() {
        // Calculate screen center.
        final int cx = getWidth() / 2, cy = getHeight() / 2;
        
        // Add a player at the center of the screen.
        addObject(new Player(), cx, cy);
        
        // Add some enemies, in a circle around the center.
        // (Yep---trig.)
        int numEnemies = 20;
        int enemyRadius = 250;
        for (int i = 0; i < numEnemies; i++) {
            double theta = Math.PI * 2 * i / numEnemies;
            int x = (int) (cx + enemyRadius * Math.cos(theta));
            int y = (int) (cy + enemyRadius * Math.sin(theta));
            addObject(new Enemy3(), x, y);
        }
    }
}
