import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The game logo for the title screen. This object animates in by moving
 * {@value #TOTAL_DISPLACEMENT} along the y-axis, and by fading in over
 * {@value #MAX_FRAME} frames. After {@value #FLARE_DELAY} frames, it will
 * spawn a {@link TitleFlare} object.
 */
public class Title extends Actor
{
    private int frame = 0;
    public static final int MAX_FRAME = 120;
    public static final int FLARE_DELAY = 60;
    
    private int startingY;
    public static final int TOTAL_DISPLACEMENT = -150;
    
    private TitleFlare flare;
    
    public void addedToWorld(World world) {
        startingY = getY();
        getImage().setTransparency(0);
    }
    
    /**
     * Fade in and move up.
     */
    public void act() 
    {
        if (frame >= MAX_FRAME) {
            getImage().setTransparency(255);
            return;
        }
        
        // Proportion of life
        double t = (double) frame / (double) MAX_FRAME;
        getImage().setTransparency((int) (t * 255));
        
        // Ease out movement
        double k = Math.pow(t, 0.15);
        setLocation(getX(), (int) Math.round(startingY + TOTAL_DISPLACEMENT * k));
        
        // At half-way point, add title flare.
        if (frame == FLARE_DELAY) {
            flare = new TitleFlare(this);
            getWorld().addObject(flare, getX(), getY());
        }
        
        frame++;
    }
    
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        if (flare != null) {
            flare.goToTitle();
        }
    }
}
