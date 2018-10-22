import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * A fully black object that can fade in and out for World transitions.
 * 
 * @author William Chargin
 * @version 3 October 2014: Initial version
 */
public class ScreenShade extends Actor
{
    /**
     * The opacity level of this screen shade; 255 is fully black, and 0 is invisible.
     */
    private int opacity = 255;
    
    @Override
    public void addedToWorld(World world) {
        // Create a new image of the desired size.
        final GreenfootImage img = new GreenfootImage(world.getWidth(), world.getHeight());
        
        // Fill the image with black.
        img.setColor(Color.BLACK);
        img.fill();
        img.setTransparency(opacity);
        
        // Apply this image.
        setImage(img);
        
        // Finally, move to the center of the screen.
        setLocation(world.getWidth() / 2, world.getHeight() / 2);
    }
    
    /**
     * Sets the opacity level of this image; 255 is fully black, and 0 is invisible.
     * 
     * @param opacity
     *      the new opacity value
     */
    public void setOpacity(int opacity) {
        this.opacity = opacity;
        getImage().setTransparency(opacity);
    }
    
}