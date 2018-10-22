import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * One of the more agressive enemies.
 * Should fire at player.
 * 
 * @author William Chargin
 * 
 * @version 14 October 2014: Move some code to here from NPC
 * @version 7 October 2014: Initial version
 */
public class Enemy3 extends NPC
{   
    boolean isTracking;
   
    /**
     * Creates a new enemy, setting up physics.
     */
    public Enemy3(boolean track) {
        isTracking = track;
        setAngularDamping(0.9);
        setMaxAngularSpeed(8);
        setMaxSpeed(.5);
        setRotation(Greenfoot.getRandomNumber(360));
        increaseVelocityPolar(1, getRotation());
        
    }
    
    /**
     * Moves somewhat randomly, weighted toward the center of the screen.
     * If touching a player shot, destroy self and drop an item.
     */
    public void act() {
        
        
        // Move randomly...
        increaseAngularVelocity((Math.random() * 2) - 1);
        if(isTracking)
        {
            trackPlayer();
        }
        if(!isTracking)
        {
            increaseVelocityPolar(1, getPreciseRotation());
        }
        increaseVelocityPolar(1, getPreciseRotation());
        // ...but don't stray too far from the center.
        increaseVelocity((getWorld().getWidth() / 2 - getX()) * 0.002, (getWorld().getHeight() / 2 - getY()) * 0.002);
       
        super.act();
    } 

}
