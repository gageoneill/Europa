import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level04 here.
 * 
 * @author (Jaskaran Buttar) 
 * @version (a version number or a date)
 */
public class Level04 extends Level
{

    /**
     * Constructor for objects of class Level04.
     * 
     */
    private Player player;
    int playerSize = 0;
    private int replacementCount = 0;
    EnemySpawner es;
    Player p = new Player();
    public void act()
    {
        super.act();
        sizeGoal = 150;
        playerSize = p.getSize();
        if (playerSize >= sizeGoal)
        {
            levelComplete = true;
        }
        if(goToNext)
        {
            // add upgrade points if next Level not avaliable
            if(!getMap().getPlayerData().canAccess(Level05.class)){
                getMap().getPlayerData().incUpgradePoints();
            }
            
            getMap().getPlayerData().markAccessible(Level05.class);
            returnToMap();
        }
        getCompletionPercentage();
        replacementCount();
    }
    public void replacementCount()
    {
        int c = getObjects(Enemy1.class).size();
        int n = 6;
        es.setReplacementCount(n-c);
    }
    public void prepare()
    {
        super.prepare();
        p.setSize(50); 
        //Wall w1 = new Wall(1200, 0);
        //addObject(w1, 0, 0);
        //Wall w2 = new Wall(900,90);
        //addObject(w2, 7, 450);
        
        Enemy1 e1 = new Enemy1();
        addObject(e1, 785, 400);
        Enemy1 e2 = new Enemy1();
        addObject(e2, 278, 200);
        Enemy1 e3 = new Enemy1();
        addObject(e3, 546, 100);
        Enemy1 e4 = new Enemy1();
        addObject(e4, 124, 300);
        Enemy1 e5 = new Enemy1();
        addObject(e5, 754, 300);
        Enemy1 e6 = new Enemy1();
        addObject(e6, 245, 400);
        
        addObject(p, 600, 800);
        //ObjectiveMessage objective = new ObjectiveMessage("Grow 4 Sizes");
        //addObject(objective, getWidth() / 2, getHeight() *7/8);
        KeyD d = new KeyD();
        addObject(d, 600, 600);
        es = new EnemySpawner(1);
        addObject(es, 600, 200);
    }
     
    public Level04(Map map)
    {
        super(map);
        prepare();
    }
    public int currentSize()
    {
        return playerSize;
    }
        public double getCompletionPercentage()
    {
         
        return (double)playerSize/sizeGoal;
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
