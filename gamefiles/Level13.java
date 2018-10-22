import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * 
 * @author Gage O'Neill
 * @version (a version number or a date)
 */
public class Level13 extends Level
{
    int playerSize;
    int sizeGoal = 450;
    private int replacementCount = 0;
    Player player = new Player();
    FinalBoss fb;
    MiniBossHealthBar hb = new MiniBossHealthBar();
    public void act()
    {
        super.act();
        getCompletionPercentage();
        checkLevelCompletion();
        replacementCount();
        this.removeObjects(getObjects(Item.class));
    }
    public Level13(Map map)
    {
        super(map);
        prepare();
        sizeGoal = 450;
        this.removeObject(sbc);
        
        //this.removeObject(item);
    }
    public void replacementCount()
    {
        
    }
    public void prepare()
    {
        super.prepare();
        fb = new FinalBoss();
        addObject(player, 600, 700);
        addObject(fb, 600, 200);
        addObject(hb,1,1);
        setPaintOrder(MiniBossHealthBar.class);
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
        if (fb.levelComplete == true)
        {
            this.levelComplete = true;
        }
        if(goToNext){
            //getMap().getPlayerData().markAccessible(Level09.class);
            Greenfoot.setWorld(new StatisticsDisplay(getPlayerData(), getMap()));
        }              
    }
    public double getBossHPPercent()
    {
        return((double)fb.hp / fb.hpMax);
    }
    public int getMiniBossX()
    {
        if (fb.getWorld() == null) return 0;
        return fb.getX();
    }
    public int getMiniBossY()
    {
        if (fb.getWorld() == null) return 0;
        return fb.getY();
    }
}
