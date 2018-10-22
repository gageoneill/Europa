import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Rocket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rocket extends Actor
{
    /**
     * Act - do whatever the Rocket wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }
    public void launchParticles(Color baseColor)
    {
        final int numParticles = 100;
        final int particleSize = 10;
        for (int i = 0; i < numParticles; i++)
        {
            Particle p = new Particle(baseColor, particleSize);
            getWorld().addObject(p, getX(), getY());
            p.increaseVelocityPolar(5 + 3 * Math.random(), Math.random() * 360);
        }
    }
}
