import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutSceneSkip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CutSceneSkip extends Actor
{
    private boolean isFadingOut = true;
    
    /**
     * Act - do whatever the CutSceneSkip wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(isFadingOut){
            getImage().setTransparency(getImage().getTransparency() - 5);
        }
        else{   // FadingIn
            getImage().setTransparency(getImage().getTransparency() + 5);
        }
        
        if (getImage().getTransparency() <= 0){
            isFadingOut = false;
        }
        if (getImage().getTransparency() >= 255){
            isFadingOut = true;
        }
    }    
}
