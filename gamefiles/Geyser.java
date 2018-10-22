import greenfoot.*;
import java.awt.Color;
/**
 * 
 * @author Gage O'Neill
 * @version 11 November 2014
 * 
 */
public class Geyser extends Actor
{
    public Geyser()
    {
        getImage().setTransparency(0);
    }    
    public void act() 
    {
        launchParticles(new Color(0, 50, 200, 180));
        launchParticles(new Color(150, 150, 255, 127));
    }       
    public void launchParticles(Color baseColor)
    {
        final int numParticles = 50;
        final int particleSize = 10;
        for (int i = 0; i < numParticles; i++)
        {
            Particle p = new Particle(baseColor, particleSize);
            getWorld().addObject(p, getX(), getY() + Greenfoot.getRandomNumber(800)-300);
            p.increaseVelocityPolar(5 + 3 * Math.random(), Math.random() * 360);
        }
    }
}
