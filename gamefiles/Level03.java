import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level01 here.
 * 
 * @author William Chargin
 * 
 * @version 7 October 2014: Initial version
 */
public class Level03 extends Level
{
    //background image cred: http://www.dreamstime.com/royalty-free-stock-photo-winter-ice-background-image2089595
    Player player = new Player();
    boolean willEnemyTrackPlayer = true;
    /**
     * Constructor for objects of class Level01.
     * 
     */
    public Level03(Map map)
    {
        super(map);
        prepare();
        sizeGoal = 100;
    }
    
    @Override
    public void prepare() {
        super.prepare();
        
        // Calculate screen center.
        final int cx = getWidth() / 2, cy = getHeight() / 2;
        
        // Add a player at the center of the screen.
        addObject(player, cx, cy);
        player.setSize(50);
        // Add some enemies, in a circle around the center.
        // (Yep---trig.)
        int numEnemies = 10;
        int enemyRadius = 250;
        for (int i = 0; i < numEnemies; i++) {
            double theta = Math.PI * 2 * i / numEnemies / 2;
            int x = (int) (cx + enemyRadius * Math.cos(theta));
            int y = (int) (cy + enemyRadius * Math.sin(theta));
            addObject(new Enemy3(willEnemyTrackPlayer), x, y);
        }
        //addObject(new Wall(80, 0), 150, 150);
        KeyA a = new KeyA();
        addObject(a,600,100);
        ObjectiveMessage objective = new ObjectiveMessage("Press A to Shield");
        addObject(objective, getWidth()/2, getHeight()*7/8);
    }
    
    public void act(){
        super.act();
        
        if(getObjects(NPC.class).isEmpty()){   // TODO condition to end level
            levelComplete = true;
        }
        
        if(goToNext){ 
            getMap().getPlayerData().markAccessible(Level04.class);
            returnToMap();
        }
    }
    
    public double getCompletionPercentage()//edited by Clay Asato
    {
        double currentEnemyCount = getObjects(NPC.class).size();
        double killCount = 10 - currentEnemyCount;
        double percentage = killCount/10;
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
