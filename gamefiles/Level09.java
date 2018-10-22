import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * 
 * 
 * @author Jaskaran Buttar 
 * @version 
 * 
 */
public class Level09 extends Level
{
    int playerSize;
    boolean bossIsDead;
    private int replacementCount = 0;
    boolean willEnemyTrackPlayer = true;
    Player player = new Player();   
    boolean bossMode = false;
    EnemySpawner e1 = new EnemySpawner(2);
    EnemySpawner e2 = new EnemySpawner(4);
    MiniBoss2 mb2; // = new MiniBoss2();
    MiniBossHealthBar mbhb = new MiniBossHealthBar();   
    public void act()
    {
        super.act();
        playerSize = player.getSize();
        if(playerSize >= sizeGoal && bossMode == false)
        {
            bossMode = true;
            checkForBoss();
        }
        if(goToNext)
        {
            // add upgrade points if next Level not avaliable
            if(!getMap().getPlayerData().canAccess(Level10.class)){
                getMap().getPlayerData().incUpgradePoints();
            }
            
            getMap().getPlayerData().markAccessible(Level10.class);
            returnToMap();
        }
        getCompletionPercentage();
        checkLevelCompletion();
        replacementCount();
        currentSize();

    }
    public void prepare()
    {
        addObject(player, 600,450);
        super.prepare();
        
        mb2 = new MiniBoss2();
        Wall w1 = new Wall(1200,0);
        addObject(w1,600,150);
        Wall w2 = new Wall(1200,0);
        addObject(w2,600,750);
        addObject(e1,100,450);
        addObject(e2,1100,450);
        bossMode = false;
    }
    public void replacementCount()
    {
        int c = getObjects(Enemy3.class).size();
        int n = 4;
        e1.setReplacementCount(n-c);
        e2.setReplacementCount(n-c);
    }
    public Level09(Map map)
    {
        super(map);
        prepare();
        sizeGoal = 150;
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
        if (mb2.levelComplete)
        {
            levelComplete = true;
        }
        if(goToNext){ 
            // add upgrade points if next Level not avaliable
            if(!getMap().getPlayerData().canAccess(Level10.class)){
                getMap().getPlayerData().incUpgradePoints();
                getMap().getPlayerData().incUpgradePoints();
            }            
            getMap().getPlayerData().markAccessible(Level10.class);
            returnToMap();
        }
               
    }
    public double getBossHPPercent()
    {
        int hp = mb2.checkHPLevel();
        int hpMax = mb2.checkMaxHP();
        return (double)hp/hpMax;
    }
    public int getMiniBossX()
    {
        if (mb2.getWorld() != null)
        {
            return mb2.getX();
        }
        else
        {
            return 0;
        }
    }
    public int getMiniBossY()
    {
        if(mb2.getWorld() != null)
        {
            return mb2.getY();
        }
        else
        {
            return 0;
        }
    }

    
    public void checkForBoss()
    {
        if(bossMode)
        {
            addObject(mb2, 106, 498);
            addObject(mbhb, getWidth()/2, (getHeight()*3)/2);
            removeObject(e1); 
            removeObject(e2);
        }
    }
    

}
