import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

/**
 * Write a description of class IncreaseIcon here.
 * 
 * @author Mateus Seehagen Rodrigues 
 * @version November 19th 2014
 */
public class IncreaseIcon extends Actor
{
    // Parameters for button appearance
    private int diameter;
    private Color color;
    private Color highlightColor;
    private Color disabledColor = Color.GRAY;
    private String text;
    
    // Button images
    private GreenfootImage imgOut;
    private GreenfootImage imgOver;
    private GreenfootImage imgDisabled;
        
    /**
     * The number of upgrades avaliable.
     */
    private int upgradeType;
    
    /**
     * The player data to use to upgrade the main character.
     */
    private PlayerData data;
    
    /**
     * Act - do whatever the IncreaseIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // If there is upgrade points
        if (data.getUpgradePoints() <= 0) 
        {
            setImage(imgDisabled);
            return;
        }
        
        // or if have 5 or more points in that atributte       
        if (this.upgradeType == 101 && data.getPointsSpeed() >= 5){
            setImage(imgDisabled);
            return;
        }
        if (this.upgradeType == 102 && data.getPointsShotRange() >= 5){
            setImage(imgDisabled);
            return;
        }
        if (this.upgradeType == 103 && data.getPointsRecoil() >= 5){
            setImage(imgDisabled);
            return;
        }
        
        final MouseInfo mi = Greenfoot.getMouseInfo();
        
        // If the mouse is over the button...
        if (mi != null && Math.hypot(mi.getX() - getX(), mi.getY() - getY()) < diameter / 2)
        {
            // ...switch to the "hover" image.
            setImage(imgOver);
            
            // If the mouse is also pressed...
            if (Greenfoot.mousePressed(null))
            {
                // ... then upgrade.
                if(this.upgradeType == 101){
                    data.upgradeSpeed();
                } else if(this.upgradeType == 102){
                    data.upgradeShotRange();
                } else if(this.upgradeType == 103){
                    data.upgradeRecoil();
                }
            }
        }
        else
        {
            // Mouse is not over the button.
            setImage(imgOut);
        }
    }    
    
    public IncreaseIcon(int diameter, Color color, PlayerData data, int upgradeType) {
        // Set fields.
        this.diameter = diameter;
        this.color = color;
        this.highlightColor = color.darker();
        this.text = "+";
        this.data = data;
        this.upgradeType = upgradeType;
        
        // Create the three images: mouse out, mouse over, and disabled.
        imgOut = createImage(diameter, color, text);
        imgOver = createImage(diameter, highlightColor, text);
        imgDisabled = createImage(diameter, disabledColor, text);
    }
    
    
    /**
     * This method creates a GreenfootImage and draws the button image on it.
     */
    private static GreenfootImage createImage(int diameter, Color color, String text)
    {
        // Create an image on which to draw.
        GreenfootImage image = new GreenfootImage(diameter, diameter);
        
        // Get the graphics context (the "canvas").
        Graphics2D g2d = ((Graphics2D) image.getAwtImage().getGraphics());
        
        // Anti-alias for smooth graphics.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill in background oval.
        g2d.setColor(color);
        g2d.fillOval(1, 1, diameter - 2, diameter - 2);
        
        // Outline background oval.        
        final int strokeWidth = 4;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(strokeWidth));
        g2d.drawOval(strokeWidth / 2, strokeWidth / 2, diameter - strokeWidth, diameter - strokeWidth);
        
        // Set the font size, and determine what size the text will be.
        final float fontFactor = 0.6f;
        g2d.setFont(image.getFont().deriveFont(diameter * fontFactor));
        final int textWidth = g2d.getFontMetrics().stringWidth(text);
        final int textHeight = g2d.getFontMetrics().getAscent();
        
        // Determine text position (center in circle).
        final int textX = (diameter - textWidth * 9 / 10) / 2; // tends to overshoot a bit
        final int textY = diameter - textHeight / 2;
        
        // Draw text.
        g2d.drawString(text, textX, textY);
        
        // Finish up.
        g2d.dispose();
        return image;
    }
}
