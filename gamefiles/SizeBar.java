import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class SizeBar here.
 * 
 * @author Clay Asato
 * @version (a version number or a date)
 */
public class SizeBar extends SizeMeter
{
    /**
     * Act - do whatever the SizeBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int goal;
    int count = 0;
    int barWidth = 1100;
    int barHeight = 40;
    int animationCounter;
    int endBarAmount;
    int barAmount;

    
    public SizeBar(int goalForThisLevel)
    {
         goal = goalForThisLevel;
    }

    
    public void act() 
    {
        // Add your action code here.
        fillBar();
    }    
    
    public double percentProgress()
    {
        //First get our progress and then divide by our goal. That will give a number between 0 and 1.
        Level level = (Level)getWorld();
        double percent = level.getCompletionPercentage();
        //System.out.println(percent);
        return percent;
    }
    public void fillBar()
    {
        //create and assign a variable that will set %% multiplied by 1100, our max bar length
        endBarAmount = (int)(percentProgress()*(barWidth-10));
        //System.out.println(endBarAmount);
        //make an image for the bar, make it the size of barWidth and barHeight
        GreenfootImage bar = new GreenfootImage(barWidth,barHeight);
            
        //assign the color green
        Color GREEN = new Color (0,255,0);
        //Set our image to have the default color green
        bar.setColor(GREEN);
        int amtChange = endBarAmount - barAmount;
        //System.out.println("amtChange " + amtChange);
        barAmount = animateBarFill(amtChange);
        //draw a filled rectangle, color it green, (at x=5,y=5 WITHIN our image, and as big as our image)
        bar.fillRect(5,5,barAmount,barHeight);
        //System.out.println("baAmount " + barAmount);
        //set this actor's image to the image we just created
        this.setImage(bar);        
    }

    

}
