import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    public void setVelocityPolar(double speed, double angleDegrees)
    {
        final double angleRadians = Math.toRadians(angleDegrees);
        vx = speed * Math.cos(angleRadians);
        vy = speed * Math.sin(angleRadians);
        setRotation((int) angleDegrees);
    }
    /**
     * Sets the velocity by keeping the same speed but moving at a new angle.
     */
    public void setHeading(double angleDegrees)
    {
        setVelocityPolar(Math.hypot(vx, vy), angleDegrees);
    }
    
    public void act() 
    {
        integratePhysics();
        checkBoundary();
    }
    
    private void checkBoundary()
    {
        final double X = getWorld().getWidth(), Y = getWorld().getHeight();
        if (x < 0 || x > X || y < 0 || y > Y)
        {
            getWorld().removeObject(this);
        }
    }
    
    private double x;
    private double y;
    private double vx;
    private double vy;
    @Override
    public void setLocation(int x, int y)
    {
        super.setLocation(x, y);
        this.x = x;
        this.y = y;
    }
    public void setLocation(double x, double y)
    {
        super.setLocation((int) x, (int) y);
        this.x = x;
        this.y = y;
    }
    private void integratePhysics()
    {
        setLocation(x + vx, y + vy);
    }
    
    public double getVelocityX()
    {
        return vx;
    }
    public double getVelocityY()
    {
        return vy;
    }
}
