package EndOfDays;

import java.io.IOException;
import javax.microedition.lcdui.*;

public class Options extends Canvas
{

    private Image backgroundImage;
    private Image imgLogo;
    private Image imgOptions;
    private Image imgSoundOn;
    private Image imgMusicOff;
    private Image imgSoundOff;
    private Image imgVibrateOn;
    private Image imgVibrateOff;
    private Image imgEasy;
    private Image imgNormal;
    private Image imgHard;
    private Image imgBack;
    private Image imgBackFocus;

    private int width;
    private int height;
    private int YLogo;
    private int YOptions;
    private int YSound;
    private int YVibrate;
    private int YDifficulty;

    private boolean isBackButtonPressed;

    private Main main;

    public Options(Main main)
    {
        this.main = main;
        this.setFullScreenMode(true);

        try
        {
            backgroundImage = this.main.getBackgroundImage();
            imgLogo = this.main.getLogoImage();
            imgOptions = Image.createImage("/OptionsFocus.png");
            imgSoundOn = Image.createImage("/son.png");
            imgMusicOff = Image.createImage("/moff.png");
            imgSoundOff = Image.createImage("/soff.png");
            imgVibrateOn = Image.createImage("/von.png");
            imgVibrateOff = Image.createImage("/voff.png");
            imgEasy = Image.createImage("/e.png");
            imgNormal = Image.createImage("/n.png");
            imgHard = Image.createImage("/h.png");
            imgBack = Image.createImage("/BackNoFocus.png");
            imgBackFocus = Image.createImage("/BackFocus.png");
        }
        catch (IOException ex){ex.printStackTrace();}

        width = getWidth();
        height = getHeight();

        YLogo = 0;
        YOptions = YLogo + imgLogo.getHeight() + 5;
        YSound = YOptions + imgOptions.getHeight() + 20;
        YVibrate = YSound + imgSoundOn.getHeight() + 20;
        YDifficulty = YVibrate + imgVibrateOn.getHeight() + 20;
    } 

    public void paint(Graphics g)
    {
        width = getWidth();
        height = getHeight();
        
        g.drawImage(backgroundImage, 0, 0, Graphics.TOP | Graphics.LEFT);
        g.drawImage(imgLogo, width/2, YLogo, Graphics.TOP | Graphics.HCENTER);
        g.drawImage(imgOptions, width/2, YOptions, Graphics.TOP | Graphics.HCENTER);

        //draw Sound
        if(main.getSoundStatus() == 0)
        {
           g.drawImage(imgSoundOn, width/2, YSound, Graphics.TOP | Graphics.HCENTER);
        }
        else if(main.getSoundStatus() == 1)
        {
            g.drawImage(imgMusicOff, width/2, YSound, Graphics.TOP | Graphics.HCENTER);
        }
        else
        {
            g.drawImage(imgSoundOff, width/2, YSound, Graphics.TOP | Graphics.HCENTER);
        }

        //draw Vibrate
        if(main.getVibrateStatus() == 0)
        {
            g.drawImage(imgVibrateOn, width/2, YVibrate, Graphics.TOP | Graphics.HCENTER);
        }
        else
        {
            g.drawImage(imgVibrateOff, width/2, YVibrate, Graphics.TOP | Graphics.HCENTER);
        }

        //draw Difficulty
        if(main.getDifficulty() == 0)
        {
            g.drawImage(imgEasy, width/2, YDifficulty, Graphics.TOP | Graphics.HCENTER);
        }
        else if(main.getDifficulty() == 1)
        {
            g.drawImage(imgNormal, width/2, YDifficulty, Graphics.TOP | Graphics.HCENTER);
        }
        else if(main.getDifficulty() == 2)
        {
            g.drawImage(imgHard, width/2, YDifficulty, Graphics.TOP | Graphics.HCENTER);
        }
        //draw Back
        if(isBackButtonPressed == true)
        {
            g.drawImage(imgBackFocus, 0, height, Graphics.BOTTOM | Graphics.LEFT);
        }
        else
        {
            g.drawImage(imgBack, 0, height, Graphics.BOTTOM | Graphics.LEFT);
        }
    }
    

    protected  void pointerPressed(int x, int y)
    {
        //if Sound clicked
        if(x > ((width/2) - (imgSoundOn.getWidth()/2)) && x < ((width/2) + (imgSoundOn.getWidth()/2)) && y > YSound && y < (YSound + imgSoundOn.getHeight()))
        {
            if(main.getSoundStatus() == 0)
            {
                main.setSoundStatus(1);
                this.repaint();
            }
            else if(main.getSoundStatus() == 1)
            {
                main.setSoundStatus(2);
                this.repaint();
            }
            else
            {
                main.setSoundStatus(0);
                this.repaint();
            }
        }

        //if Vibrate clicked
         if(x > ((width/2) - (imgVibrateOn.getWidth()/2)) && x < ((width/2) + (imgVibrateOn.getWidth()/2)) && y > YVibrate && y < (YVibrate + imgVibrateOn.getHeight()))
        {
            if(main.getVibrateStatus() == 0)
            {
                main.setVibrateStatus(1);
                this.repaint();
            }
            else
            {
                main.setVibrateStatus(0);
                this.repaint();
            }
        }

        //if Difficulty clicked
         if(x > ((width/2) - (imgEasy.getWidth()/2)) && x < ((width/2) + (imgEasy.getWidth()/2)) && y > YDifficulty && y < (YDifficulty + imgEasy.getHeight()))
        {
            if(main.getDifficulty() == 0)
            {
                main.setDifficulty(1);
                this.repaint();
            }
            else if(main.getDifficulty() == 1)
            {
                main.setDifficulty(2);
                this.repaint();
            }
            else
            {
                main.setDifficulty(0);
                this.repaint();
            }
        }

        //back button is pressed
        if(x > 0 && x < imgBack.getWidth() && y > height - imgBack.getHeight() && y < height)
        {
            isBackButtonPressed = true;
            this.repaint();
        }
    }

    /**
     * Called when the pointer is released.
     */
    protected  void pointerReleased(int x, int y)
    {
        //back button is pressed
        if(x > 0 && x < imgBack.getWidth() && y > height - imgBack.getHeight() && y < height)
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