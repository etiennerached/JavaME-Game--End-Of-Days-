package EndOfDays;

import java.io.IOException;
import javax.microedition.lcdui.*;

public class HighScores extends Canvas
{
    private final short HIGH_SCORE_NUMBER = 10;
    private int w;
    private int h;
    private Main main;
    private Image imgBackNoFocus;
    private Image imgBackFocus;
    private SaveLoadData saveLoadData;
    private boolean isBackButtonPressed;

    public HighScores(Main main)
    {
        saveLoadData = new SaveLoadData();
        this.main = main;
        this.setFullScreenMode(true);
        isBackButtonPressed = false;
    } 
    


    public void paint(Graphics g)
    {
        w = getWidth();
        h = getHeight();
        try
        {
            String []highScoresString = saveLoadData.getHighScores();

            imgBackFocus = Image.createImage("/BackFocus.png");
            imgBackNoFocus = Image.createImage("/BackNoFocus.png");

            Image backgroundImg = main.getBackgroundImage();
            Image logoImg = main.getLogoImage();
            Image rankImg = Image.createImage("/RankFocus.png");

            

            g.setColor(0xFF7800);
            g.fillRect(0, 0, w, h);

            g.drawImage(backgroundImg, 0, 0, Graphics.TOP | Graphics.LEFT);
            g.drawImage(logoImg, w/2, 0, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(rankImg, w/2, logoImg.getHeight() + 10, Graphics.TOP | Graphics.HCENTER);

            //draw Back
            if(isBackButtonPressed == true)
            {
                g.drawImage(imgBackFocus, 0, h, Graphics.BOTTOM | Graphics.LEFT);
            }
            else
            {
                g.drawImage(imgBackNoFocus, 0, h, Graphics.BOTTOM | Graphics.LEFT);
            }

            Font f = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
            g.setFont(f);
            g.setColor(0x0000FF);

            for(int i = 0; i < HIGH_SCORE_NUMBER; i+=2)
            {
                if( highScoresString[i].equals("0"))
                {
                    g.drawString( ((i/2)+1)+"", (w * 25) / 100, h/2 + (Font.SIZE_LARGE * i), Graphics.BASELINE | Graphics.HCENTER);
                    g.drawString( "_____" , w/2, h/2 + (Font.SIZE_LARGE * i), Graphics.BASELINE | Graphics.HCENTER);
                    g.drawString( "_____" , (w * 75) / 100, h/2 + (Font.SIZE_LARGE * i), Graphics.BASELINE | Graphics.HCENTER);
                }
                else
                {
                    g.drawString( ((i/2)+1)+"", (w * 25) / 100, h/2 + (Font.SIZE_LARGE * i), Graphics.BASELINE | Graphics.HCENTER);
                    g.drawString( highScoresString[i] , w/2, h/2 + (Font.SIZE_LARGE * i), Graphics.BASELINE | Graphics.HCENTER);
                    g.drawString( highScoresString[i+1] , (w * 75) / 100, h/2 + (Font.SIZE_LARGE * i), Graphics.BASELINE | Graphics.HCENTER);
                }

            }
        }
        catch (IOException ex)
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