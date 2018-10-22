import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitlePlanet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitlePlanet extends Actor
{
    
    private double x;
    private double speed;
    
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        this.x = getX();
    }
    
    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        this.x = x;
    }
    
    public void act() {
        super.act();
        x += speed;
        super.setLocation((int) Math.round(x), getY());
        
        double w = getImage().getWidth() / 2.0;
        double min = -w, max = getWorld().getWidth() + w;
        if (x < min) {
            x = max;
        } else if (x > max) {
            x = min;
        }
    }
    
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
