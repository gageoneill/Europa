import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * A basic circular shield that deflects projectiles.
 * 
 * @author William Chargin
 * @version 20 November 2014
 */
public class Shield extends Actor
{
    /** This is the actual size of the shield. */
    private int size;
    
    // These two values are used to animate the shield size.
    // They're totally irrelevant to the actual collision detection.
    private int mu;
    private int t;
    
    public Shield(int size)
    {
        this.mu = size;
        this.size = size;
        paintImage();
    }
    public void act()
    {
        animateSize();
        deflectObjects();
    }
    
    private void deflectObjects()
    {
        /*
         * Instead of using the built-in collision detection, we can loop over
         * all projectiles and check each one manually. This way, we'll be able
         * to compare to the actual circle (by checking distance from center),
         * instead of just the square bounding box.
         */
        final int x = getX(), y = getY();
        for (Object object : getWorld().getObjects(Projectile.class))
        {
            Projectile projectile = (Projectile) object;
            if (isInRange(projectile))
            {
                processCollision(projectile);
            }
        }
    }
    
    private boolean isInRange(Projectile projectile)
    {
        /*
         * We'd like to check to see if the projectile is within range.
         * However, the (x, y) position of the projectile is its center point.
         * We can use some trigonometry to find the forward-most point.
         */
        
        // Get some information about the geometry of the object.
        double heading = Math.atan2(projectile.getVelocityY(), projectile.getVelocityX());
        double halfLength = projectile.getImage().getWidth() * 0.5;
        
        // Calculate the (x, y) position of the front of the projectile.
        double x = projectile.getX() + halfLength * Math.cos(heading);
        double y = projectile.getY() + halfLength * Math.sin(heading);
        
        // Get the distance from this (x, y) to the center of the shield.
        // If it's less than (or equal to) the radius, we have a collision.
        return Math.hypot(x - getX(), y - getY()) <= size / 2;
    }

    private void processCollision(Projectile projectile)
    {
        // Find the projectile's heading.
        double heading = Math.atan2(projectile.getVelocityY(), projectile.getVelocityX());
        
        // Find the angle of the tangent line.
        double tan = Math.atan2(projectile.getY() - getY(), projectile.getX() - getX()) + Math.PI / 2;
        
        // Calculate new angle.
        projectile.setHeading(Math.toDegrees(2 * tan - heading));
    }
    
    private void animateSize()
    {
        // Update the value of 'size' according to the animation.
        final int maxt = 60;
        size = (int) (mu * (Math.sin(Math.PI * 2 * t / maxt) * 0.5 * 0.5 + 1));
        
        // Advance, and loop if necessary.
        t++;
        if (t > maxt)
        {
            t = 0;
        }
        
        // Set the new image.
        paintImage();
    }
    private void paintImage()
    {
        GreenfootImage img = new GreenfootImage(size, size);
        Graphics2D g2d = (Graphics2D) img.getAwtImage().getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color inner = new Color(255, 0, 0, 16);
        Color center = new Color(255, 0, 0, 32);
        Color outer = new Color(255, 0, 0, 127);
        float radius = size * 0.5f;
        g2d.setPaint(new java.awt.RadialGradientPaint(
            radius, radius,
            radius,
            new float[]{ 0, 0.5f, 1 },
            new Color[] { inner, center, outer })
        );
        g2d.fillOval(0, 0, size, size);
        g2d.dispose();
        setImage(img);
    }
}
