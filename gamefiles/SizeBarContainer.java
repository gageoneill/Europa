import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SizeBarContainer here.
 * 
 * @author Clay Asato 
 * @version (a version number or a date)
 */
public class SizeBarContainer extends SizeMeter
{
    public void act() 
    {
        
    }    
    public void drawContainer()
    {
        SizeBar sizeBar = new SizeBar(0);
        GreenfootImage barContainer = new GreenfootImage(sizeBar.barWidth+10,60);
        barContainer.drawRect(5,5,sizeBar.barWidth+4, sizeBar.barHeight+5);
        this.setImage(barContainer);
    }
}
