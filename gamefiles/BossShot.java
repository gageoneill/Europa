import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * A semantically separate enemy shot fired by bosses.
 * @author William Chargin
 * @version 7 December 2014
 * 
 */
public class BossShot extends EnemyShot implements Reflectable
{
    public BossShot()
    {
        super();
    }
    public BossShot(int lifetime)
    {
        super(lifetime);
    }
}
