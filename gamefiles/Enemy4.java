import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * 
 * @author Gage O'Neill 
 * @version December 4 2014
 * 
 */
public class Enemy4 extends NPC
{
    boolean isTracking;
    int shotTimer = 0;
    public Enemy4(boolean track)
    {
        isTracking = track;
        setAngularDamping(0.8);
        setMaxAngularSpeed(6);
        setMaxSpeed(.4);
    }
    public void act() 
    {
        super.act();
        if(isTracking)
        {
            trackPlayer();
        }
        shoot();
    }  
    public void shoot()
    {
        if (getWorld() == null)
        {
            return;
        }
        shotTimer++;
        if(shotTimer == 75)
        {           
            EnemyShot es = new EnemyShot();
            es.setRotation(getPreciseRotation());
            getWorld().addObject(es, getX(), getY());
            
            es.setVelocity(getVelocityX(), getVelocityY());
            es.increaseVelocityForward(12);
            shotTimer = 0;
        }
    }
}
