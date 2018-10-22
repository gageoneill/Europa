import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Clay Asato
 * @version 4 November 2014
 * 
 * 
 */
public class Level07 extends Level
{
    int playerSize;
    private int replacementCount = 0;
    boolean willEnemyTrackPlayer = true;
    Player player = new Player();
    EnemySpawnerMoving esm = new EnemySpawnerMoving(1);
    public void act()
    {
        super.act();
        getCompletionPercentage();
        //replacementCount();
        checkLevelCompletion();
        if (getObjects(NPC.class).size() < 5)
       {
            esm.setReplacementCount(1);
        }
        currentSize();
    }
    public Level07(Map map)
    {
        super(map);
        prepare();
        sizeGoal = 150;
    }
    public void prepare()
    {
        addObject(player, 600,900);
        super.prepare();
        //add players
        
        //add walls
         
        //add enemies
        Enemy1 e1 = new Enemy1();
        Enemy1 e2 = new Enemy1();
        addObject(e1, 400, 250);
        addObject(e2, 800, 250);
        

        addObject(esm , getWidth()/2, getHeight()/2);
       
        esm.setTimerCap(100);
    }
    public int currentSize()
    {
        playerSize = player.getSize();
        return playerSize;
    }
    public double getCompletionPercentage()
    {
        double size = playerSize;
        double goal = sizeGoal;
        double percent = (double)size/goal;
        //System.out.println(percent);
        return percent;
        
    }
    private void checkLevelCompletion()
    {
        // When finish Level07...
        if (playerSize >= sizeGoal)
        {
            levelComplete = true;
        }
        
        if(goToNext){ 
            // add upgrade points if next Level not avaliable
            if(!getMap().getPlayerData().canAccess(Level08.class)){
                getMap().getPlayerData().incUpgradePoints();
            }
            
            
            // okay to go to Level08.
            //when level 08 exists, mark accessible.
            getMap().getPlayerData().markAccessible(Level08.class);
            returnToMap();
        }
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
