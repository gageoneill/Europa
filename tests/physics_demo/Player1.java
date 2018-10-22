import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A demo player controlled by the arrow keys. Linear movement.
 */
public class Player1 extends PhysicsObject
{
    
    public Player1() {
        /*
         * This is the constructor.
         * It is invoked when the player is created.
         * 
         * Let's set up some physics stuff.
         */
        
        // Damping is how fast the player slows down (like friction).
        // A value of 1.0 is no damping; 0.0 is infinite damping.
        setLinearDamping(0.95);
        setAngularDamping(0.95);
        
        // Don't let the player go too fast!
        setMaxSpeed(5);
    }
    
    private void moveFromKeyboardInput() {
        // This is the acceleration value.
        // It represents how much the velocity should change
        // when the player presses a key.
        double a = 1;
        
        if (Greenfoot.isKeyDown("left")) {
            increaseVelocity(-a, 0);
        }
        if (Greenfoot.isKeyDown("right")) {
            increaseVelocity(a, 0);
        }
        
        if (Greenfoot.isKeyDown("up")) {
            increaseVelocity(0, -a);
        }
        if (Greenfoot.isKeyDown("down")) {
            increaseVelocity(0, a);
        }
    }
    
    public void act() {
        moveFromKeyboardInput();
        setRotation(Math.toDegrees(Math.atan2(getVelocityY(), getVelocityX())) + 45);
        super.act();
    }
}
