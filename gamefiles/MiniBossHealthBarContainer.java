import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiniBossHealthBarContainer here.
 * 
 * @author Clay Asato
 * @version (a version number or a date)
 */
public class MiniBossHealthBarContainer extends SizeMeter
{
    /**
     * Act - do whatever the MiniBossHealthBarContainer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        drawContainer();
    }    
    public void drawContainer()
    {
        MiniBossHealthBar mbhb = new MiniBossHealthBar();
        GreenfootImage barContainer = new GreenfootImage(mbhb.barWidth+4,60);
        barContainer.drawRect(2,2,mbhb.barWidth+2, mbhb.barHeight+2);
        this.setImage(barContainer);
    }
}
