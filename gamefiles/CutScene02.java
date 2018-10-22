import greenfoot.*;
/**
 * 
 * @author Gage O'Neill
 * @version 11 November 14
 * 
 */
public class CutScene02 extends CutScene
{
    private Rocket rocket;
    private Geyser geyser;
    private FakePlayer player;
    private FadeManager fm;
    private int pauseTimer = 0;
    private int spin = 0;
    private boolean atTop = false;
    private boolean gAtTop = false;
    private boolean flag = true;
    public CutScene02()
    {
        setPaintOrder(FakePlayer.class, Particle.class);
        prepare();
    }
    private void prepare()
    {
        // set background
        setBackground("cutscene01part03.jpg");
        // add rocket
        rocket = new Rocket();
        addObject(rocket, 600, 600);
        rocket.setRotation(270);
        // add geyser
        geyser = new Geyser();
        addObject(geyser, 1100, 1200);
        geyser.setRotation(0);
        // add hero
        player = new FakePlayer();
        addObject(player, 1100, 900);
        player.setRotation(90);
        
        fm = new FadeManager(this);
        fm.fadeIn(5);
        
        CutSceneSkip skip = new CutSceneSkip();
        addObject(skip, 600, 850);
    }
    public void act()
    {
        fm.act();
        // move player
        spin++;
        player.setRotation(spin);        
        if(!atTop)
        {
            player.setLocation(player.getX(), player.getY() - 2);                        
        }       
        if(player.getY() < 200)
        {
            atTop = true;
            pauseTimer++;
        }
        if(pauseTimer > 50)
        {
             player.setLocation(player.getX(), player.getY() + 2);
        }        
        // move geyser
        if(!gAtTop)
        {
            geyser.setLocation(geyser.getX(), geyser.getY() - 2);
        }
        if(geyser.getY() < 500){
            gAtTop = true;
        }
        if(gAtTop)
        {
            geyser.setLocation(geyser.getX(), geyser.getY() + 2);
        }
        if(geyser.getY() > 1000 && player.getY() > 900 && flag)
        {
            fm.fadeOut(30);
            flag = false;
        }
        if(fm.isFadedOut())
        {
            Greenfoot.setWorld(new Map());
        }
        // enable skip
        if(Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new Map());
        }
    }
}
