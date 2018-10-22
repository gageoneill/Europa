import greenfoot.*;
import java.awt.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shield here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shield extends PhysicsObject
{
    
    //int stm -- the maximum size that the shield can be. Goes by Percent size of image; just a visual representation of the value of time.
    //note: size does not correlate to the radius of protection.
    int sizeTimerMax = 100;
    //int sizetimer -- the current size/percent of the shield. will decrement every ACTIVE frame. will incrment every NONACTIVE frame.
    int sizeTimer = 100;
    //int activeshrinkrate -- the shrink rate of the sizetimer per frame the shield IS USED
    int activeShrinkRate;
    //int passiveGrow rate -- the amount to grow every frame that shield is NOT USED.
    int passiveGrowRate;
    //boolean active -- the value of the shield itself, true ->> shied is ON, false ->> shield is OFF.
    boolean active;
    int maxSize;
    int currentSize;
    GreenfootImage img;
    int incrementTimer = 0;
    int incrementTimerCap = 5;
    int playerXLoc;
    int playerYLoc;
    
    

    /**
     * Act - do whatever the Shield wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        img = new GreenfootImage(Math.max(1,2*currentSize),Math.max(1,2*currentSize));
        super.act();
        stickToPlayer();
        setShield();
        rechargeShield();
        this.setImage(img);
        deflectObjects();
    }
    

    //setShieldActive() method will set up the image for the shield. it MUST update every time, so it's in the act() method.
    public void setShieldActive()
    {
        active = true;
    }
    
    public void setShield()
    {
        //note to self: if time > 0 look up radial gradient paint in java
        Color COLOR = new Color(255,0,0);
        img.setColor(COLOR);
        //set currentSize int to the current value of (maxsize*timer/100) @@ in other words, the size that we will make the circle
        currentSize = (maxSize*sizeTimer)/100;

        
        if(active && sizeTimer >=0)
        {
            
            
            //ask greenfoot image to make a circle(oval with equal size vert/horiz) and set it to the current size of the shield.
            img.setTransparency(90);
            //set the timer to decrement 1% every frame
            sizeTimer--;
        }
        else
        {
            img.setTransparency(0);
        }

        //System.out.println(sizeTimer);
        //int offset = maxSize - currentSize;
        //Note to self: last two params in fillOval are DIAMETERS NOT RADII. make = to 2r
        img.fillOval(0,0,2*currentSize,2*currentSize);
        
    }
    //meant to be called by the world in order to pass through the current size of the player.
    public void setMaxSize(int size)
    {
        maxSize = size;
    }
    
    public void receivePlayerPositions(int xpos, int ypos)
    {
        playerXLoc = xpos;
        playerYLoc = ypos;
    }
    public void stickToPlayer()
    {
        this.setLocation(playerXLoc,playerYLoc);
    }
    public void setShieldInactive()
    {
        active = false;
    }
    private void rechargeShield()
    {
        if(sizeTimer < sizeTimerMax)
        {
            incrementTimer++;
            //System.out.println("INCREMENT TIMER " + incrementTimer);
            if(incrementTimer>=incrementTimerCap && sizeTimer<100)
            {
                sizeTimer++;
                incrementTimer = 0;
            }
            //System.out.println("SIZE TIMER " + sizeTimer);
        }
    }
   
    private void deflectObjects()
    {
        if(active)
        {
            final int x = getX(), y = getY();
            for (Object object : getWorld().getObjects(Reflectable.class))
            {
                PhysicsObject r = (PhysicsObject) object;
                if (isInRange(r))
                {
                    bounceProjectiles(r);
                    r.stun();
                }
            }
        }
    }
    
    private boolean isInRange(PhysicsObject r)
    {
        double heading = Math.atan2(r.getVelocityY(), r.getVelocityX());
        double halfLength = r.getImage().getWidth() * 0.5;
        
        double x = r.getX() + halfLength * Math.cos(heading);
        double y = r.getY() + halfLength * Math.sin(heading);
        
        return Math.hypot(x - getX(), y - getY()) <= currentSize;
    }
    
    private void bounceProjectiles(PhysicsObject r)
    {
        
        //get projectile's original heading
        double heading = Math.atan2(r.getVelocityY(), r.getVelocityX());
        double normal =  Math.atan2(r.getY() - getY(), r.getX() - getX()); 
        //calculate the angle of the TAN line @@ intersecting point
        double tan = normal + Math.PI / 2;
        //get the old speed 
        double vectorSpeed = Math.hypot(r.getVelocityY(), r.getVelocityX());
        //calculate the new angle of our reflectable
        r.setRotation(Math.toDegrees(2* tan - heading));
        //give the objecti its speed back w/ new rotation
        r.setLocation(getX()+(currentSize + (r.getImage().getWidth()/2))*Math.cos(normal), getY() + (currentSize + (r.getImage().getWidth()/2))*Math.sin(normal));
        r.setVelocity(0,0);
        r.increaseVelocityForward(vectorSpeed + 3);
    }
    public void resetSize()
    {
        sizeTimer = 100;
    }
}
