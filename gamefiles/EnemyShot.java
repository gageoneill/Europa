 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * 
 * @author Gage O'Neill
 * @version December 4 2014
 * 
 */
public class EnemyShot extends PhysicsObject implements Reflectable
{
    private int lifetime;
    private int frame = 0;
    public EnemyShot()
    {
        this.lifetime = 25;
    }
    public EnemyShot(int lifetime)
    {
        this.lifetime = lifetime;
    }
    public void act() 
    {
        super.act();
        bounce();
        frame++;
        if(frame >= lifetime)
        {
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
