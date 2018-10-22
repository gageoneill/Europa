import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.util.Set;

/**
 * This is a button that, when clicked, opens the upgrade menu.
 * 
 * @author Mateus Seehagen Rodrigues
 * 
 * @version 04 November 2014: Created
 */
public class UpgradeIcon extends Actor
{
    // Parameters for button appearance
    private Color color;
    private Color highlightColor;
    private String text;
    
    // Button images
    private GreenfootImage imgOut;
    private GreenfootImage imgOver;
    
    // Condition of the upgrades
    private boolean open = false;
    
    // Actors of the GUI
    private IncreaseIcon speedUp;
    private IncreaseIcon shotRangeUp;
    private IncreaseIcon recoilUp;
    private UpgradesGUI  upGUI;
    
    // Player data that contains information about the upgrades
    private PlayerData data;
    private Set<Class<? extends Level>> levels;
    
    
    public void act()
    {
        final MouseInfo mi = Greenfoot.getMouseInfo();
        
        // If the mouse is over the button...
        if (mi != null && Math.hypot(mi.getX() - getX(), mi.getY() - getY()) < 200 / 2)
        {
            // ...switch to the "hover" image.
            setImage(imgOver);
            
            // If the mouse is also pressed...
            if (Greenfoot.mousePressed(null))
            {
                // ...then open or close the GUI
                Map map = (Map) getWorld();
                
                if(open){ // closes the GUI
                    map.removeObject(upGUI);
                    map.removeObject(speedUp);
                    map.removeObject(shotRangeUp);
                    map.removeObject(recoilUp);
                    
                    // return access to the levels
                    data.setLevels(levels);
                    levels.clear();
                    
                    open = false;
                } else { // opens the GUI
                    // remove access to the levels
                    //HashSet
                    levels = data.getLevels();
                    data.markAllInaccessible();
                    
                    int halfX = map.getWidth()/2;
                    
                    upGUI = new UpgradesGUI(data);
                    map.addObject(upGUI, halfX, 200);
                    
                    speedUp = new IncreaseIcon(50, Color.RED, data, 101);
                    map.addObject(speedUp, halfX + 225, 70+50);
                    shotRangeUp = new IncreaseIcon(50, Color.RED, data, 102);
                    map.addObject(shotRangeUp, halfX + 225, 70+130);
                    recoilUp = new IncreaseIcon(50, Color.RED, data, 103);
                    map.addObject(recoilUp, halfX + 225, 70+210);
                    
                    open = true;
                }
            }
        }
        else
        {
            // Mouse is not over the button.
            setImage(imgOut);
        }
        
        updateImages();
    }
    
    public UpgradeIcon(PlayerData data)
    {
        // Set fields.
        this.color = Color.ORANGE;
        this.highlightColor = color.darker();
        this.text = "Upgrades";
        this.data = data;
        
        // Create the three images: mouse out, mouse over, and disabled.
        imgOut = createImage(color, text);
        imgOver = createImage(highlightColor, text);
    }
    
    /**
     * This method creates a GreenfootImage and draws the button image on it.
     */
    private static GreenfootImage createImage(Color color, String text)
    {
        int width = 250;
        int height = 90;
        
        // Create an image on which to draw.
        GreenfootImage image = new GreenfootImage(width, height);
        
        // Get the graphics context (the "canvas").
        Graphics2D g2d = ((Graphics2D) image.getAwtImage().getGraphics());
        
        // Anti-alias for smooth graphics.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill in background oval.
        g2d.setColor(color);
        g2d.fillOval(1, 1, width - 2, height - 2);
        
        // Outline background oval.        
        int strokeWidth = 4;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.drawOval(strokeWidth / 2, strokeWidth / 2, width - strokeWidth, height - strokeWidth);
        
        // Set the font size, and determine what size the text will be.
        float fontFactor = 0.4f;
        g2d.setFont(image.getFont().deriveFont(height * fontFactor));
        int textWidth = g2d.getFontMetrics().stringWidth(text);
        int textHeight = g2d.getFontMetrics().getAscent();
        
        // Determine text position (center in circle).
        int textX = (width - textWidth) / 2; // tends to overshoot a bit
        int textY = (height + textHeight*8/10) / 2;
        
        // Draw text.
        g2d.drawString(text, textX, textY);
        
        // Finish up.
        g2d.dispose();
        return image;
    }
    
    public void updateImages(){
        int nUpgrades = this.data.getUpgradePoints();
        
        if(nUpgrades != 0){
            this.text = "Upgrades +"+Integer.toString(nUpgrades);
        } else {
            this.text = "Upgrades";
        }
        
        this.imgOut = createImage(color, text);
        this.imgOver = createImage(highlightColor, text);
    }
    
    
}

