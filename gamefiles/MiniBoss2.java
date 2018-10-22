import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * 
 * 
 * @author Clay Asato
 * @version (a version number or a date)
 */
public class MiniBoss2 extends Boss
{
    public MiniBoss2()
    {
        setLinearDamping(.97);
        setMaxSpeed(1);
        charge = false;
        aimTimer = 0;
        chanceOfSpawn = 200;
    }
    int aimTimer = 0;
    boolean charge;
    int xOffsetFromPlayer;
    int yOffsetFromPlayer;
    int chargeCoolDownTimer = 0;
    boolean aiming;
    public void act() 
    {
        // Add your action code here.
        super.act();
        if (getWorld() == null)
        {
            return;
        }
        moveRandom();
        aim();
        coolDown(aimTimer);
        System.out.println("aiming" + aiming);
        System.out.println("Charge" + charge);
    }
    public void moveRandom()
    {
        //Random movement program
        if(!charge)
        {
            setMaxSpeed(1);
            // Move randomly...
            increaseAngularVelocity((Math.random() * 2) - 1);
            increaseVelocityPolar(1, getPreciseRotation());
            // ...but don't stray too far from the center.
            increaseVelocity((getWorld().getWidth() / 2 - getX()) * 0.002, (getWorld().getHeight() / 2 - getY()) * 0.002);
        }
        int randNum = Greenfoot.getRandomNumber(400);
        if(randNum  == 50 && chargeCoolDownTimer == 0)
        {
            charge = true;
            setMaxSpeed(10);
        }

    }
    //stop for a few seconds... aim...
    public void aim()
    {

        if(charge)
        {
            aiming = true;
            followPlayer();
            aimTimer++;
            //coolDown(aimTimer);
            goCharge(aimTimer);
        }
        else
        {
            aimTimer = 0;
        }
    }
    //charge at the player's saved location
    public void goCharge(int currentTime)
    {
        if (currentTime > 200 && currentTime < 250)
        {
            aiming = false;
            double distance = Math.hypot(xOffsetFromPlayer,yOffsetFromPlayer);
            if(distance > 14.14)
            {
                increaseVelocityForward(1.0);
            }
            else
            {
                charge = false;
            }
//             else
//             {
//                Level lv = (Level)getWorld();
//                Player p = lv.getPlayer();
//                turnTowards(p.getX(), p.getY());
//             
//             }
        }
    }
    //pause at new location
    public void coolDown(int currentTime)
    {
        chargeCoolDownTimer--;
        if (chargeCoolDownTimer < 0)
        {
            chargeCoolDownTimer = 0;
            
        }
        
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
            
            if(aiming)
            {
                turnTowards(playerXLocation, playerYLocation);
                xOffsetFromPlayer = getX()-(p.getX());
                yOffsetFromPlayer = getY()-(p.getY());
            }
            // int distanceFromCenter = Math.hypot(xOffset,yOffset);
            //increaseVelocityPolar(1, this.getRotation());
            //int distanceFromCenter = 0;

            /*if (distanceFromCenter < 500)
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
}
