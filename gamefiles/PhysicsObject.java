import greenfoot.Actor;
import greenfoot.World;
import java.util.List;
/**
 * A {@code PhysicsObject} is an {@link Actor} that is equipped with some basic
 * physical interactions. It has velocity and angular velocity, which control
 * its movement and rotation.
 * <p>
 * Because {@code Actor} uses integer values for position and rotation, this
 * class duplicates these fields as {@code double} values (decimals). The exact
 * values can be found with the {@code getPrecise...} methods, such as
 * {@link #getPreciseX()}.
 * 
 * @author William Chargin
 * 
 * @version 9 October 2014: Add increaseForwardVelocity ("push") method.
 * @version 7 October 2014: Add support for max speed.
 * @version 3 October 2014: Initial version
 */
public class PhysicsObject extends Actor
{
    ///////////////////////////////////////////////////////////////////////////
    // Fields
    ///////////////////////////////////////////////////////////////////////////
    
    
    private int stun = 0;
    private int maxStun = 30;
    public void stun()
    {
        stun = maxStun;
    }
    
    /**
     * The x-component of this object's velocity (in px/frame).
     */
    private double vx = 0.0;
    
    /**
     * The y-component of this object's velocity (in px/frame).
     */
    private double vy = 0.0;
    
    /**
     * The maximum allowable speed for this object.
     * If this value is negative, there is no max speed.
     */
    private double maxSpeed = -1.0;
    
    /**
     * The linear damping of this object, where {@code 1.0} is no damping and
     * {@code 0.0} is infinite damping. Velocity values will be multiplied by
     * the damping value after each frame.
     */
    private double linearDamping = 1.0;
    
    /**
     * This object's angular velocity (in degrees/frame). Positive values
     * indicate clockwise rotation.
     */
    private double omega = 0.0;
    
    /**
     * The maximum allowable angular speed for this object.
     * If this value is negative, there is no max angular speed.
     */
    private double maxAngularSpeed = -1.0;
    
    /**
     * The angular damping of this object, where {@code 1.0} is no damping and
     * {@code 0.0} is infinite damping. The angular velocity will be multiplied
     * by the damping value after each frame.
     */
    private double angularDamping = 1.0;
    
    /**
     * This object's current x-position. Stored because Actor only uses int.
     */
    private double x;
    
    /**
     * This object's current y-position. Stored because Actor only uses int.
     */
    private double y;
    
    /**
     * This object's current rotation, in degrees, where zero is due east
     * and positive values indicate clockwise rotation. Stored
     * because Actor only uses int.
     */
    private double theta;
    
    private double lastX;
    private double lastY;
    private double lastTheta;
    
    private boolean noBounds = false;
    
    ///////////////////////////////////////////////////////////////////////////
    // Overrides for methods in Actor that only support int
    ///////////////////////////////////////////////////////////////////////////

    
    @Override
    public void setLocation(int x, int y) {
        /*
         * We're overriding (changing the behavior of) the setLocation 
         * method from the Actor class. However, we don't want to just
         * replace the behavior, we want to add on to it. Therefore, we
         * first call Actor's setLocation method to actually move this
         * object.
         */
        super.setLocation(x, y);
        
        /*
         * Now, we want to change the x and y coordinates of this object
         * to match the new ones.
         */
        this.x = x;
        this.y = y;
    }
    
