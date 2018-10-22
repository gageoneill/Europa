import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Shrink here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shrink extends NPC
{
    public void act()
    {
        setLocation(getX(), getY() + 1);
        super.act();
        turn();
    }
    public void turn()
    {
        move(1);
        if (Greenfoot.getRandomNumber(100) < 10)
        {
            turn(Greenfoot.getRandomNumber(180) - 90);
        }
    }
    {
        setColor(Color.RED);
    }
    public void onCollisionWith(Player player) {
        player.setRadius(player.getRadius() / 2 + 1);
    } 
}