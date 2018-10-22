import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level01 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DemoLevel extends World
{

    /**
     * Constructor for objects of class Level01.
     * 
     */
    public DemoLevel()
    {
        super(600, 600, 1, false);
        
        // Add two objects.
        int x = getWidth() / 3;
        int y = getHeight() / 2;
        addObject(new Player1(), x * 1, y);
        addObject(new Player2(), x * 2, y);
    }
    
    public void act() {
        // Move objects that are outside the world back to the center.
        for (Object o : getObjects(null)) {
            if (o instanceof Actor) {
                Actor a = (Actor) o;
                
                // If x < 0 or x > width or y < 0 or y > height, you're outside the world.
                if (a.getX() < 0 || a.getX() > getWidth() ||
                        a.getY() < 0 || a.getY() > getHeight()) {

                    // Move to center.
                    a.setLocation(getWidth() / 2, getHeight() / 2);
                }
            }
        }
    }
}
