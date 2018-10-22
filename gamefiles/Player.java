import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;


/**
 * The main player in the game.
 * 
 * @author William Chargin & Mateus Seehagen Rodrigues
 * 
 * @version 06 November 2014: Added PlayerData and variable caracteristics.
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

    private PlayerData playerData;

    /** The number of frames to wait before firing. */
    private static final int FIRE_COOLDOWN_MAX = 10;

    /** The speed at which shots are fired. */
    private static final int FIRE_SPEED = 25;

    /** The radius inside of which to ignore movement commands (prevents atan2 singularity). */
    private static final int MOUSE_CONTROL_THRESHOLD = 25;
    
    /** The original image (scaled down for different sizes). */
    private final GreenfootImage originalImage = new GreenfootImage("hero.png");
    
    /** The garbage collection&ndash;amenable cache of images of different sizes. */
    private static final Map<Integer, SoftReference<GreenfootImage>> imageCache = new HashMap<Integer, SoftReference<GreenfootImage>>();

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
        updateImage();
        setLinearDamping(0.95);
    }

    public void updateSpeed()
    {
        Level l = (Level) getWorld();
        if (l == null)
        {
            return;
        }
        this.playerData = l.getPlayerData();
        setMaxSpeed(playerData.getSpeed());
        setLinearDamping(-0.1 / 4 * (playerData.getSpeed() - 10) / 2 + 0.95);
        keyAcceleration = 1.0 + 0.5 * (playerData.getSpeed() - 10) / 2;
    }
    
    /**
     * Calls a few other methods; look there for more info.
     */
    public void act() 
    {
        super.act();
        //if(getOneObjectInFront(Wall.class)==null)
        move();
        face();
        fireIfPossible();
        absorbPowerups();
        boundary();
        updateSpeed();
        checkShot();
        checkDeath();
        //checkObstacle();
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
        if (getWorld() instanceof Level01) {
            // Can't fire in tutorial 1.
            return;
        }

        if (Greenfoot.isKeyDown(KEY_FIRE)) {
            // Create and add Shot
            Shot s = new Shot(playerData.getShotRange());
            s.setRotation(getPreciseRotation()); // face the same way as this object
            getWorld().addObject(s, getX(), getY());

            // Move at the same speed as this object, plus firing velocity
            s.setVelocity(getVelocityX(), getVelocityY());
            s.increaseVelocityForward(FIRE_SPEED);

            // Recoil on this object
            increaseVelocityForward(playerData.getRecoil());

            // Reset fire timer
            fireCooldown = FIRE_COOLDOWN_MAX;
            
            // Play sound
            new GreenfootSound("fire.wav").play();
            
            // Increase shot count
            playerData.shotsFired++;
        }
    }

    /**
     * Moves the player foward or backward according to keyboard events.
     */
    private void move() {
        // Move forward and backward
        if (Greenfoot.isKeyDown(KEY_FORWARD)) {
            MouseInfo mi = Greenfoot.getMouseInfo();
            double fac = 1;
            if (mi != null)
            {
                double distance = Math.hypot(getX() - mi.getX(), getY() - mi.getY());
                if (distance < MOUSE_CONTROL_THRESHOLD)
                {
                    fac = Math.pow(distance / MOUSE_CONTROL_THRESHOLD, 2);
                }
            }
            if (fac < 0.1)
            {
                fac = 0;
            }
            increaseVelocityForward(keyAcceleration);
            setVelocity(fac * getVelocityX(), fac * getVelocityY());
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
        Enemy1 e1 = (Enemy1) getOneIntersectingObject(Enemy1.class);
        if (shrink  != null) {
            shrink.onCollected();
            getWorld().removeObject(shrink);
            if (size > 25) {
                increaseSizeBy(-25);
            }

        }
        Item testItem = (Item) getOneIntersectingObject(TestItem.class);
        if (testItem != null)
        {
            increaseSizeBy(200);
            getWorld().removeObject(testItem);
            
        }
    }

    /**
     * Increases the size of this player by the given amount.
     * 
     * @param deltaSize
     *      the change in size (positive value &rarr; grow; negative value &rarr; shrink)
     */
    public void increaseSizeBy(int deltaSize) {
        setSize(this.size + deltaSize);
    }

    /**
     * Sets the absolute size of this player.
     * 
     * @param newSize
     *      the new size value, in pixels
     */
    public void setSize(int newSize) {
        if (this.size == newSize)
        {
            // No need to redo stuff
            return;
        }
        this.size = newSize;
        updateImage();
    }
    
    /**
     * Updates the image to match the current size. If the current size has been reached before, the
     * previous image will be used. Otherwise, or if the image has been garbage collected, a new image
     * will be drawn and cached.
     */
    private void updateImage()
    {
        synchronized (imageCache)
        {
            SoftReference<GreenfootImage> ref = imageCache.get(size);
            boolean regen = false;
            if (ref != null)
            {
                GreenfootImage img = ref.get();
                if (img != null)
                {
                    setImage(img);
                    return;
                }
            }
            
            // Need to redraw image
            Image bi = originalImage.getAwtImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            GreenfootImage img = new GreenfootImage(size, size);
            img.getAwtImage().getGraphics().drawImage(bi, 0, 0, null);
            
            imageCache.put(size, new SoftReference<GreenfootImage>(img));
            
            setImage(img);
        }
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

    /**
     * 
     * Restarts the level once a player touches an NPC prior to the level being completed
     * 
     * @author Gage O'Neill
     */
    public void checkDeath()
    {
        Actor npc = getOneIntersectingObject(NPC.class);
        if(npc != null)
        {
            die();
        }
    }
    
    public void die()
    {
        Level level = (Level) getWorld();
        if (!level.checkLevelComplete())
        {
            playerData.deathCount++;
            level.resetWorld();
        }
    }
    
    /**
     * Checks whether the player has been hit by an enemy shot and restarts the level
     * 
     *  @author Gage O'Neill
     */
    public void checkShot()
    {
        Actor enemyshot;
        enemyshot = getOneIntersectingObject(EnemyShot.class);
        if(enemyshot != null)
        {
            die();
        }
    }
}
