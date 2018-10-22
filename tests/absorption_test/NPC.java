import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NPC extends Microbe
{
    private int cooldown = 0;
    private int maxCooldown = 5;
    
    public void onCollisionWith(Player player) {
    }
    
    public void act() {
        super.act();
        if (cooldown == 0) {
            Actor player = getOneIntersectingObject(Player.class);
            if (player != null) {
                onCollisionWith((Player) player);
                cooldown = maxCooldown;
            }
        } else {
            cooldown--;
        }
    }
    
}
