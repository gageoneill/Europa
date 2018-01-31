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
    
    /**
     * Creates a new enemy, setting up physics.
     */
    public Enemy3() {
        setAngularDamping(0.9);
        setMaxAngularSpeed(10);
        setMaxSpeed(2);
        setRotation(Greenfoot.getRandomNumber(360));
        increaseVelocityPolar(1, getRotation());
    }
    
    /**
     * Moves somewhat randomly, weighted toward the center of the screen.
     * If touching a player shot, destroy self and drop an item.
     */
    public void act() {
        super.act();
        
        // Move randomly...
        increaseAngularVelocity((Math.random() * 2) - 1);
        increaseVelocityPolar(1, getPreciseRotation());
        // ...but don't stray too far from the center.
        increaseVelocity((getWorld().getWidth() / 2 - getX()) * 0.002, (getWorld().getHeight() / 2 - getY()) * 0.002);
    
        if (isTouching(Shot.class)) {
            // Drop an item.
            World w = getWorld();
            
            // Get a random type of item to drop: either 0 or 1.
            int type = Greenfoot.getRandomNumber(2); // Z[0, 2) == Z[0, 1]
            if (type == 0) {
                dropItem(new Grow());
            } else {
                dropItem(new Shrink());
            }
            
            // Die.
            w.removeObject(this);
        }
    }    
}
