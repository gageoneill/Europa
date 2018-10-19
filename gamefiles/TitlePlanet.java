import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * One of the planets on the title screen.
 * This class automatically handles moving and wrapping.
 * <p>
 * Because planets need to move more slowly than one pixel per frame, this class
 * keeps track of how far "between" pixels the planet is.
 * 
 * @author William Chargin
 * 
 * @version 3 October 2014: Initial version
 */
public class TitlePlanet extends Actor
{
    /**
     * The speed (in px/frame) at which this planet should move horizontally.
     */
    private double speed;
    
    /**
     * This planet's partial x position: x - floor(x).
     */
    private double partialX;
    
    /**
     * Sets the speed of this planet.
     * 
     * @param speed
     *      the new speed, in px/frame
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    /**
     * Moves this planet according to its speed, and wraps if necessary.
     */
    public void act() {
        // Move horizontally.
        partialX += speed;
        setLocation(getX() + (int) partialX, getY());
        partialX = partialX - (int) partialX;
        
        // Wrap, if we're off the edge of the screen.
        wrap();
    }    
    
    /**
     * Wraps the planet around the screen if it is off the edge.
     */
    private void wrap() {
        // Get information about our environment.
        final GreenfootImage img = getImage();
        final World world = getWorld();
        
        // This value will be useful for calculations.
        final int halfWidth = img.getWidth() / 2;
        
        // Calculate bounds.
        final int xmin = -halfWidth;
        final int xmax = world.getWidth() + halfWidth;
        
        // Check to see if we're outside the bounds.
        if (getX() < xmin) {
            // Too far to the left.
            setLocation(xmax, getY());
        } else if (getX() > xmax) {
            // Too far to the right.
            setLocation(xmin, getY());
        }
    }
}
