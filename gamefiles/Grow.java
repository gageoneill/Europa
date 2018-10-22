import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Makes the user grow.
 * 
 * @author William Chargin
 * 
 * @version 14 October 2014: Add particle support
 * @version 7 October 2014: Initial version
 */
public class Grow extends Item
{
    public void onCollected()
    {
        // Launch green-ish particles.
        launchParticles(new Color(30, 220, 30, 150));
        new GreenfootSound("powerup.wav").play();
    }
}
