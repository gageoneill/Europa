import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * 
 * @author Gage O'Neill 
 * @version December 12 2014
 * 
 */
public class FinalBoss extends Boss
{
    int shotTimer = 0;
    public FinalBoss()
    {
        chanceOfSpawn = 100;
        setMaxSpeed(8);
    }
    public void act() 
    {
        super.act();
        aimAtPlayer();
        shoot();    
        spawn();
    } 
    public void shoot()
    {
        if(getWorld() == null)
        {
            return;
        }
        shotTimer++;
        if(shotTimer == 50)
        {
            BossShot bs1 = new BossShot(50);
            bs1.setRotation(getPreciseRotation());
            getWorld().addObject(bs1, getX(), getY() + 20);
            bs1.setVelocity(getVelocityX(), getVelocityY());
            bs1.increaseVelocityForward(12);
            
            BossShot bs2 = new BossShot(50);
            bs2.setRotation(getPreciseRotation() + 15);
            getWorld().addObject(bs2, getX(), getY() + 20);
            bs2.setVelocity(getVelocityX(), getVelocityY());
            bs2.increaseVelocityForward(12);
            
            BossShot bs3 = new BossShot(50);
            bs3.setRotation(getPreciseRotation() - 15);
            getWorld().addObject(bs3, getX(), getY() + 20);
            bs3.setVelocity(getVelocityX(), getVelocityY());
            bs3.increaseVelocityForward(12);
            
            shotTimer = 0;
        }
    }
    public void aimAtPlayer()
    {
        if(getWorld() == null)
        {
            return;
        }
        Level lv = (Level)getWorld();       
        Player p = lv.getPlayer();
        if(lv.getPlayer() != null)
        {
            int playerXLocation = p.getX();
            int playerYLocation = p.getY();
            turnTowards(playerXLocation, playerYLocation);
        }
    }
    public void orbit()  
    {  
        
    }  
    // overrides inherited move behavior
    public void move()
    {
        if(this.getX() <= 200)
        {
            turn(180);
        }
        if(this.getX() >= 1000)
        {
            turn(180);
        }
    }
    private void spawn()
    {
        Level level = (Level) getWorld();
        int randnum = Greenfoot.getRandomNumber(1500);
        if(randnum == 250)
        {
            level.dropEnemy(2, this.getX(), this.getY(), true);
        }
        if(randnum == 500)
        {
            level.dropEnemy(3, this.getX(), this.getY(), true);
        }
        if(randnum == 750)
        {
            level.dropEnemy(4, this.getX(), this.getY(), true);
        }
    }
}
