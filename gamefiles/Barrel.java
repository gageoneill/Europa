    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class barrel here.
 * 
 * @author (Jaskaran Buttar and William Chargin) 
 * @version (a version number or a date)
 */
public class Barrel extends PhysicsObject
{
    private final double explosionRadius = 150;
    
    public Barrel()
    {
        
        setNoBounds(true);
    }
     public void launchParticles(Color baseColor)
    {
        final int numParticles = 75;
        final int particleSize = 12;
        for (int i = 0; i < numParticles; i++)
        {
            Particle p = new Particle(baseColor, particleSize);
            getWorld().addObject(p, getX(), getY());
            p.increaseVelocityPolar(10 + 3 * Math.random(), Math.random() * 360);
        }
    }
    public void act()
    {
        setLocation(getX(), getY() + 1);
        setVelocity(0, 2);
         Actor shot;
        shot = getOneIntersectingObject(Shot.class);
        if(shot != null)
        {
            explode();
        }
    }
    
    public void explode()
    {
        Greenfoot.playSound("explosion.wav");
            
            World world = getWorld();
            launchParticles(new Color(255, 255, 0, 160));
            launchParticles(new Color(255, 127, 0, 160));
            launchParticles(new Color(255, 0, 0, 160));
            
            for (Object o : world.getObjects(MiniBoss3.class))
            {
                MiniBoss3 b = (MiniBoss3) o;
                double distance = Math.hypot(b.getX() - getX(), b.getY() - getY());
                if (distance < explosionRadius)
                {
                    b.decrementHP();
                }
            }
            for (Object o : world.getObjects(Player.class))
            {
                Player p = (Player) o;
                double distance = Math.hypot(p.getX() - getX(), p.getY() - getY());
                if (distance < explosionRadius)
                {
                    p.die();
                }
            }
            for (Object o : world.getObjects(NPC.class))
            {
                NPC n = (NPC) o;
                if (o instanceof Boss)
                {
                    return;
                }
                double distance = Math.hypot(n.getX() - getX(), n.getY() - getY());
                if (distance < explosionRadius)
                {
                    world.removeObject(n);
                    ((Level) world).getPlayerData().enemiesKilled++;
                }
            }
            
            world.removeObject(this);
    }
}