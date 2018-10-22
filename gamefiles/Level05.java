import greenfoot.*;
/**
 * 
 * @author Gage O'Neill 
 * @version 4 November 2014
 * 
 *
 */
public class Level05 extends Level
{
    int playerSize;
    private int replacementCount = 0;
    EnemySpawner es1;
    EnemySpawner es2;
    Player p = new Player();
    public void act()
    {
        super.act();
        sizeGoal = 150;
        playerSize = p.getSize();
        if(playerSize >= sizeGoal)
        {
            levelComplete = true;
        }
        if(goToNext)
        { 
            // add upgrade points if next Level not avaliable
            if(!getMap().getPlayerData().canAccess(Level06.class)){
                getMap().getPlayerData().incUpgradePoints();
            }
            
            
            getMap().getPlayerData().markAccessible(Level06.class);
            returnToMap();
        }
        getCompletionPercentage();
        replacementCount();
    }
    public Level05(Map map)
    {
        super(map);
        prepare();
    }
    public void prepare()
    {
        super.prepare();
        // add player
        addObject(p, 600, 450);
        p.setSize(50);
        // add walls
        // left section
        Wall w1 = new Wall(175, 55);
        addObject(w1, 240, 275);
        Wall w2 = new Wall(217, 90);
        addObject(w2, 288, 450);
        Wall w3 = new Wall(175, -55);
        addObject(w3, 240, 625);
        // right section
        Wall w4 = new Wall(175, -55);
        addObject(w4, 960, 275);
        Wall w5 = new Wall(217, 90);
        addObject(w5, 912, 450);
        Wall w6 = new Wall(175, 55);
        addObject(w6, 960, 625);
        // add enemies
        es1 = new EnemySpawner(2);
        es1.setAngleStart(70);
        es1.setAngleRange(20);
        es1.setVelocity(15);
        addObject(es1, 75, 450);
        es2 = new EnemySpawner(2);
        es2.setAngleStart(-90);
        es2.setAngleRange(20);
        es2.setVelocity(15);
        addObject(es2, 1125, 450);
    }
    public int currentSize()
    {
        return playerSize;
    }
    public double getCompletionPercentage()
    {
        return (double)playerSize/sizeGoal;
    }
    public void replacementCount()
    {
        int x = getObjects(Enemy2.class).size();
        int y = 5;
        es1.setReplacementCount(y-x);
        es2.setReplacementCount(y-x);
    }
    public double getBossHPPercent()
    {
        return 0;
    }
    public int getMiniBossX()
    {
        return 0;
    }
    public int getMiniBossY()
    {
        return 0;
    }
}
