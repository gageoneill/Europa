import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DemoWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DemoWorld extends World
{

    int timer;
    
    /**
     * Constructor for objects of class DemoWorld.
     * 
     */
    public DemoWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 600, 1, false); 
        prepare();
    }
    
    private void prepare()
    {
        addObject(new Shield(150), getWidth() / 2, getHeight() / 2);
    }
    
    public void act()
    {
        timer++;
        if (timer % 30 == 0)
        {
            Projectile p = new Projectile();
            double theta = Math.random() * Math.PI * 2;
            double r = 250;
            addObject(p,
                getWidth() / 2 + (int) (r * Math.cos(theta)),
                getHeight() / 2 + (int) (r * Math.sin(theta)));
            p.setVelocityPolar(-(Math.random() * 8 + 2), Math.toDegrees(theta + Math.random() * 0.5 - 0.25));
        }
    }
}
