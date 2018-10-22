import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.awt.Color;

/**
 * Blatant eye-candy. Emitted from items when collected.
 * 
 * @author William Chargin
 * 
 * @version 14 October 2014
 */
public class Particle extends PhysicsObject
{
    /** The random number generator used for Box-Muller generation. */
    private static final Random RANDOM = new Random();
    
    /**
     * Creates a particle of roughly the given color and size.
     * Input values will be randomized a bit.
     */
    public Particle(Color color, int size)
    {
        // Modify input values.
        color = randomColor(color);
        size = randomSize(size);
        
        // Create an image and paint an oval.
        GreenfootImage image = new GreenfootImage(size, size);
        image.setColor(color);
        image.fillOval(0, 0, size, size);
        setImage(image);
        
        // Set up physics.
        setLinearDamping(0.95);
        setNoBounds(true);
    }
    
    /**
     * Has a chance of randomly removing this object.
     * This gives the illusion of fading out without creating a bajillion images.
     */
    public void act()
    {
        if (Math.random() < 0.1) {
            getWorld().removeObject(this);
        } else {
            super.act();
        }
    }
    
    /**
     * Helper method to randomly scale a color component by a lognormal factor, clamping at 255.
     */
    private int scaleColorComponent(int component)
    {
        // Use a lognormally distributed scale factor
        // The lognormal distribution has the nice property that
        //     (integral from k to 1 of f(x) dx) = (integral from 1 to 1/k of f(x) dx)
        // so you get a uniform range of scaling factors.
        
        // The parameter sigma controls the color spread.
        // If this is too high, you get a bunch of random colors.
        final double sigma = 0.05;
        
        // Do the calculation.
        return (int) Math.min(255, Math.round(component * Math.exp(sigma * RANDOM.nextGaussian())));
    }
    
    /**
     * Creates a random color that's similar to the given base color.
     * Each RGBA component will be scaled randomly by scaleColorComponent.
     * 
     * @param baseColor
     *      the starting color to be modified
     * @return
     *      a randomly generated color, similar to baseColor
     */
    private Color randomColor(Color baseColor)
    {
        int r = baseColor.getRed(), g = baseColor.getGreen(), b = baseColor.getBlue(), a = baseColor.getAlpha();
        return new Color(scaleColorComponent(r), scaleColorComponent(g), scaleColorComponent(b), scaleColorComponent(a));
    }
    
    /**
     * Calculates a random size that's similar to the given size.
     * 
     * @param baseSize
     *      the starting size to be modified
     * @return
     *      a random size, fairly close to baseSize
     */
    private int randomSize(double baseSize)
    {
        // Just as with colors, with want lognormally distributed size factors.
        final double sigma = 0.5;
        final double scale = Math.exp(sigma * RANDOM.nextGaussian());
        return (int) Math.round(baseSize * scale);
    }
    
}
