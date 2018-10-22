import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UpgradesGUI here.
 * 
 * @author Mateus Seehagen Rodrigues
 * @version November 20th
 */
public class UpgradesGUI extends Actor
{
    private PlayerData data;
    
    private int pointsSpeed;
    private int pointsShotRange;
    private int pointsRecoil;
    
    public UpgradesGUI(PlayerData data){
        this.data = data;
        
        this.pointsSpeed = data.getPointsSpeed();
        this.pointsShotRange = data.getPointsShotRange();
        this.pointsRecoil = data.getPointsRecoil();
        
        update();
    }
    
    /**
     * Act - do whatever the UpgradesGUI wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if(pointsSpeed != data.getPointsSpeed()){
            pointsSpeed = data.getPointsSpeed();
            update();
        }
        if(pointsShotRange != data.getPointsShotRange()){
            pointsShotRange = data.getPointsShotRange();
            update();
        }
        if(pointsRecoil != data.getPointsRecoil()){
            pointsRecoil = data.getPointsRecoil();
            update();
        }
    }
    
    public void update(){
        setImage("UpgradesGUI.jpg");
        GreenfootImage image = getImage();
        
        image.setFont(image.getFont().deriveFont(45f));
        
        image.drawString(Integer.toString(pointsSpeed), 380, 63);
        image.drawString(Integer.toString(pointsShotRange), 380, 143);
        image.drawString(Integer.toString(pointsRecoil), 380, 223);
    }
}
