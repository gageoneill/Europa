import greenfoot.*;
/**
 * 
 * @author Gage O'Neill and Clay Asato
 * @version 4 November 2014
 * 
 */
public class Level06 extends Level
{
    int playerSize;
    boolean willEnemyTrackPlayer = false;
    Player p = new Player();
    EnemySpawner es1;
    EnemySpawner es2;
    boolean bossIsDead = false;
    int maxEnemyCount = 2;
    int timer = 0;
    boolean bossMode = false;
    MiniBoss1 mb; 
    MiniBossHealthBar  mbhb; 
    //MiniBossHealthBarContainer mbhbc = new MiniBossHealthBarContainer();         
    public void act()
    {
        super.act();
        playerSize = p.getSize();
        refillEnemySpawner();
        checkLevelCompletion();
        checkBossEnter();        
    }
    public Level06(Map map)
    {
        super(map);
        prepare();
        sizeGoal = 200;
    }
    public void prepare()
    {
        super.prepare();
        // add player
        addObject(p, 600, 800);
        p.setSize(50);
        // add walls
        Wall w1 = new Wall(200, 315);
        addObject(w1, 300, 250);
        Wall w2 = new Wall(250, 305);
        addObject(w2, 325, 450);
        Wall w3 = new Wall(200, 45);
        addObject(w3, 900, 250);
        Wall w4 = new Wall(250, 55);
        addObject(w4, 875, 450);
        Wall w5 = new Wall(125, 330);
        addObject(w5, 275, 100);
        Wall w6 = new Wall(125, 30);
        addObject(w6, 925, 100);
        // add enemies
        es1 = new EnemySpawner(1);
        es1.setAngleStart(70);
        es1.setAngleRange(40);
        addObject(es1, 100, 150);
        es2 = new EnemySpawner(1);
        es2.setAngleStart(70);
        es2.setAngleRange(40);
        addObject(es2, 1100, 150);
        
        mb = new MiniBoss1();
        mbhb = new MiniBossHealthBar();
        
        bossMode = false;
        setPaintOrder(MiniBossHealthBar.class);
        mb.go = false;
    }
    public int currentSize()
    {    
        return playerSize;
    }
    public double getCompletionPercentage()
    {         
        return (double)playerSize/sizeGoal;
    }
    private void checkBossEnter()
    {
        if (getCompletionPercentage() >= 1 && bossMode == false)
        {
            enterMiniBoss();
            bossMode = true;
        }
        
    }
    
    private void checkLevelCompletion()
    {
        // When finish Level06...
        if (mb.levelComplete == true)
        {
            this.levelComplete = true;
        } 
        if(goToNext){ 
            // add upgrade points if next Level not avaliable
            if(!getMap().getPlayerData().canAccess(Level07.class)){
                getMap().getPlayerData().incUpgradePoints();
                getMap().getPlayerData().incUpgradePoints();
            }
            
            
            // okay to go to Level07.
            //when level 08 exists, mark accessible.
            getMap().getPlayerData().markAccessible(Level07.class);
            returnToMap();
        }
    }
    private void refillEnemySpawner()
    {
        int randNum = Greenfoot.getRandomNumber(2) + 1;        
        if(getObjects(NPC.class).size() < maxEnemyCount && timer >5 && bossMode == false)
        {
        switch(randNum)
            {
            case 1:
            es1.setReplacementCount(1);
            timer = 0;
            break;
            case 2:
            es2.setReplacementCount(1);
            timer = 0;
            break;
            }
        }
        timer++;
    }
    
    private void enterMiniBoss()
    {
        
        //System.out.println("ENTER MINI BOSS");
        addObject(mb, this.getWidth()/2, 100);
        addObject(mbhb, this.getWidth()/2,100);
        //addObject(mbhbc, this.getWidth()/2,100);
        mb.go = true;
        this.removeObject(sb);
        this.removeObject(sbc);
    }
    public final double getBossHPPercent()
    {
        return ((double)mb.hp / mb.hpMax);
    }
    
    public int getMiniBossX()
    {
        if (mb != null && mb.getWorld() != null)
        {
            return mb.getX();
        }
        else
        {return 0;}
    }
    public int getMiniBossY()
        {if (mb != null &&  mb.getWorld() != null)
        {
            return mb.getY();
        }
        else
        {return 0;}
    }
}
