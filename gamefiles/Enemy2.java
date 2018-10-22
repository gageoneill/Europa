import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy2 here.
 * 
 * @author Gage O'Neill
 * @version (a version number or a date)
 */
public class Enemy2 extends NPC
{
    int counter = 0;
    public Enemy2()
    {
        setMaxSpeed(2);
    }    
    public void act() 
    {
        super.act();
        if(counter > 150)
        {
            trackPlayer();           
        }
        counter ++;
    }      
}
