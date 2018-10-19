import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class automatically manages fading in and out.
 */
public class FadeManager
{
    /**
     * The duration of the complete fading animation, in frames.
     */
    private int duration;
    
    /**
     * The current frame in the animation.
     */
    private int frame;
    
    /**
     * Whether the scene is fading in ({@code true}) or out ({@code false}).
     */
    private boolean fadingIn = true;
    
    /**
     * The world that will be fading in and out.
     */
    private final World world;
    
    /**
     * The screen shade used by this {@code FadeManager}.
     */
    private final ScreenShade shade;
    
    /**
     * Creates a fade manager associated with the given world.
     * 
     * @param world
     *      the world to fade in and out
     */
    public FadeManager(World world) {
        this.world = world;
        this.shade = new ScreenShade();
        
        world.addObject(shade, world.getWidth() / 2, world.getHeight() / 2);
    }
    
    /**
     * This method should be called once per frame by the world associated with this FadeManager.
     */
    public void act() {
        if (frame <= duration) {
            int value = 255 * frame / duration;
            if (fadingIn) {
                shade.setOpacity(255 - value);
            } else {
                shade.setOpacity(value);
            }
            frame++;
        }
    }
    
    /**
     * Begins fading in the screen over the given number of frames.
     * 
     * @param duration
     *      the duration of the fade to begin
     */
    public void fadeIn(int duration) {
        this.duration = duration;
        this.frame = 0;
        this.fadingIn = true;
    }
    
    /**
     * Begins fading out the screen over the given number of frames.
     * 
     * @param duration
     *      the duration of the fade to begin
     */
    public void fadeOut(int duration) {
        this.duration = duration;
        this.frame = 0;
        this.fadingIn = false;
    }

    /**
     * Immediately fades the screen in entirely; i.e., the screen will not be covered.
     */
    public void cutIn() {
        this.duration = -1;
        this.frame = 0;
        this.fadingIn = false;
    }
    
    /**
     * Immediately fades the screen out entirely; i.e., the screen will appear black.
     */
    public void cutOut() {
        this.duration = -1;
        this.frame = 0;
        this.fadingIn = false;
    }
    
    /**
     * Determines if the screen was fading in and has now finished.
     * 
     * @return
     *      whether the screen was fading in and has now finished
     */
    public boolean isFadedIn() {
        return fadingIn && frame > duration;
    }
    
    /**
     * Determines if the screen was fading out and has now finished.
     * 
     * @return
     *      whether the screen was fading out and has now finished
     */
    public boolean isFadedOut() {
        return !fadingIn && frame > duration;
    }
}
