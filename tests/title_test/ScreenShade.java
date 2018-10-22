import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class ScreenShade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScreenShade extends Actor
{
    public void addedToWorld(World world) {
        GreenfootImage im = new GreenfootImage(world.getWidth(), world.getHeight());
        im.setColor(Color.BLACK);
        im.fill();
        setImage(im);
        setLocation(world.getWidth() / 2, world.getHeight() / 2);
    }
    
    public void setTransparency(int transparency) {
        getImage().setTransparency(transparency);
    }
    
}
