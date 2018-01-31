import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main player in the game.
 * 
 * @author William Chargin
 * 
 * @version 12 October 2014: Switch to procedurally generated sprite.
 * @version 8 October 2014: Add absorbPowerups method.
 * @version 7 October 2014: Initial version
 */
public class Player extends PhysicsObject
{
    
    /**
     * The acceleration from pressing a key.
     * This might be changed by buffs/debuffs.
     */
    private double keyAcceleration = 1.0;
    
    /**
     * The current size of this player.
     */
    private int size = 50;
    
    /**
     * The number of frames this object has left to wait before firing.
     */
    private int fireCooldown = 0;
    
    /** The number of frames to wait before firing. */
    private static final int FIRE_COOLDOWN_MAX = 10;
    
    /** The speed at which shots are fired. */
    private static final int FIRE_SPEED = 25;

    /** The speed at which the player recoils from a shot. */
    private static final int FIRE_RECOIL = 5;
    
    /** The radius inside of which to ignore movement commands (prevents atan2 singularity). */
    private static final int MOUSE_CONTROL_THRESHOLD = 25;
    
    ///////////////////////////////////////////////////////////////////////////
    // Key bindings
    ///////////////////////////////////////////////////////////////////////////
    private static final String KEY_FIRE = "d";
    private static final String KEY_FORWARD = "w";
    private static final String KEY_REVERSE = "s";
    
    /**
     * Creates the player, setting up physics settings.
     */
    public Player() {
        setMaxSpeed(10);
        setLinearDamping(0.95);
        setSize(size);
    }
    
    /**
     * Calls a few other methods; look there for more info.
     */
    public void act() 
    {
        super.act();
        move();
        face();
        fireIfPossible();
        absorbPowerups();
    }
    
    /**
     * Fires a shot if (a) the fire key is pressed, and (b) the cooldown has elapsed.
     * This should be called every frame for the timer to work properly.
     */
    private void fireIfPossible() {
        // Make sure we can fire
        if (fireCooldown > 0) {
            fireCooldown--;
            return;
        }
        
        if (Greenfoot.isKeyDown(KEY_FIRE)) {
            // Create and add Shot
            Shot s = new Shot();
            s.setRotation(getPreciseRotation()); // face the same way as this object
            getWorld().addObject(s, getX(), getY());
            
            // Move at the same speed as this object, plus firing velocity
            s.setVelocity(getVelocityX(), getVelocityY());
            s.increaseVelocityForward(FIRE_SPEED);
            
            // Recoil on this object
            increaseVelocityForward(-FIRE_RECOIL);
            
            // Reset fire timer
            fireCooldown = FIRE_COOLDOWN_MAX;
        }
    }
    
    /**
     * Moves the player foward or backward according to keyboard events.
     */
    private void move() {
        // Move forward and backward
        if (Greenfoot.isKeyDown(KEY_FORWARD)) {
            increaseVelocityForward(keyAcceleration);
        }
        if (Greenfoot.isKeyDown(KEY_REVERSE)) {
            increaseVelocityForward(-keyAcceleration);
        }
    }
    
    /**
     * Rotates the player to face the mouse.
     */
    private void face() {
        // Turn to face mouse
        final MouseInfo mi = Greenfoot.getMouseInfo();
        
        if (mi != null) {
            final double distanceToMouse = Math.hypot(mi.getX() - getX(), mi.getY() - getY());
            if (distanceToMouse < MOUSE_CONTROL_THRESHOLD) {
                // Vectors become singular or low-precision, stuff gets screwed up. Bail.
                return;
            }
            
            // Face mouse.
            turnTowards(mi.getX(), mi.getY());
        }
        
    }
    
    /**
     * Absorbs any powerups currently touching the player.
     */
    private void absorbPowerups() {
        Item grow = (Item) getOneIntersectingObject(Grow.class);
        if (grow != null) {
            grow.onCollected();
            getWorld().removeObject(grow);
            increaseSizeBy(25);
        }
        
        Item shrink = (Item) getOneIntersectingObject(Shrink.class);
        if (shrink != null) {
            shrink.onCollected();
            getWorld().removeObject(shrink);
            if (size > 25) {
                increaseSizeBy(-25);
            }
        }
    }
    
    /**
     * Increases the size of this player by the given amount.
     * 
     * @param deltaSize
     *      the change in size (positive value &rarr; grow; negative value &rarr; shrink)
     */
    public void increaseSizeBy(int deltaSize) {
        this.size += deltaSize;
        setSize(this.size);
    }
    
    /**
     * Sets the absolute size of this player.
     * 
     * @param newSize
     *      the new size value, in pixels
     */
    public void setSize(int newSize) {
        GreenfootImage img = new GreenfootImage(newSize, newSize / 2);
        
        // Draw a right-facing triangle on the image.
        int[] x = { 0, newSize, 0 };
        int[] y = { 0, newSize / 4, newSize / 2 };
        img.fillPolygon(x, y, 3);
        
        setImage(img);
    }
    
    /**
     * Gets the player's current size.
     * 
     * @return
     *      the current size
     */
    public int getSize()
    {
        return size;
    }
}
