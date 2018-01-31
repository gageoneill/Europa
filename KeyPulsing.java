import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the pulsing image of a key that fades out over time.
 * To use this class, extend it, and set a different image.
 * 
 * @author William Chargin
 * @version 14 October 2014: Initial version
 */
public abstract class KeyPulsing extends Actor
{
    /** The current frame of the animation; the number of times "act" has been called. */
    private int frame = 0;
    
    /** The period of the pulsation; repeats every [this many] frames. */
    private int period = 40;
    
    /** The shape parameter for the exponential function used to decay this key. */
    private double lambda = 0.004;
    
    /**
     * Updates the transparency of this key to make it appear to pulse.
     */
    public void act() 
    {
        // Some basic math to get an alpha value on [0, 1].
        double t = (double) frame / period * Math.PI * 2;
        double cos = Math.cos(t) * 0.5 + 0.5;
        double alpha = cos * Math.exp(-lambda * frame);
        
        // Map R[0, 1] to Z[0, 255], and set as transparency.
        getImage().setTransparency((int) (255 * alpha));
        
        // Increase frame counter.
        frame++;
    }    
}
