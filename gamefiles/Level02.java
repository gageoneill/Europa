import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Level02 here.
 * 
 * @author Jaskaran Buttar 
 * @version
 */
public class Level02 extends Level
{
    //background image cred: http://www.dreamstime.com/royalty-free-stock-photo-winter-ice-background-image2089595
    private Player player;
    public void act()
    {
        super.act();
        
        if (getObjects(NPC.class).isEmpty())
        {
            levelComplete = true;
        }
        
        if(goToNext){  
            getMap().getPlayerData().markAccessible(Level03.class);
            returnToMap(); 
        }
        sizeGoal = 100;
    }
    public void prepare()
    {
        super.prepare();
        
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
        Enemy1 e7 = new Enemy1();
        addObject(e7, 651, 200);
        Enemy1 e8 = new Enemy1();
        addObject(e8, 943, 100);
        Player p = new Player();
        addObject(p, 600, 800);
        ObjectiveMessage objective = new ObjectiveMessage("Kill All The Enemies");
        addObject(objective, getWidth() / 2, getHeight() *7/8);
        KeyD d = new KeyD();
        addObject(d, 600, 600);
        
    }
    public Level02(Map map)
    {
        super(map);
        prepare();
    }
    //Modified by Clay
    public double getCompletionPercentage()
    {
        double percentage;
        double enemiesLeft = getObjects(NPC.class).size();
        double killCount = 8 - enemiesLeft;
        percentage = killCount/8;
        //System.out.println(percentage);
        return percentage;
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
