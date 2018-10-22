import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A demo player controlled by WASD. A/D rotate, W moves forward. Rotational movement.
 */
public class Player2 extends PhysicsObject
{
    
    public Player2() {
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
        setMaxAngularSpeed(3);
    }
    
    private void moveFromKeyboardInput() {
        // This is the acceleration value.
        // It represents how much the velocity should change
        // when the player presses a key.
        double alpha = 0.5;
        
        if (Greenfoot.isKeyDown("a")) {
            increaseAngularVelocity(-alpha);
            
        }
        if (Greenfoot.isKeyDown("d")) {
            increaseAngularVelocity(alpha);
        }
        
        double v = 1;
        if (Greenfoot.isKeyDown("w")) {
            increaseVelocityPolar(v, getPreciseRotation());
        }
        
    }
    
    public void act() {
        moveFromKeyboardInput();
        super.act();
    }
}