    /**
     * Assigns a new location for this actor. The new coordinates will be rounded
     * to the nearest integer and passed to {@link Actor#setLocation(int,int)}.
     * 
     * @param x
     *      the new x-location
     * @param y
     *      the new y-location
     */
    public void setLocation(double x, double y) {
        /*
         * Pass the rounded version to Actor's setLocation method.
         */
        super.setLocation((int) Math.round(x), (int) Math.round(y));
        
        /*
         * Store the actual (non-rounded) coordinates locally.
         */
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void setRotation(int theta) {
        /*
         * We're overriding (changing the behavior of) the setRotation
         * method from the Actor class. However, we don't want to just
         * replace the behavior; we want to add on to it. Therefore, we
         * first call Actor's setRotation method to actually rotate
         * this object.
         */
        super.setRotation(theta);
        
        /*
         * Now, we update the internal value to match this one.
         */
        this.theta = theta;
    }
    
    /**
     * Assigns a new rotation for this actor. The new rotation will be rounded
     * to the nearest integer and passed to {@link Actor#setRotation(int)}.
     * 
     * @param theta
     *      the new rotation, in degrees, where zero is due east and positive
     *      values indicate clockwise rotation
     */
    public void setRotation(double theta) {
        /*
         * Pass the rounded version to Actor's setRotation method.
         */
        super.setRotation((int) Math.round(theta));
        
        /*
         * Store the actual (non-rounded) value locally.
         */
        this.theta = theta;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Accessors (getters and setters)
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Gets this object's x-position as a decimal value.
     * 
     * @return
     *      this object's x-position
     */
    public double getPreciseX() {
        return x;
    }
    
    /**
     * Gets this object's y-position as a decimal value.
     */
    public double getPreciseY() {
        return y;
    }
    
    
    /**
     * Sets the velocity of this object, overriding the current velocity.
     * 
     * @param vx
     *      the new x-velocity
     * @param vy
     *      the new y-velocity
     */
    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
    
    /**
     * Increases the velocity by the given value on each axis.
     * This is equivalent to calling:
     * <pre><code>setVelocity(getVelocityX() + dvx, getVelocityY() + dvy);</code></pre>
     * 
     * @param dvx
     *      the change in x-velocity (stands for &Delta;<i>v<sub>x</sub></i>)
     * @param dvy
     *      the change in y-velocity (stands for &Delta;<i>v<sub>y</sub></i>)
     */
    public void increaseVelocity(double dvx, double dvy) {
        this.vx += dvx;
        this.vy += dvy;
    }
    
    /**
     * Increases the velocity by the given magnitude in the given direction.
     * You can think of this as "pushing" this object in a certain direction.
     * 
     * @param r
     *      the magnitude (radius) of the increase of velocity
     * @param theta
     *      the direction (angle) of the increase of velocity, in degrees,
     *      where zero is due east and positive values are clockwise
     */
    public void increaseVelocityPolar(double r, double theta) {
        theta = Math.toRadians(theta);
        this.vx += r * Math.cos(theta);
        this.vy += r * Math.sin(theta);
    }
    
    /**
     * Increases the velocity by the given magnitude in the current direction.
     * You can think of this as "pushing" the object in the direction it's facing.
     * 
     * @param r
     *      the magnitude of the increase of velocity
     */
    public void increaseVelocityForward(double r) {
        increaseVelocityPolar(r, theta);
    }
    
    /**
     * Gets the x-component of this object's velocity.
     * 
     * @return
     *      this object's x-velocity
     */
    public double getVelocityX() {
        return vx;
    }
    
    /**
     * Gets the y-component of this object's velocity.
     * 
     * @return
     *      this object's y-velocity
     */
    public double getVelocityY() {
        return vy;
    }
    
    /**
     * Gets the speed of this object.
     * 
     * @return
     *      the current speed (magnitude of velocity)
     */
    public double getSpeed() {
        return Math.hypot(vx, vy);
    }
    
    /**
     * Sets the maximum speed of this object.
     * 
     * @param maxSpeed
     *      the new maximum speed, or a negative value to indicate that
     *      there should not be any maximum speed
     * @see #maxSpeed
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    
    /**
     * Gets the maximum speed of this object.
     * 
     * @return
     *      the maximum speed
     * @see #maxSpeed
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }
    
    /**
     * Gets this object's rotation as a decimal value.
     */
    public double getPreciseRotation() {
        return theta;
    }
    
    /**
     * Sets this object's angular velocity.
     * 
     * @param omega
     *      the new angular velocity, in degrees/second, where positive values
     *      indicate clockwise rotation
     * @see #omega
     */
    public void setAngularVelocity(double omega) {
        this.omega = omega;
    }
    
    /**
     * Gets this object's angular velocity.
     * 
     * @return
     *      the angular velocity, in degrees/second, where positive values
     *      indicate clockwise rotation
     * @see #omega
     */
    public double getAngularVelocity() {
        return omega;
    }
    
    /**
     * Increases this object's angular velocity by the given amount.
     * 
     * @param domega
     *      the increase of angular velocity, in degrees/frame, where
     *      positive values indicate clockwise rotation
     *      (stands for &Delta;&omega;)
     */
    public void increaseAngularVelocity(double domega) {
        this.omega += domega;
    }
    
    /**
     * Sets the maximum angular speed of this object.
     * 
     * @param maxAngularSpeed
     *      the new maximum angular speed, or a negative value to indicate that
     *      there should not be any maximum angular speed
     * @see #maxAngularSpeed
     */
    public void setMaxAngularSpeed(double maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }
    
    /**
     * Gets the maximum angular speed of this object.
     * 
     * @return
     *      the maximum angular speed
     * @see #maxAngularSpeed
     */
    public double getMaxAngularSpeed() {
        return maxAngularSpeed;
    }
    
    /**
     * Sets the linear damping value. The velocity will be multiplied by this
     * value after each frame.
     * 
     * @see #linearDamping
     */
    public void setLinearDamping(double linearDamping) {
        this.linearDamping = linearDamping;
    }
    
    /**
     * Gets the current linear damping value.
     * 
     * @see #linearDamping
     */
    public double getLinearDamping() {
        return linearDamping;
    }
    
    /**
     * Sets the angular damping value. The angular velocity will be multiplied
     * by this value after each frame.
     * 
     * @see #angularDamping
     */
    public void setAngularDamping(double angularDamping) {
        this.angularDamping = angularDamping;
    }
    
    /**
     * Gets the current angular damping value.
     * 
     * @see #angularDamping
     */
    public double getAngularDamping() {
        return angularDamping;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Physics methods
    ///////////////////////////////////////////////////////////////////////////

    
    /**
     * Moves this object according to its velocity value.
     */
    private void moveByVelocity() {
        setLocation(x + vx, y + vy);
        setRotation(theta + omega);
    }
    
    /**
     * Applies damping, as described in the documentation for the damping variables.
     * 
     * @see #linearDamping
     * @see #angularDamping
     */
    private void applyDamping() {
        vx = vx * linearDamping;
        vy = vy * linearDamping;
        omega = omega * angularDamping;
    }
    
    /**
     * Slows this object down if it is going too fast.
     * 
     * @see #maxSpeed
     */
    private void clampMaxSpeed() {
        // First, check to see if we even have a max speed.
        if (maxSpeed < 0) {
            // Nope, no max speed.
            // We're done here.
            return;
        }
        
        // Calculate the current speed.
        double speed = Math.hypot(vx, vy);
        
        // Is it too high?
        if (speed > maxSpeed) {
            // Yep.
            // Now, we want to move in the same direction, but slower.
            double ratio = maxSpeed / speed;
            
            // Slow down.
            vx = vx * ratio;
            vy = vy * ratio;
        }
    }
    
    /**
     * Slows this object down if it is rotating too fast.
     * 
     * @see #maxAngularSpeed
     */
    public void clampMaxAngularSpeed() {
        // First, check to see if we even have a max angular speed.
        if (maxAngularSpeed < 0) {
            // Nope, no max angular speed.
            // We're done here.
            return;
        }
        
        // Calculate the current angular speed.
        double angularSpeed = Math.abs(omega);
        
        // Is it too high?
        if (angularSpeed > maxAngularSpeed) {
            // Yep.
            // Now, if omega is positive, we want to set it to maxAngularSpeed.
            // If omega is negative, we want to set it to -maxAngularSpeed.
            // We can use the copySign method for this.
            // This line basically means:
            //   "Set omega to have magnitude 'maxAngularSpeed' and the sign of 'omega.'"
            omega = Math.copySign(maxAngularSpeed, omega);
        }
    }
    
    public void trackPlayer()//Written by Clay Asato //edited by Jaskaran Buttar
    {
        if (getWorld() == null || stun > 0)
        {
            return;
        }
        Level lv = (Level)getWorld();       
        Player p = lv.getPlayer();
        
        if(lv.getPlayer() != null)
        {
            int playerXLocation = p.getX();
            int playerYLocation = p.getY();
            turnTowards(playerXLocation, playerYLocation);
            //  int xOffset = getX()-playerXLocation;
            //  int yOffset = getY()-playerYLocation;
            //  int heading = getRotation();
            increaseVelocityPolar(.2, this.getRotation());
            
            List l = getWorld().getObjects(NPC.class);
            for ( Object o : l)
            {
                NPC npc = (NPC)o;
                if (this == npc)
                {
                    continue;
                }
                double dx = this.getX() - npc.getX();
                double dy = this.getY() - npc.getY();
                double dz = Math.hypot(dx, dy);
                double theta = Math.toDegrees(Math.atan2(dy, dx));
                if ( dz < 80)
                {
                    increaseVelocityPolar((80.0-dz)/80.0,theta);
                }
            }
        }
        
        
    }
    
    public double getLastX()
    {
        return lastX;
    }
    public double getLastY()
    {
        return lastY;
    }
    public double getLastTheta()
    {
        return lastTheta;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Key Greenfoot methods
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public void act() {
        storeLastCoordinates();
        moveByVelocity();
        applyDamping();
        clampMaxSpeed();
        boundary();
        if (--stun < 0)
        {
            stun = 0;
        }
    }
    
    private void storeLastCoordinates()
    {
        lastX = x;
        lastY = y;
        lastTheta = theta;
    }
    
    public void boundary()//Jaskaran Buttar
    {
        if (noBounds)
        {
            return;
        }
        int halfwidth = getImage().getWidth()/2;
        double x = getVelocityX();
        double y = getVelocityY();
        if (getX() < halfwidth) 
        {
            setLocation(halfwidth + 1, getY());
            setVelocity(-x, y);          
            setRotation(180 - getRotation());
        }
        if (getX() > getWorld().getWidth() - halfwidth)
        {
            setLocation(getWorld().getWidth() - halfwidth - 1, getY());
            setVelocity(-x, y);
            setRotation(180 - getRotation());
        }
        int halfheight = getImage().getHeight()/2;
        if (getY() < halfheight)
        {
            setLocation(getX(), halfheight + 1);
            setVelocity(x, -y);
            setRotation(180 - getRotation());
        }
        if (getY() > getWorld().getHeight() -halfheight)
        {
            setLocation(getX(), getWorld().getHeight()-halfheight-1);
            setVelocity(x, -y);
            setRotation(180 - getRotation());
        }

    }
    
    public void setNoBounds(boolean noBounds)
    {
        this.noBounds = noBounds;
    }
     
    @Override
    public void addedToWorld(World w) {
        /*
         * When this object is added to the world, it is given a position.
         * We need to update our "x" and "y" variables to match this.
         */
        this.x = getX();
        this.y = getY();
    }
}
