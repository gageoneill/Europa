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
public abstract class NPC extends PhysicsObject implements Reflectable
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

    public void act()
    {
        super.act();
        
         if (getX() <= 7 || getX() >= getWorld().getWidth() - 7)
       {
           turn(180);
       }
       if (getY() <=5 || getY() >= getWorld().getHeight() - 5)
       {
           turn(180);
       }
       checkMyDeath();
    }
    
    public void checkMyDeath()
    {
        
        if (isTouching(Shot.class)) {
            // Drop an item.
            World w = getWorld();
            
            ((Level) w).getPlayerData().enemiesKilled++;

            // Get a random type of item to drop: either 0 or 1.
            new GreenfootSound("death.wav").play();
            int type = Greenfoot.getRandomNumber(2); // Z[0, 2) == Z[0, 1]
            if (type == 0) {
                dropItem(new Grow());
            } else {
                dropItem(new Shrink());
            }

            // Die.
            w.removeObject(this);
            return;
        }
    }
}