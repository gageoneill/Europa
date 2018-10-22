import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The shot fired by players. Removes itself after a certain number of frames.
 * 
 * @author William Chargin
 * 
 * @version 7 October 2014: Initial version
 */
public class Shot extends PhysicsObject
{
    /** The lifetime of this bullet; number of frames before auto-deletion. */
    private int lifetime;
    
    /** The number of frames since this bullet was fired. */
    private int frame = 0;
    
    public Shot(){
        this.lifetime = 20;
    }

    public Shot(int lifetime){
        this.lifetime = lifetime;
    }

    /**
     * Acts according to standard physics, or deletes itself when its life has expired.
     */
    public void act() {
        super.act();
        bounce();
        frame++;
        if (frame >= lifetime) {
            getWorld().removeObject(this);
        }
    }
    private void bounce()
    {           
        int halfwidth = getImage().getWidth()/2;
        
        if (getX() <= halfwidth || getX() >= getWorld().getWidth() - halfwidth)
        {
            setVelocity( -getVelocityX(), getVelocityY() );
             setRotation(180-getRotation());
        }
        int halfheight = getImage().getHeight()/2;
        if  (getY() <= halfheight || getY() >= getWorld().getHeight() - halfheight)
        {
            setVelocity(getVelocityX(),-getVelocityY());
            setRotation(-getRotation());
        }
         
    }
  
}
