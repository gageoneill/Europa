import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiniBoss here.
 * Class MiniBoss will be a subclass of NPC. 
 * it will hold the key elements/methods that specific mini bosses can call
 * 
 * 
 * @author Clay Asato
 * @version (a version number or a date)
 */
public class Boss extends NPC
{

    public Boss()
    {
        setAngularDamping(0.9);
        setMaxAngularSpeed(6);
        setMaxSpeed(.2);
        increaseVelocityPolar(1, getRotation());
        hp = hpMax;

        open = false;
    }
    /**
     * Act - do whatever the MiniBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean open;
    int hpDecrement = 50;
    int hp = 2500;
    int hpMax = 2500;
    int hitCount = 0;
    int spawnCoolDownMax = 40;
    int spawnCoolDownTime = 0;
    int hitCoolDown;
    int time = 0;
    int timerCap = 10000;
    boolean canShoot = true;
    boolean levelComplete = false;
    boolean go = false;
    boolean shotImmunity = false;
    int chanceOfSpawn;

    public void act() 
    {
        //System.out.println(levelComplete);
        // Add your action code here.
        super.act();

        spawn();
        move();
        checkHitCount();
        checkBombDrops();
        checkHitCount();
        checkMyDeath();

    }

    //method to be called every frame to decrement HP when hit with a shot
    public void checkBombDrops()
    {


        //action for when the miniboss is NOT open
        int randNum = Greenfoot.getRandomNumber(10000);
        if (randNum == 999 || time > timerCap)
        {
            setBomb();
            time = 0;
        }

        else
        {

            time++;
        }

    }
    private void checkHitCount()
    {
        if (shotImmunity)
        {
            return;
        }
        Actor shot;
        shot = getOneIntersectingObject(Shot.class);
        if(shot != null)
        {
            getWorld().removeObject(shot);
            decrementHP();
        }

        //animate hit as well
    }

    public void decrementHP()
    {
        hp = hp - hpDecrement;
        hitCount++;
    }

    @Override public void checkMyDeath()
    {
        if (hp <= 0 && getWorld() != null)
        {
            levelComplete = true;
            getWorld().removeObject(this);
        }
    }


    private void spawn()
    {
        Level level = (Level) getWorld();
        int randnum = Greenfoot.getRandomNumber(chanceOfSpawn);
        if(randnum == 0)
        {
            level.dropEnemy(3,this.getX(),this.getY(),true);
        }
    }

    public void move()//adapted from William Chargin's code
    {
        if(go)
        {
            // Move randomly...
            increaseAngularVelocity((Math.random() * 2) - 1);
            increaseVelocityPolar(1, getPreciseRotation());
            // ...but don't stray too far from the center.
            increaseVelocity((getWorld().getWidth() / 2 - getX()) * 0.002, (getWorld().getHeight() / 2 - getY()) * 0.002);
        }
    }

    private void setBomb()//written by Clay Asato
    {

    }

    public int checkHPLevel()
    {
        return hp;
    }

    public int checkMaxHP()
    {
        return hpMax;
    }
}
