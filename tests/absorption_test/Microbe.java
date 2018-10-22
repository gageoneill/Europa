import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;

/**
 * Write a description of class Microbe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Microbe extends Actor
{
    private Color color;
    private int radius;
    
    public Microbe(Color color, int radius) {
        this.color = color;
        this.radius = radius;
        updateImage();
    }
    
    public Microbe() {
        this(Color.RED, 10);
    }
    
    private void updateImage() {
        final int diameter = radius * 2;
        GreenfootImage image = new GreenfootImage(diameter, diameter);
        image.setColor(color);
        image.fillOval(0, 0, diameter, diameter);
        setImage(image);
    }
    
    public void setColor(Color color) {
        this.color = color;
        updateImage();
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setRadius(int radius) {
        this.radius = radius;
        updateImage();
    }
    
    public int getRadius() {
        return radius;
    }
    
}
