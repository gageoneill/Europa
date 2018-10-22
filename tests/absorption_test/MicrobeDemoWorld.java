import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MicrobeDemoWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MicrobeDemoWorld extends World
{
    public void act()
    {
        if(Greenfoot.getRandomNumber(1000) < 20)
        {
            Shrink s = new Shrink();
            addObject(s, Greenfoot.getRandomNumber(getWidth()-20)+10, -30);
        }
        if(Greenfoot.getRandomNumber(1000) < 20)
        {
            Grow g = new Grow();
            addObject(g, Greenfoot.getRandomNumber(getWidth()-20)+10, -30);
        }
    }
    /**
     * Constructor for objects of class MicrobeDemoWorld.
     * 
     */
    public MicrobeDemoWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 

        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        Player player = new Player();
        addObject(player, 428, 191);
        Grow grow = new Grow();
        addObject(grow, 350, -100);
        Grow grow2 = new Grow();
        addObject(grow2, 422, -30);
        Shrink shrink = new Shrink();
        addObject(shrink, 272, -50);
    }
}
