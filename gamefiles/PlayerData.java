import java.util.*; // Set, HashSet

/**
 * A record type that stores the player's data so far.
 * This may include completed levels, long-term bonuses, etc.
 * 
 * @author William Chargin & Mateus Seehagen Rodriges
 * 
 * @version 14 October 2014: Initial version
 * @version 06 November 2014: Added player status and upgrades
 */
public class PlayerData  
{
    
    /**
     * The levels that the player can access.
     */
    private final Set<Class<? extends Level>> accessibleLevels = new HashSet<Class<? extends Level>>();
    /**
     * variables to represent player status
     */

    private int speed = 10;
    private int pointsSpeed = 0;
    private int shotRange = 15;
    private int pointsShotRange = 0;
    private int recoil = -5;
    private int pointsRecoil = 0;
    private int upgradePoints = Map.HAUNGS_MODE ? 15 : 0;

    // Tracking statistics
    int shotsFired;
    int enemiesKilled;
    int deathCount;
    private final long startTime = System.currentTimeMillis();
    public String getTimeSinceStart()
    {
        long millisSinceStart = System.currentTimeMillis() - startTime;
        long seconds = millisSinceStart / 1000;
        long minutes = seconds / 60;
        long modSeconds = seconds % 60;
        String secondsString = String.format("%s %s", modSeconds, modSeconds == 1 ? "second" : "seconds");
        if (minutes == 0)
        {
            return secondsString;
        }
        else
        {
            return String.format("%s %s, %s", minutes, minutes == 1 ? "minute" : "minutes", secondsString);
        }
    }
    
    /*
     * getters and upgraders for the status
     */

    public int getSpeed() { return this.speed; }
    public int getShotRange() { return this.shotRange; }
    public int getRecoil() { return this.recoil; }
    public int getUpgradePoints() { return this.upgradePoints; }
    
    public int getPointsSpeed() { return this.pointsSpeed; }
    public int getPointsShotRange() { return this.pointsShotRange; }
    public int getPointsRecoil() { return this.pointsRecoil; }
    
    public void incUpgradePoints(){ this.upgradePoints++; }
    
    /* upgrade methods */
    
    public void upgradeSpeed(){
        this.speed += 2; 
        this.pointsSpeed++;
        this.upgradePoints--;
    }
    public void upgradeShotRange(){ 
        this.shotRange += 3;
        this.pointsShotRange++;
        this.upgradePoints--;
    }
    public void upgradeRecoil(){ 
        this.recoil += 1; 
        this.pointsRecoil++;
        this.upgradePoints--;
    }

    /**
     * Marks the given level as accessible.
     * You should call this when the player completes the preceding level.
     */
    public void markAccessible(Class<? extends Level> clazz)
    {
        accessibleLevels.add(clazz);
    }
    
    /**
     * Marks the given level as inaccessible.
     * This might apply if the player gets knocked back, or something,
     * or you want to reset the game state.
     */
    public void markInaccessible(Class<? extends Level> clazz)
    {
        accessibleLevels.remove(clazz);
    }
    
    /**
     * Determines whether the player can access a given level.
     * 
     * @param clazz
     *      the clazz of the relevant level; for example, {@code Level01.class}
     * @return
     *      {@code true} if the player can access the level, or {@code false} otherwise
     */
    public boolean canAccess(Class<? extends Level> clazz)
    {
        return accessibleLevels.contains(clazz);
    }
    
    /* methods for manipulating the acessible levels */
    
    public Set<Class<? extends Level>> getLevels(){
        return new HashSet<Class<? extends Level>>(accessibleLevels);
    }
    public void setLevels(Set<Class<? extends Level>> levels){
        accessibleLevels.clear();
        accessibleLevels.addAll(levels);
    }
    
    public void markAllInaccessible(){ this.accessibleLevels.clear(); }
}
