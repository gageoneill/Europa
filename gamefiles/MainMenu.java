import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main game menu, with the title and play button.
 * 
 * @author William Chargin
 * 
 * @version 3 October 2014: Initial version
 */
public class MainMenu extends World
{
    
    /**
     * The object that manages fading in and out of the main menu.
     */
    private FadeManager manager;

    /**
     * The standard duration for fading in and out.
     */
    private final int fadeDuration = 30;

    /**
     * True if we are fading in (from black); false if we are fading out (to black).
     */
    private boolean fadingIn = true;

    /**
     * The number of frames to delay before adding the title prompt.
     */
    private int promptDelay = 80;

    /**
     * The number of frames that have passed; when this reaches {@link #promptDelay},
     * the prompt will be added.
     */
    private int promptTimer = 0;
    
    private boolean musicStarted = false;
    
    /**
     * @author Jaskaran Buttar
     */
    private GreenfootSound bkgMusic;//adds music to the main menu
    /**
     * Creates the main menu, adding the title text and planets.
     */
    public MainMenu()
    {
        super(1200, 900, 1, false); 

        Greenfoot.setSpeed(50);
        
        // Set up the fade manager.
        manager = new FadeManager(this);
        manager.fadeIn(fadeDuration);

        bkgMusic = new GreenfootSound("halo3music.mp3");

        // We want to add the title text so that it is at y = 331 when it finishes animating.
        // However, we know that it moves up some from its starting position, so we have to
        // subtract that value out for it to end up in the right spot.
        final TitleText titleText = new TitleText();
        addObject(titleText, getWidth() / 2, 331 - titleText.movement);

        // Add the planets at some arbitrary locations at the top of the screen.
        addObject(new Earth(), 100, 80);
        addObject(new Mars(), 1000, 180);

        // Make sure to paint things in the right order.
        setPaintOrder(
            ScreenShade.class,
            TitleText.class,
            TitleFlare.class,
            TitlePlanet.class,
            TitlePrompt.class
        );
    }
    
    public void started()
    {
        if (!musicStarted)
        {
            bkgMusic.playLoop();
            musicStarted = true;
        }
    }
    
    /**
     * Checks to see if the title prompt ("press to start") should be added.
     */
    private void checkPrompt() {
        if (promptTimer < promptDelay) {
            // Not ready yet.
            promptTimer++;
        } else if (promptTimer == promptDelay) {
            // It's showtime.
            final TitlePrompt prompt = new TitlePrompt();
            addObject(prompt, getWidth() / 2, 750);
            promptTimer++;
        }
    }

    /**
     * Check to see if the user has pressed start, and go to the next world if so.
     */
    private void checkStart() {
        if (Greenfoot.isKeyDown("enter")) {
            manager.fadeOut(fadeDuration);
            //bkgMusic.stop();//stops the music when you press enter
        }
        if (manager.isFadedOut()) {
            Greenfoot.setWorld(new CutScene01());
        }
        if (Greenfoot.isKeyDown("j"))
        {
            Greenfoot.setWorld(new Jaz());
    }
}
    @Override
    public void act() {
        checkPrompt();
        checkStart();
        manager.act();
    }
    
}
