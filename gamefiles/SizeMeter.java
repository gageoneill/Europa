import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A bar that indicates the current size (and thus level progress) of the player.
 * 
 * @author Clay Asato
 */
public class SizeMeter extends Actor
{
    int barAmount;
    int endBarAmount;
    public void act() 
    {
        // Add your action code here.
        
        
    }   

    
    private void fillBar()
    {
        // TODO
        return;
    }
    public int animateBarFill(int amountChanging)
    {
        //this will change the bar amount every frame and smooth out the bar when it's filling.
        int speed = amountChanging/5;
        barAmount = barAmount+speed;
        if ((barAmount - endBarAmount) > -2 && (barAmount - endBarAmount) < 2)
        {
            barAmount = endBarAmount;
        }
        return barAmount;
    }
}
