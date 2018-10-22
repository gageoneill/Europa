import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemySpawner here.
 * 
 * @author Jaskaran Buttar and Gage O'Neill 
 * @version (a version number or a date)
 */
public class EnemySpawner extends PhysicsObject
{
    private int enemyType;
    int timerCap = 150;
    private double angleStart = 0;
    private double angleRange = 360;
    private double velocity = 12;
    public EnemySpawner(int enemy)
    {
        enemyType = enemy;
    }
    public void increaseReplacementCount()
    {
        replacementCount++;
    }
    public void placeEnemy()
    {
        NPC e = null;
        if (enemyType == 1)
        {
            e = new Enemy1();
        }
        else if (enemyType == 2)
        {
            e = new Enemy2();
        }
        else if (enemyType == 3)
        {
            e = new Enemy3(true);
        }
        else if (enemyType == 4)
        {  
            e = new Enemy4(true);
        }
        
        getWorld().addObject(e, 600,200);
        e.setLocation(getX(), getY());
        e.turn(Greenfoot.getRandomNumber((int)angleRange) + (int)angleStart);
        e.move(Greenfoot.getRandomNumber(150)+50);
        e.increaseVelocityForward(velocity);
        //super.setRotation(theta);
        
    }
    public int getReplacementCount()
    {
        return replacementCount;
    }
    public void setReplacementCount(int a)
    {
        replacementCount = a;
        
    }
    
    /**
     * Act - do whatever the EnemySpawner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int timer = 0;
    public int replacementCount = 0;
    public void act() 
    {
       super.act();
       respawn();
       timer++;
    }    
    public void respawn()
    {
        if (timer >= timerCap && replacementCount >= 1)
        {
            placeEnemy();
            replacementCount--;
            timer = 0;
        }
    }
    public void setAngleStart(double angleStart)
    {
        this.angleStart = angleStart;
    }
    public void setAngleRange(double angleRange)
    {
        this.angleRange = angleRange;
    }
    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }
        
}