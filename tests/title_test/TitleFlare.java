import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A completely gratuitous lens flare effect. This object will follow
 * a {@link Title} object. It will fade in over {@value #FADE_FRAME_MAX}
 * frames, and pulsate with a period of {@value #OSCILLATION_PERIOD} frames.
 */
public class TitleFlare extends Actor
{
    private final Title target;
    
    private static final int X_OFFSET = -145;
    private static final int Y_OFFSET = 77;
    
    private int fadeFrame = 0;
    public static final int FADE_FRAME_MAX = 60;
    
    private int oscillationFrame = 0;
    public static final double OSCILLATION_PERIOD = 160;
    public static final double OSCILLATION_FACTOR = 0.2;
    
    /**
     * Creates a title flare that tracks the given title.
     */
    public TitleFlare(Title target) {
        this.target = target;
    }
    
    @Override
    public void addedToWorld(World world) {
        goToTitle();
        getImage().setTransparency(0);
    }
    
    /**
     * Moves this object to the title object it is following.
     */
    public void goToTitle() {
        setLocation(target.getX() + X_OFFSET, target.getY() + Y_OFFSET);
    }
    
    /**
     * Moves to the title object and fades in.
     */
    public void act() 
    {
        goToTitle();
        fade();
    }    
    
    /**
     * Adjusts the transparency of this object to create a "fading" effect.
     */
    private void fade() {
        double alpha = Math.pow((double) fadeFrame / (double) FADE_FRAME_MAX, 2);
        double t = (double) oscillationFrame / (double) OSCILLATION_PERIOD * Math.PI * 2;
        alpha *= 1 - (Math.cos(t) / 2 + 0.5) * OSCILLATION_FACTOR;
        getImage().setTransparency((int) Math.round(alpha * 255));
        
        if (fadeFrame < FADE_FRAME_MAX) {
            fadeFrame++;
        }
        oscillationFrame++;
    }
}
