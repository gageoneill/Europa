import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;

/**
 * A message that appears on the screen, displays an objective, and fades out.
 * 
 * @author William Chargin
 * @version 14 October 2014: Initial version
 */
public class ObjectiveMessage extends Actor
{
    /** The current frame of this animation. */
    private int frame = 0;
    
    /** The total number of frames in this animation. */
    private int lifetime = 300;
    
    /** The large image that is scaled down to make each frame. */
    private GreenfootImage largeImage;
    
    /**
     * Creates a message with the given text.
     * 
     * @param text
     *      the text to display on this message
     */
    public ObjectiveMessage(String text)
    {
        largeImage = new GreenfootImage(text, 60, Color.BLACK, new Color(0, 0, 0, 0));
        setTransformedImage(0);
    }
    
    /**
     * Sets the image of this message to the large image, scaled and faded by a certain amount.
     * The value of proportion should be 0 when the animation begins and 1 when it ends.
     * 
     * @param proportion
     *      the proportion of the animation that has completed; that is, frame / lifetime
     */
    private void setTransformedImage(double proportion)
    {
        // Calculate the scale, and the new width and height.
        double scale = 0.75 + 0.25 * proportion;
        int width = (int) (largeImage.getWidth() * scale);
        int height = (int) (largeImage.getHeight() * scale);
        
        // Calculate the transparency.
        double alpha = 1 - Math.pow(proportion, 2);
        int transparency = (int) (255 * alpha);
        
        // Create an image by copying and modifying largeImage.
        GreenfootImage thisImage = new GreenfootImage(largeImage);
        thisImage.scale(width, height);
        thisImage.setTransparency(transparency);
        
        // Set this image.
        setImage(thisImage);
    }
    
    /**
     * Advances one frame and updates the image.
     * Removes self after lifetime expires.
     */
    public void act() 
    {
        setTransformedImage((double) frame / lifetime);
        frame++;
        if (frame >= lifetime)
        {
            getWorld().removeObject(this);
        }
    }
    
}
