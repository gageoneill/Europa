import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Makes the user shrink.
 * 
 * @author William Chargin
 * 
 * @version 14 October 2014: Add particle support
 * @version 7 October 2014: Initial version
 */
public class Shrink extends Item
{
    public void onCollected()
    {
        // Launch red-ish particles.
        launchParticles(new Color(200, 30, 30, 150));
        new GreenfootSound("powerdown.wav").play();
    }
}
