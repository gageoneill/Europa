import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemySpawnerMoving here.
 * 
 * Follows all the rules of Enemy Spawner, but tracks the player within certain boundaries
 * 
 * @author Clay Asato 
 * @version (a version number or a date)
 */
public class EnemySpawnerMoving extends EnemySpawner
{
    public EnemySpawnerMoving(int enemy)
    {
        super(enemy);
        setMaxSpeed(0.1);
    }
    /**
     * Act - do whatever the EnemySpawnerMoving wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        followPlayer();
    }   
    public void followPlayer()
    {
        if (getWorld() == null)
        {
            return;
        }
        Level lv = (Level)getWorld();       
        Player p = lv.getPlayer();
        World w = getWorld();
        if(lv.getPlayer() != null)
        {
            int playerXLocation = p.getX();
            int playerYLocation = p.getY();
            turnTowards(playerXLocation, playerYLocation);
            int xOffset = getX()-(w.getWidth()/2);
            int yOffset = getY()-(w.getHeight()/2);
           //int distanceFromCenter = Math.hypot(xOffset,yOffset);
            increaseVelocityPolar(1, this.getRotation());
            //int distanceFromCenter = 0;
            /*
            if (distanceFromCenter < 500)
            {
                 increaseVelocityPolar(1, this.getRotation());
            }
            else
            {
                //move Randomly
                increaseAngularVelocity((Math.random() * 2) - 1);
                increaseVelocityPolar(1, getPreciseRotation());
                // ...but don't stray too far from the center.
                //increaseVelocity((getWorld().getWidth() / 2 - getX()) * 0.002, (getWorld().getHeight() / 2 - getY()) * 0.002);
            
            }*/
        }

    }
            public void setTimerCap(int amount)
        {
            this.timerCap = amount;
        }
}
