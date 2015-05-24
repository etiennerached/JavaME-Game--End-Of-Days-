package EndOfDays;

import javax.microedition.lcdui.*;

public class StartupScreen extends Canvas
{
    private Image background;

    public StartupScreen(Image img)
    {
        this.setFullScreenMode(true);
        background = img;
    } 
    
    public void paint(Graphics g)
    {
        g.drawImage(background, 0 , 0, Graphics.TOP | Graphics.LEFT);
    }
}