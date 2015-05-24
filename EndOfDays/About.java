/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EndOfDays;

import java.io.IOException;
import javax.microedition.lcdui.*;

public class About extends Canvas
{

    private String AboutMessage;
    private String Name;
    private String Version;
    private int w;
    private int h;
    private Main main;
    private Image imgBackNoFocus;
    private Image imgBackFocus;
    private Image imgBackground;
    private Image imgLogo;
    private boolean isBackButtonPressed;

    public About(Main main)
    {
        this.main = main;
        AboutMessage = "Created by:";
        Name = "Etienne Rashed";
        Version = "Version: 1.0";
        this.setFullScreenMode(true);
        imgBackground = main.getBackgroundImage();
        imgLogo = main.getLogoImage();
        try
        {
            imgBackNoFocus = Image.createImage("/BackNoFocus.png");
            imgBackFocus = Image.createImage("/BackFocus.png");
        }
        catch (IOException ex){ex.printStackTrace();}

        isBackButtonPressed = false;

    } 
    


    public void paint(Graphics g)
    {
        w = getWidth();
        h = getHeight();
        try
        {
            g.setColor(0xFF7800);
            g.fillRect(0, 0, w, h);

            //draw background
            g.drawImage(imgBackground, 0, 0, Graphics.TOP | Graphics.LEFT);

            //draw Logo
            g.drawImage(imgLogo, w/2, 0, Graphics.TOP | Graphics.HCENTER);


            //draw backButton
            if(isBackButtonPressed == true)
            {
                g.drawImage(imgBackFocus, 0, h, Graphics.BOTTOM | Graphics.LEFT);
            }
            else
            {
                g.drawImage(imgBackNoFocus, 0, h, Graphics.BOTTOM | Graphics.LEFT);
            }

            //draw info
            Font f = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
            g.setFont(f);
            g.setColor(0x0000FF);
            g.drawString(Version, w/2, imgLogo.getHeight() + 10, Graphics.TOP | Graphics.HCENTER);
            g.drawString(AboutMessage, w/2, h/2, Graphics.BASELINE | Graphics.HCENTER);
            g.drawString(Name, w/2, h/2 + Font.SIZE_LARGE+10, Graphics.BASELINE | Graphics.HCENTER);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
   
    protected  void pointerPressed(int x, int y)
    {
        //back button is pressed
        if(x > 0 && x < imgBackNoFocus.getWidth() && y > h - imgBackNoFocus.getHeight() && y < h)
        {
            isBackButtonPressed = true;
            this.repaint();
        }
    }

    protected void pointerReleased(int x, int y)
    {
        //back button is pressed
        if(x > 0 && x < imgBackNoFocus.getWidth() && y > h - imgBackNoFocus.getHeight() && y < h)
        {
            isBackButtonPressed = false;
            main.setChoice(0);
        }
        else if(isBackButtonPressed == true)
        {
            isBackButtonPressed = false;
            this.repaint();            
        }
    }
}