import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * 
 * @author Jaskaran Buttar
 * @version
 */
public class Level10 extends Level
{
    private Player p;

    int playerSize;

    private int replacementCount = 0;
    Player player = new Player();
    EnemySpawner e1 = new EnemySpawner(4);
    EnemySpawner e2 = new EnemySpawner(3);
    EnemySpawner e3 = new EnemySpawner(4);
    EnemySpawner e4 = new EnemySpawner(3);
    public void replacementCount()
    {
        int c = getObjects(Enemy3.class).size();
        int n = 6;
        e1.setReplacementCount(n-c);
        e2.setReplacementCount(n-c);
        e3.setReplacementCount(n-c);
        e4.setReplacementCount(n-c);
        System.out.println(n-c);
        sizeGoal = 150;

    }

    public void act()
    {
        super.act();
        playerSize = player.getSize();
        if(playerSize >= sizeGoal)
        {
            levelComplete = true;
        }
        if(goToNext)
        {
            // add upgrade points if next Level not avaliable
            if(!getMap().getPlayerData().canAccess(Level11.class)){
                getMap().getPlayerData().incUpgradePoints();
            }

            getMap().getPlayerData().markAccessible(Level11.class);
            returnToMap();
        }
        getCompletionPercentage();
        checkLevelCompletion();
        replacementCount();
    }

    public Level10(Map map)
    {
        super(map);
        prepare();
        sizeGoal = 200;
    }

    public void prepare()
    {
        super.prepare();
        player.setSize(50);
        addObject(player,450,600);
        Wall w1 = new Wall(250,0);
        addObject(w1, 614, 164);
        Wall w2 = new Wall(150,45);
        addObject(w2, 886, 277);
        Wall w3 = new Wall(150,90);
        addObject(w3, 945, 484);
        Wall w4 = new Wall(150,135);
        addObject(w4, 873,706);
        Wall w6 = new Wall(250,0);
        addObject(w6,607,804);
        Wall w7 = new Wall(150,45);
        addObject(w7,340,723);
        Wall w8 = new Wall(150,90);
        addObject(w8,277,499);
        Wall w9 = new Wall(150,135);
        addObject(w9,357,276);
        Wall w10 = new Wall(150,45);
        //addObject(w10, 136, 47);
        Wall w12 = new Wall(150,135);
        //addObject(w12,1063,47);
        Wall w13 = new Wall(150,135);
        //addObject(w13,1150,137);
        Wall w14 = new Wall(150,45);
        //addObject(w14,1149,764);
        Wall w15 = new Wall(150,45);
        //addObject(w15,1062,854);
        Wall w16 = new Wall(150,45);
        //addObject(w16, 39, 133); 
        Wall w17 = new Wall(150,135);
        //addObject(w17,43,775);
        Wall w18 = new Wall(150,135);
        //addObject(w18,133,857);
        addObject(e1,40,860);
        addObject(e2,1168,869);
        addObject(e3,1162,40);
        addObject(e4,41,40);
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
            getMap().getPlayerData().markAccessible(Level11.class);
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
