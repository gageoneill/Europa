import greenfoot.*;
import java.util.List;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.geom.RoundRectangle2D;

/**
 * 
 * @author Gage O'Neill
 * @version 4 November 2014
 * 
 */
public class Wall extends PhysicsObject
{
    private int width;
    
    public void act() 
    {
        super.act();
        checkObjects();
    }   
    public Wall(int width, double rotation)
    {
        this.width = width;
        paintImage(getImage(), width);
        setRotation((int) rotation);
        setNoBounds(true);
    }
    
    private void paintImage(GreenfootImage base, int width)
    {
        final int height = 16;
        GreenfootImage image = new GreenfootImage(width, height);
        Graphics2D g2d = (Graphics2D) image.getAwtImage().getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D rect = new RoundRectangle2D.Double(1, 1, width - 2, height - 2, height - 2, height - 2);
        g2d.setClip(rect);
        g2d.drawImage(base.getAwtImage(), 0, 0, width, height, null);
        g2d.setColor(Color.BLACK);
        g2d.setClip(null);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(rect);
        g2d.dispose();
        setImage(image);
    }
    
    /**
     * 
     * Checks whether an object collides with a wall, adjusts angle and velocity of the object accordingly
     * 
     */
    public void checkObjects()
    {
        List list = getIntersectingObjects(PhysicsObject.class);
        for(Object o : list)
        {
            PhysicsObject object = (PhysicsObject) o;
            if(object != null && !(object instanceof Wall) && !(object instanceof EnemySpawner))
            {
                double theta = this.getPreciseRotation();
                double thetarad = Math.toRadians(theta);
                double dot = Math.abs((object.getLastX() - getX()) * Math.cos(thetarad) + (object.getLastY() - getY()) * Math.sin(thetarad));
                if (dot >= width / 2 - 1)
                {
                    object.move(-15);
                    object.setVelocity(-object.getVelocityX(), -object.getVelocityY());
                }
                else
                {
                    //set rotation
                    double phi = Math.toDegrees(Math.atan2(object.getVelocityY(), object.getVelocityX()));
                    double newPhi = 2 * (theta) - phi;
                    object.setRotation(newPhi);
                    //adjust velocity
                    double speed = object.getSpeed();
                    object.setVelocity(0, 0);
                    object.increaseVelocityForward(speed);
                    object.move(10);
                    if (object instanceof Player)
                    {
                        object.setRotation(phi);
                    }
                }
                
                if (object instanceof NPC)
                {
                    object.stun();
                }
            }
        }
    }
}
