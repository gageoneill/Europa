import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level12 extends Level
{
    int playerSize;

    private int replacementCount = 0;
    Player player = new Player();
    EnemySpawner e1 = new EnemySpawner(3);
    EnemySpawner e2 = new EnemySpawner(3);
    MiniBoss3 mb3;
    MiniBossHealthBar mbhb;
    boolean bossMode = false;
    
    public void act()
    {
        if (Greenfoot.getRandomNumber(150) == 1)
        {
        addObject(new Barrel(),Greenfoot.getRandomNumber(3) * -365 +170,50);
        
    }
        super.act();
        getCompletionPercentage();
        replacementCount();
        currentSize();
        checkLevelCompletion();
    }
    public Level12(Map map)
    {
        super(map);
        prepare();
        sizeGoal = 200;
    }
    public void replacementCount()
    {
         int c = getObjects(Enemy3.class).size();
        int n = 6;
        e1.setReplacementCount(n-c);
        e2.setReplacementCount(n-c);
        
    }
    public void prepare()
    {
        bossMode = false;
        addObject(player, 600,450);
        super.prepare();
        
        addObject(e1, e1.getImage().getWidth() / 2, getHeight() - e1.getImage().getHeight() / 2);
        addObject(e2, getWidth() - e2.getImage().getWidth() / 2, getHeight() - e2.getImage().getHeight() / 2);
        
        /*
         Wall w1 = new Wall(200,55);
        addObject(w1, 59, 81);
        Wall w2 = new Wall(200,125);
        addObject(w2, 292, 83);
        Wall w3 = new Wall(200,55);
        addObject(w3, 410,79);
        Wall w4 = new Wall(200,125);
        addObject(w4, 660,79);
        Wall w5 = new Wall(200,55);
        addObject(w5, 775, 80);
        Wall w6 = new Wall(200,125);
        addObject(w6,1029,80);
        Wall w7 = new Wall(450,90);
        addObject(w7,1090,197);
        Wall w8 = new Wall(450,90);
        addObject(w8,1093,843);
        */
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
        if (playerSize >= sizeGoal && bossMode ==false)
        {
            mb3 = new MiniBoss3();
            addObject(mb3, 1100, 525);
            mbhb = new MiniBossHealthBar();
            addObject(mbhb,1,1);
            bossMode = true;
        }
        if(goToNext){
            // add upgrade points if next Level not avaliable
            if(!getMap().getPlayerData().canAccess(Level13.class)){
                getMap().getPlayerData().incUpgradePoints();
                getMap().getPlayerData().incUpgradePoints();
            }
            
            getMap().getPlayerData().markAccessible(Level13.class);
            returnToMap();
        }              
    }
    public double getBossHPPercent()
    {
        double percent = (double)mb3.checkHPLevel() / mb3.checkMaxHP();
        if (percent == 0)
        {
            levelComplete = true;
        }
        return percent;
        
    }
    public int getMiniBossX()
    {
        return mb3.getX();
    }
    public int getMiniBossY()
    {
        return mb3.getY();
    }
}
