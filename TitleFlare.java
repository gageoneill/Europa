import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The lens flare on the title screen. Fades in, then does nothing.
 * Always follows a TitleText actor.
 * 
 * @author William Chargin
 * 
 * @version 3 October 2014: Initial version
 */
public class TitleFlare extends Actor
{
    /**
     * The title that this flare follows.
     */
    private final TitleText title;
    
    // Offset parameters: x = title.x + offsetX, y = title.y + offsetY
    private final int offsetX = -155;
    private final int offsetY = 78;
    
    /** The number of frames over which to fade in. */
    private final int fadeLength = 40;
    
    /** The current frame in the fading animation. */
    private int fadeFrame = 0;
    
    /**
     * Creates a TitleFlare to follow the given TitleText object.
     * 
     * @param title
     *      the title to follow
     */
    public TitleFlare(TitleText title) {
        this.title = title;
    }
    
    /**
     * Moves this object to match up with the title object that it is tracking.
     */
    private void moveToTitle() {
        setLocation(title.getX() + offsetX, title.getY() + offsetY);
    }
    
    /**
     * Adjusts this flare's opacity according to the fade in frame.
     */
    private void fadeIn() {
        if (fadeFrame <= fadeLength) {
            getImage().setTransparency(255 * fadeFrame / fadeLength);
            fadeFrame++;
        }
    }
    
    @Override
    public void act() {
        moveToTitle();
        fadeIn();
    }
    
    @Override
    public void addedToWorld(World w) {
        // As soon as we're added to the world, we want to jump to the title.
        moveToTitle();
        
        // Also, start completely transparent.
        // Otherwise, you'll see a flash before the flare fades in.
        getImage().setTransparency(0);
    }
}
