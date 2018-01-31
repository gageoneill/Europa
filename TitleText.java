import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The title text for the main menu.
 * 
 * @author William Chargin
 * @version 3 October 2014: Initial version
 */
public class TitleText extends Actor
{
    /**
     * The amount that this object will move on the y-axis.
     */
    public final int movement = -100;
    
    /**
     * The starting position of the title, used to determine the position at each frame.
     */
    private double originalY;
    
    /**
     * The number of frames over which the fade-in animation will take place.
     */
    private int animationLength = 80;
    
    /**
     * The speed at which the title animates upward. Should be at least 4.0, or the title
     * will appear to stop suddenly.
     */
    private double movementSpeedFactor = 4.5;
    
    /**
     * The current frame number in the animation.
     */
    private int frame = 0;
    
    /**
     * The delay before adding the TitleFlare; a flare will be added on this frame.
     */
    private int flareDelay = 60;
    
    /**
     * Initializes the title text, completely transparent.
     */
    public TitleText() {
        // Start faded out
        getImage().setTransparency(0);
    }
    
    /**
     * Animates the title text by fading it in and moving it upward.
     */
    public void act() 
    {
        // If the animation is done, do nothing.
        if (frame > animationLength) {
            return;
        }
        
        // Fade in.
        int alpha = (int) (255.0 * frame / animationLength);
        getImage().setTransparency(alpha);
        
        // Move up.
        double totalDisplacement = movement *
            (1 - Math.exp(-1.0 * movementSpeedFactor * frame / animationLength));
        super.setLocation(getX(), (int) Math.round(originalY + totalDisplacement));
        
        // Do we need to add a flare now?
        if (frame == flareDelay) {
            // Do so.
            final TitleFlare flare = new TitleFlare(this);
            getWorld().addObject(flare, getX(), getY());
        }
        
        // Advance the frame number.
        frame++;
    }    
    
    /**
     * Moves this title to the given location.
     * The location will be considered the starting point of the animation:
     * if the animation is already underway, you may see an immediate jump.
     */
    public void setLocation(int x, int y) {
        this.originalY = y;
        super.setLocation(x, y);
    }
}
