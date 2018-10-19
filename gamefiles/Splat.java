import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Splat here.
 * 
 * @author no one yet!
 */
public class Splat extends PhysicsObject
{
    private int lifetime = 30;
    private int frame = 0;
    
    public void act()
    {
        int alpha = (int) (255.0 * (lifetime - frame) / lifetime);
        // TODO actually set the opacity
        // look in the GreenfootImage documentation for how to do this
    }
}
