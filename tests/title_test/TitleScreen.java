import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    private ScreenShade shade;
    
    private int fadeFrame = 0;
    private static final int fadeFrameMax = 30;
    
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 900, 1, false);
        Greenfoot.setSpeed(50);
        addObject(new Title(), getWidth() / 2, getHeight() / 3 - Title.TOTAL_DISPLACEMENT);

        addPlanets();
        
        shade = new ScreenShade();
        addObject(shade, 0, 0);
        
        setPaintOrder(ScreenShade.class, Title.class, TitleFlare.class, TitlePlanet.class);
    }
    
    public void act() {
        if (shade == null) {
            return;
        }
        double alpha = 1 - (double) fadeFrame / (double) (fadeFrameMax);
        shade.setTransparency((int) Math.round(255 * alpha));
        fadeFrame++;
        if (fadeFrame >= fadeFrameMax) {
            removeObject(shade);
            shade = null;
        }
    }
    
    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void addPlanets()
    {
        Mars mars = new Mars();
        addObject(mars, 1016, 253);
        Earth earth = new Earth();
        addObject(earth, 190, 107);
        mars.setLocation(1046, 174);
        mars.setLocation(1049, 162);
    }
}
