import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * All NPC characters should extend this class.
 * It provides some useful helper methods that NPCs can use.
 * 
 * @author William Chargin
 * 
 * @version 14 October 2014: Strip down to make more semantic sense (move code to Enemy3)
 * @version 7 October 2014: Initial version
 */
public abstract class NPC extends PhysicsObject
{

    /**
     * Helper method to emit an item at the current location.
     * For example, invoke as "dropItem(new Grow())" to drop a growth powerup.
     * 
     * @param item
     *      the item to drop
     */
    protected void dropItem(Item item) {
        // Add the item here.
        World w = getWorld();
        w.addObject(item, getX(), getY());
        
        // Move at some speed in a random direction.
        final double speed = 3;
        item.increaseVelocityPolar(speed, Greenfoot.getRandomNumber(360));
    }
    
}
