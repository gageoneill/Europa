import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * First cutscene of Europa game, make all the movement and introduces the story.
 * All background images obtained from:
 * http://10wallpaper.com/list/Exploration_of_the_universe_-_space_and_space_technology_design_wallpaper.html
 * 
 * @author Mateus Seehagen Rodrigues
 * @version October 28th, 2014
 */
public class CutScene01 extends CutScene
{
    private Rocket rocket;
    private FadeManager fm;
    private int state;
    private boolean flag;
    public CutScene01()
    {
        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        setBackground("cutscene01part01.jpg");
        
        rocket = new Rocket();
        addObject(rocket, 580, 650);
        rocket.setRotation(315);
        
        fm = new FadeManager(this);
        fm.fadeIn(5);
        state = 0;
        flag = true;
        
        CutSceneSkip css = new CutSceneSkip();
        addObject(css, 600, 850);
    }    
    public void act(){
        fm.act();
        
        // first part of the cutscence
        if (state == 0){
            if (rocket.getRotation() > 270){    // first movement
                rocket.setLocation(rocket.getX()+1, rocket.getY()-2);
            }
            else if (rocket.getRotation() < 270 && rocket.getRotation() > 225){ // second movement
                rocket.setLocation(rocket.getX()-1, rocket.getY()-2);
            }
            else if (rocket.getRotation() == 225){  // final movement
                rocket.setLocation(rocket.getX()-2, rocket.getY()-2);
                if(flag){   // starts fading
                    fm.fadeOut(100);
                    flag = false;
                }
            }
            
            if(rocket.getX() % 2 == 0 && rocket.getRotation() > 225){   // set Rotation
                rocket.setRotation(rocket.getRotation()-1);
            }
            
            if(fm.isFadedOut()){    // when faded, next stage
                state++;
                setBackground("cutscene01part02.jpg");
                rocket.setLocation(1000,750);
                flag = true;
                
                fm.fadeIn(1);
            }
        }
        else if(state == 1){
            rocket.setLocation(rocket.getX()-1, rocket.getY()-1);
            
            if(rocket.getX() == 700){
                fm.fadeOut(100);
            }
            
            if(fm.isFadedOut()){    // when faded, next stage
                state++;
                setBackground("cutscene01part03.jpg");
                rocket.setLocation(600,100);
                rocket.setRotation(270);
                
                fm.fadeIn(1);
            }
        }
        
        else if(state == 2){
            
            if(rocket.getY() < 450){ // aproching planet
                rocket.setLocation(rocket.getX(), rocket.getY()+2);
            }
            else if(rocket.getY() < 600 && rocket.getY() >= 450){ // landing
                rocket.setLocation(rocket.getX(), rocket.getY()+1);
                double prob = Math.sqrt((rocket.getY() - 450.0) / (600.0 - 450.0)) / 2;
                if (Math.random() < prob)
                {
                    launchParticles(new Color(127, 127, 127, 180));
                    launchParticles(new Color(255, 255, 255, 180));
                }
            }
            else{
                if (flag) {
                    fm.fadeOut(100);
                    flag = false;
                }
            }
            
            if(fm.isFadedOut()){    // when faded, next stage
                state++;              
            }
        }
        
        else if (state == 3){
            Greenfoot.setWorld(new CutScene02());
        }
        
        if (Greenfoot.isKeyDown("space")) { // space to skip
            Greenfoot.setWorld(new CutScene02());
        }
    }
    public void launchParticles(Color baseColor)
    {
        final int numParticles = 8;
        final int particleSize = 15;
        for (int i = 0; i < numParticles; i++)
        {
            Particle p = new Particle(baseColor, particleSize);
            addObject(p, rocket.getX(), rocket.getY() + 25);
            p.increaseVelocityPolar(5 + 3 * Math.random(), Math.random() * 360);
        }
    }
}
