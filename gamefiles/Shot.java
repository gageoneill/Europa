import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The shot fired by players. Removes itself after a certain number of frames.
 * 
 * @author William Chargin
 * 
 * @version 7 October 2014: Initial version
 */
public class Shot extends PhysicsObject
{
    /** The lifetime of this bullet; number of frames before auto-deletion. */
    private int lifetime = 20;
    
    /** The number of frames since this bullet was fired. */
    private int frame = 0;
    
    /**
     * Acts according to standard physics, or deletes itself when its life has expired.
     */
    public void act() {
        super.act();
        frame++;
        if (frame >= lifetime) {
            getWorld().removeObject(this);
        }
    }
    
}
