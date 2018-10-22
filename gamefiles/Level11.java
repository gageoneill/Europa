import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * 
 * @author (Jaskaran Buttar) 
 * @version (a version number or a date)
 */
public class Level11 extends Level
{
    int playerSize;

    private int replacementCount = 0;
    Player player = new Player();
    EnemySpawnerMoving e1 = new EnemySpawnerMoving(4);
    EnemySpawnerMoving e2 = new EnemySpawnerMoving(4);
    public void act()
    {
        super.act();
        currentSize();
        getCompletionPercentage();
        checkLevelCompletion();
        replacementCount();
    }
    public Level11(Map map)
    {
        super(map);
        prepare();
        sizeGoal = 150;
    }
    public void prepare()
    {
        super.prepare();
        player.setSize(50);
        addObject(player,600,450);
        Wall w1 = new Wall(600,0);
        addObject(w1, 610, 63);
        Wall w2 = new Wall(400,45);
        addObject(w2, 1028, 204);
        Wall w3 = new Wall(250,90);
        addObject(w3, 1168, 471);
        Wall w4 = new Wall(400,135);
        addObject(w4, 1030,738);
        Wall w5 = new Wall(600,0);
        addObject(w5, 610, 883);
        Wall w6 = new Wall(400,45);
        addObject(w6,189,742);
        Wall w7 = new Wall(250,90);
        addObject(w7,47,472);
        Wall w8 = new Wall(400,135);
        addObject(w8,189,206);
        addObject(e1,608,212);
        addObject(e2,594,731);
    }
    public int currentSize()
    {
        playerSize = player.getSize();
        return playerSize;
    }
    public double getCompletionPercentage()
    {
        return(double)playerSize/sizeGoal;
    }
    private void checkLevelCompletion()
    {
        if (playerSize >= sizeGoal)
        {
            levelComplete = true;
        }
        if(goToNext){
            // add upgrade points if next Level not avaliable
            if(!getMap().getPlayerData().canAccess(Level12.class)){
                getMap().getPlayerData().incUpgradePoints();
            }
            
            getMap().getPlayerData().markAccessible(Level12.class);
            returnToMap();
        }              
    }
    public void replacementCount()
    {
        int c = getObjects(Enemy3.class).size();
        int n = 6;
        e1.setReplacementCount(n-c);
        e2.setReplacementCount(n-c);
        System.out.println(n-c);

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
