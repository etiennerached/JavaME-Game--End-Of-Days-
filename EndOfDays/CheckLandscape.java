package EndOfDays;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


public class CheckLandscape extends Canvas
{
    private int w;
    private int h;
    private String message;
    private Image img;

    public CheckLandscape()
    {
        this.setFullScreenMode(true);
        message = "Please switch to the landscape mode";
        w = getWidth();
        h = getHeight();
        //img = Image.createImage(360, 640);
    }

    protected void paint(Graphics g)
    {
        //initialize img
        //if(img == null)
        {
            int width = 360;
            int height = 640;

            img = Image.createImage(width, height);
            Graphics graphics = img.getGraphics();
            graphics.setColor(0x000000);
            graphics.fillRect(0, 0, width, height);

            graphics.setColor(0xFF0000);
            Font f = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_SMALL);
            graphics.setFont(f);
            graphics.drawString(message, 0, height/2, Graphics.BASELINE | Graphics.LEFT);
        }

        g.drawImage(img, 0, 0, Graphics.TOP | Graphics.LEFT);

    }

    public boolean isLandscape()
    {
        w = getWidth();
        h = getHeight();
        
        if(w > h)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
