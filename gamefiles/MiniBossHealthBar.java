import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class MiniBossHealthBar here.
 * 
 * @author Clay Asato
 * @version (a version number or a date)
 */
public class MiniBossHealthBar extends SizeMeter
{
    int goal;
    int count = 0;
    int barWidth = 200;
    int barHeight = 6;
    int animationCounter;
    int endBarAmount;
    int barAmount;



    
    public void act() 
    {
        // Add your action code here.
        super.act();
        fillBar();
        follow();
    }    
    
    public double percentProgress()
    {
        //First get our progress and then divide by our goal. That will give a number between 0 and 1.
        Level level = (Level)getWorld();
        double percent = level.getBossHPPercent();
        //System.out.println("percent" + percent);
        return percent;
    }
    public void fillBar()
    {
        //create and assign a variable that will set %% multiplied by 1100, our max bar length
        endBarAmount = (int)(percentProgress()*(barWidth-10));
        //System.out.println("endBarAmount" + endBarAmount);
        //make an image for the bar, make it the size of barWidth and barHeight
        GreenfootImage bar = new GreenfootImage(barWidth,barHeight);
            
        //assign the color green
        Color GREEN = new Color (0,255,0);
        //Set our image to have the default color green
        bar.setColor(GREEN);
        int amtChange = barAmount - endBarAmount;
        //System.out.println("amtChange " + amtChange);
        
        //draw a filled rectangle, color it green, (at x=5,y=5 WITHIN our image, and as big as our image)
        bar.fillRect(0,0,endBarAmount,barHeight);
        //System.out.println("baAmount " + barAmount);
        //set this actor's image to the image we just created
        this.setImage(bar);        
    }
    
    public void follow()
    {
        Level level = (Level)getWorld();
        int x = level.getMiniBossX();
        int y = level.getMiniBossY();
        this.setLocation (x,y - 90);

    }

}
