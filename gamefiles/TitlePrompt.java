import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The "press enter to play" prompt on the title screen.
 * 
 * @author William Chargin
 * 
 * @version 3 October 2014: Initial version
 */
public class TitlePrompt extends Actor
{
    /**
     * The number of frames that have gone by since the last cycle of fading.
     */
    private int fadeTick = 0;
    
    /**
     * The number of frames after which the fading should repeat.
     */
    private final int fadePeriod = 40;
    
    @Override
    public void act() {
        // Calculate the current position in the fading cycle.
        double t = (double) fadeTick / fadePeriod;
        
        // Set the image transparency.
        getImage().setTransparency(255 - (int) (127.5 * (Math.cos(t * Math.PI * 2) + 1)));
        
        // Increase the frame count, but wrap around if necessary.
        fadeTick = (fadeTick + 1) % fadePeriod;
    }
}
