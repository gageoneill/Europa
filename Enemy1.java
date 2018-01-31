import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy1 extends NPC
{
    /**
     * Act - do whatever the Enemy1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //getX(), getY() + 1;
       move(1);
       if (getX() <= 5 || getX() >= getWorld().getWidth() - 5)
{
    turn(180);
}
if (getY() <=5 || getY() >= getWorld().getHeight() - 5)
{
    turn(180);
}
    }    
}
