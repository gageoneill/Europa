import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiniBoss3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniBoss3 extends Boss
{
    boolean moveFreely;
    int frameCount;
    boolean movingRight = false;
    public MiniBoss3()
    {
        shotImmunity = true;
        hpMax = 3;
        hp = hpMax;
        hpDecrement = 1;
        moveFreely = false;
        frameCount = 0;
        setLinearDamping(.9);
        setMaxSpeed(1.2);
        chanceOfSpawn = 500;
    }
    public void act() 
    {
        super.act();
    }   
   
    public void move()
    {
        
        if (getWorld() == null)
        {
            return;
        }
        
        
        if(moveFreely)
        {
            // Move randomly...
            increaseAngularVelocity((Math.random() * 2) - 1);
            increaseVelocityPolar(1, getPreciseRotation());
            // ...but don't stray too far from the center.
            increaseVelocity((getWorld().getWidth() / 2 - getX()) * 0.002, (getWorld().getHeight() / 2 - getY()) * 0.002);
       
        }
        else
        {
            if (getX() < 150)
            {
                movingRight = true;
                setRotation(0);
            }
            else if (getX() > 900)
            {
                movingRight = false;
                setRotation(180);
            }
            
            increaseVelocityForward(0.1);
            if(frameCount >= 2000)
            {
                moveFreely = true;
            }
            frameCount++;
        }
    }
}
