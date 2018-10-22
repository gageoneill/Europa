import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
public class Player extends Microbe
{
    {
        setColor(Color.BLUE);
        setRadius(20);
    }
    
    public void act() {
        int dx = 0, dy = 0;
        if (Greenfoot.isKeyDown("left")) {
            dx--;
        }
        if (Greenfoot.isKeyDown("right")) {
            dx++;
        }
        if (Greenfoot.isKeyDown("up")) {
            dy--;
        }
        if (Greenfoot.isKeyDown("down")) {
            dy++;
        }
        
        final int speed = 3;
        dx *= speed;
        dy *= speed;
        setLocation(getX() + dx, getY() + dy);
    }
}
