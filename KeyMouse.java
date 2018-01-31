import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An icon of a mouse that simply follows the cursor and fades out over time.
 * 
 * @author William Chargin
 * 
 * @version 14 October 2014: Initial version.
 */
public class KeyMouse extends Actor
{
    
    /** The number of frames to wait before beginning to fade out. */
    private int fadeDelay = 300;
    
    /** The number of frames over which to fade out (after the delay). */
    private int fadeLength = 100;
    
    /** The current frame number. */
    private int frame = 0;
    
    /**
     * Moves this actor to the mouse cursor, and progresses through the fade-out process.
     */
    public void act() 
    {
        MouseInfo mi = Greenfoot.getMouseInfo();
        if (mi != null)
        {
           setLocation(mi.getX(), mi.getY());
        }
        
        frame++;
        if (frame > fadeDelay) {
            getImage().setTransparency(255 - 255 * (frame - fadeDelay) / fadeLength);
            if (frame >= fadeDelay + fadeLength) {
                getWorld().removeObject(this);
            }
        }
    }    
    
   //obsolete mouseclick method to check if
   // @author Clay Asato
   // @version 14 October 2014: Initial version
// private boolean isClicking(int buttonNumber)
// {
//     mouseInfo = new
//     switch (buttonNumber)
//     {
//         case 1:
//             if (mouseInfo.getButton() == 1)
//                 {
//                     return (true);
//                     break;
//                 }
//         case 2:
//          if (mouseInfo.getButton() == 2)
//                 {
//                     return (true);
//                     break;
//                 }
//         case 3:
//             if (mouseInfo.getButton() == 3)
//                 {
//                     return (true);
//                     break;
//                 }
//         default:
//             return(false);
//     }
// }
}
