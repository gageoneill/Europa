import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Item here.
 * 
 * @author William Chargin
 * 
 * @version 14 October 2014: Add particle support
 * @version 7 October 2014: Initial version
 */
public class Item extends PhysicsObject
{
    private int itemTimer = 0;
    /**
     * This constructor just sets up some basic physics settings.
     */
    public Item() 
    {
        setLinearDamping(0.9);
        
    }
    private int invincibilityTimer = 0;
    /**
     * This method will be called when the item is picked up by a player.
     * You can add stuff like particle effects here.
     */
    public void onCollected() {
    }
    
    /**
     * Helper method to launch a bunch of particles of the given color.
     */
    public void launchParticles(Color baseColor)
    {
        final int numParticles = 100;
        final int particleSize = 10;
        for (int i = 0; i < numParticles; i++)
        {
            Particle p = new Particle(baseColor, particleSize);
            getWorld().addObject(p, getX(), getY());
            p.increaseVelocityPolar(5 + 3 * Math.random(), Math.random() * 360);
        }
    }
    
    public void act()
    {
        super.act();
        /**
         *  makes items unable to be shot and thus removed for 50 frames
         *  @author Gage O'Neill
         *  
         */
        invincibilityTimer ++;
        if(invincibilityTimer > 50)
        {
            checkIfShot();
        }
        itemTimer++;
        itemTimer();
    }
    /**
     * Method to destroy items on contact with shot w/ particle effect
     * @author Gage O'Neill
     * 
     */
    public void checkIfShot()
    {
        Actor shot;
        shot = getOneIntersectingObject(Shot.class);
        if(shot != null)
        {
            World world = getWorld();
            launchParticles(Color.BLACK);
            world.removeObject(this);           
        }
    } 
    /**
     * 
     * Method to remove items after certain time
     * @author Gage O'Neill
     */
    public void itemTimer()
    {
        Level world = (Level)getWorld();
        if(world != null && itemTimer > world.getItemTimer() ){            
            Color color = new Color(176, 176, 176, 80); 
            launchParticles(color);
            world.removeObject(this);            
        }
    }
}
