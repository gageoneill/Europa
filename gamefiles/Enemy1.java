import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy1 here.
 * 
 * @author (Jaskaran Buttar) 
 * @version (a version number or a date)
 */
public class Enemy1 extends NPC
{
    /**
     * Act - do whatever the Enemy1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int speed = Greenfoot.getRandomNumber(5)+2;
    public void act() 
    {
        move(2);
        turn(speed);
       
        super.act();
    }
    public Enemy1()
    {
        setLinearDamping(.97);
    }
}