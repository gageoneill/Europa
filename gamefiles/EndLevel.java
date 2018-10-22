import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color; // Colors

/**
 * Write a description of class ScoreBoard here.
 * 
 * @author Mateus Seehagen Rodrigues
 * @version 10/21/2014
 */
public class EndLevel extends Actor {
    
    private GreenfootImage text;
    private String label = "  Level\nCompleted";
    
    public EndLevel(){
        super();
        
        // Draw text
        this.text = new GreenfootImage(this.label, 60, Color.BLACK, new Color(0, 0, 0, 0));   
        
        // Set the transparency to 0, so its invisible
        this.text.setTransparency(0);
        
        // Set the actor image to be the text created
        this.setImage(text);
    }
    
    public EndLevel(String message){
        this.label = message;
        
        // Draw text
        this.text = new GreenfootImage(this.label, 40, Color.BLACK, new Color(0, 0, 0, 0));   
        
        // Set the transparency to 0, so its invisible
        this.text.setTransparency(0);
        
        // Set the actor image to be the text created
        this.setImage(text);
    }
    
    /**
     * Act - do whatever the ScoreBoard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }
}

