import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    /**
     * This constructor just sets up some basic physics settings.
     */
    public Item() {
        setLinearDamping(0.9);
    }
    
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
    
}
