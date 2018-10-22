import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Shows stats on your time, shot-kill ratio, and death count.
 * 
 * @author William Chargin
 */
public class StatisticsDisplay extends World
{

    final Map map;
    public StatisticsDisplay(PlayerData data, Map previous)
    {    
        super(1200, 900, 1, false);
        setBackground(drawImage(data));
        addObject(new TitlePrompt(), getWidth() / 2, getHeight() * 3 / 4);
        this.map = previous;
    }
    
    public void act()
    {
        if (Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.setWorld(map);
        }
    }
    
    private GreenfootImage drawImage(PlayerData data)
    {
        GreenfootImage image = new GreenfootImage(getBackground());
        String[] labels = { "Time", "Deaths", "Shot\u2013Kill Ratio" };
        String[] values = {
            data.getTimeSinceStart(),
            String.format("%s", data.deathCount),
            String.format("%.02f", (double) data.enemiesKilled / data.shotsFired)
        };
        final int n = Math.min(labels.length, values.length);
        
        Graphics2D g2d = (Graphics2D) image.getAwtImage().getGraphics();
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(40f));
        FontMetrics fm = g2d.getFontMetrics();
        int longestWidth = -1;
        for (String label : labels)
        {
            int width = fm.stringWidth(label);
            if (width > longestWidth)
            {
                longestWidth = width;
            }
        }
        
        int offsetX = 150;
        int offsetY = 300;
        int padding = 50;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        for (int i = 0; i < n; i++)
        {
            String label = labels[i];
            String value = values[i];
            int labelWidth = fm.stringWidth(label);
            int labelX = offsetX + longestWidth - labelWidth;
            int y = offsetY + fm.getHeight() * i + fm.getAscent();
            g2d.drawString(label, labelX, y);
            g2d.drawString(value, offsetX + longestWidth + padding, y);
        }
        g2d.dispose();
        return image;
    }
    
}
