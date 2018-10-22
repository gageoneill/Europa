import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level08 here.
 * 
 * @author (Jaskaran Buttar) 
 * @version (a version number or a date)
 */
public class Level08 extends Level
{
    int playerSize;
    
    private int replacementCount = 0;
    boolean willEnemyTrackPlayer = true;
    Player player = new Player();
    
    EnemySpawner e1 = new EnemySpawner(3);
    EnemySpawner e2 = new EnemySpawner(3);
    public void act()
    {
        super.act();
        getCompletionPercentage();
        checkLevelCompletion();
        replacementCount();
        currentSize();
    }
    public void replacementCount()
    {
        int c = getObjects(Enemy3.class).size();
        int n = 6;
        e1.setReplacementCount(n-c);
        e2.setReplacementCount(n-c);
        
    }
    /**
     * Constructor for objects of class Level08.
     * 
     */
    public Level08(Map map)
    {
        super(map);
        prepare();
        sizeGoal = 200;
    }
    public void prepare()
    {
        addObject(player, 600,450);
        super.prepare();
        Wall w1 = new Wall(150,90);
        addObject(w1, 600, 75);
        Wall w2 = new Wall(150,90);
        addObject(w2, 600, 825);
        
        
        //Wall w3 = new Wall(300,90);
        //addObject(w3, 351, 458);
        //Wall w4 = new Wall(300,90);
        //addObject(w4, 855,458);
        Wall w5 = new Wall(100,0);
        //addObject(w5,95,388);
        Wall w6 = new Wall(100,0);
        //addObject(w6,1124,387);
        Wall w7 = new Wall(100,90);
        //addObject(w7,52,445);
        Wall w8 = new Wall(100,90);
        //addObject(w8,1166,445);
        Wall w9 = new Wall(100,0);
        //addObject(w9,94,500);
        Wall w10 = new Wall(100,0);
        //addObject(w10, 1123, 502);
        Wall w11 = new Wall(60,90);
        addObject(w11,965,444);
        Wall w12 = new Wall(200,45);
        addObject(w12, 899, 354);
        Wall w13 = new Wall(200,135);
        addObject(w13, 301, 354);
        Wall w14 = new Wall(60,90);
        addObject(w14,235,445);
        Wall w15 = new Wall(200,45);
        addObject(w15, 302, 535);
        Wall w16 = new Wall(200,135);
        addObject(w16, 898, 533);
        
        
        
        
        
        
        addObject(e1,100,446);
        
        addObject(e2,1103,444);
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
            if(!getMap().getPlayerData().canAccess(Level09.class)){
                getMap().getPlayerData().incUpgradePoints();
            }
            
            
            getMap().getPlayerData().markAccessible(Level09.class);
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