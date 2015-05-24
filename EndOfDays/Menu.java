package EndOfDays;
import java.io.IOException;
import javax.microedition.lcdui.*;

public class Menu extends Canvas //implements Runnable
{
    private Image imgLogo;
    
    private Image imgMenuNFStart;
    private Image imgMenuNFOptions;
    private Image imgMenuNFRank;
    private Image imgMenuNFHelp;
    private Image imgMenuNFAbout;
    private Image imgMenuNFExit;
    private Image imgBackGround;
    
    private Image imgMenuFStart;
    private Image imgMenuFOptions;
    private Image imgMenuFRank;
    private Image imgMenuFHelp;
    private Image imgMenuFAbout;
    private Image imgMenuFExit;
        
    private int YLogo;
    private int YMenuStart;
    private int YMenuOptions;
    private int YMenuRank;
    private int YMenuHelp;

    private int x;
    private int imgHeight;

    private int menuSelected;

    private Main main;

    private int w;
    private int h;

    private boolean isAboutExitPressed;

    public Menu(Main main) throws IOException
    {
        this.main = main;
        imgLogo = main.getLogoImage();
        imgBackGround = main.getBackgroundImage();

        imgMenuNFStart = Image.createImage("/StartNoFocus.png");
        imgMenuNFOptions = Image.createImage("/OptionsNoFocus.png");
        imgMenuNFHelp = Image.createImage("/HelpNoFocus.png");
        imgMenuNFRank = Image.createImage("/RankNoFocus.png");
        imgMenuNFAbout = Image.createImage("/AboutNoFocus.png");
        imgMenuNFExit = Image.createImage("/ExitNoFocus.png");

        imgMenuFStart = Image.createImage("/StartFocus.png");
        imgMenuFOptions = Image.createImage("/OptionsFocus.png");
        imgMenuFRank = Image.createImage("/RankFocus.png");
        imgMenuFHelp = Image.createImage("/HelpFocus.png");
        imgMenuFAbout = Image.createImage("/AboutFocus.png");
        imgMenuFExit = Image.createImage("/ExitFocus.png");

        menuSelected = 1;
        
        imgHeight = imgMenuNFStart.getHeight();

        isAboutExitPressed = false;
    }

    protected void paint(Graphics g)
    {
        w = getWidth();
        h = getHeight();
        int LogoHeight = imgLogo.getHeight();

        x = w/2;

        YLogo = 0;
        YMenuStart = LogoHeight + 10;
        YMenuOptions = imgHeight + YMenuStart + 5;
        YMenuRank = imgHeight + YMenuOptions + 5;
        YMenuHelp = imgHeight + YMenuRank + 5;

        drawImages(g);
    }

    public void drawImages(Graphics g)
    {
        g.drawImage(imgBackGround, 0, 0, Graphics.TOP | Graphics.LEFT);
        g.drawImage(imgLogo, x, YLogo, Graphics.TOP | Graphics.HCENTER);

        if(menuSelected == 1)
        {
            g.drawImage(imgMenuFStart, x, YMenuStart, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFOptions, x, YMenuOptions, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFRank, x, YMenuRank, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFHelp, x, YMenuHelp, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFAbout, 0, h, Graphics.BOTTOM | Graphics.LEFT);
            g.drawImage(imgMenuNFExit, w, h, Graphics.BOTTOM | Graphics.RIGHT);
        }
        else if (menuSelected == 2)
        {
            g.drawImage(imgMenuNFStart, x, YMenuStart, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuFOptions, x, YMenuOptions, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFRank, x, YMenuRank, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFHelp, x, YMenuHelp, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFAbout, 0, h, Graphics.BOTTOM | Graphics.LEFT);
            g.drawImage(imgMenuNFExit, w, h, Graphics.BOTTOM | Graphics.RIGHT);
        }
        else if (menuSelected == 3)
        {
            g.drawImage(imgMenuNFStart, x, YMenuStart, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFOptions, x, YMenuOptions, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuFRank, x, YMenuRank, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFHelp, x, YMenuHelp, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFAbout, 0, h, Graphics.BOTTOM | Graphics.LEFT);
            g.drawImage(imgMenuNFExit, w, h, Graphics.BOTTOM | Graphics.RIGHT);
        }
        else if(menuSelected == 4)
        {
            g.drawImage(imgMenuNFStart, x, YMenuStart, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFOptions, x, YMenuOptions, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFRank, x, YMenuRank, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuFHelp, x, YMenuHelp, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFAbout, 0, h, Graphics.BOTTOM | Graphics.LEFT);
            g.drawImage(imgMenuNFExit, w, h, Graphics.BOTTOM | Graphics.RIGHT);
        }
        else if(menuSelected == 5)
        {
            g.drawImage(imgMenuNFStart, x, YMenuStart, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFOptions, x, YMenuOptions, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFRank, x, YMenuRank, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFHelp, x, YMenuHelp, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuFAbout, 0, h, Graphics.BOTTOM | Graphics.LEFT);
            g.drawImage(imgMenuNFExit, w, h, Graphics.BOTTOM | Graphics.RIGHT);
        }
        else if(menuSelected == 6)
        {
            g.drawImage(imgMenuNFStart, x, YMenuStart, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFOptions, x, YMenuOptions, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFRank, x, YMenuRank, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFHelp, x, YMenuHelp, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFAbout, 0, h, Graphics.BOTTOM | Graphics.LEFT);
            g.drawImage(imgMenuFExit, w, h, Graphics.BOTTOM | Graphics.RIGHT);
        }
        else if(menuSelected == 7)
        {
            g.drawImage(imgMenuNFStart, x, YMenuStart, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFOptions, x, YMenuOptions, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFRank, x, YMenuRank, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFHelp, x, YMenuHelp, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(imgMenuNFAbout, 0, h, Graphics.BOTTOM | Graphics.LEFT);
            g.drawImage(imgMenuNFExit, w, h, Graphics.BOTTOM | Graphics.RIGHT);
        }
    }

    protected void pointerPressed(int x, int y)
    {
        int imgWidth = imgMenuNFStart.getWidth()/2;
        int imgExitWidth = imgMenuNFExit.getWidth();
        int imgExitHeight = imgMenuNFExit.getHeight();

        if(x > (this.x - imgWidth) && x < (this.x + imgWidth))
        {
            //start
             if(y > YMenuStart && y < YMenuStart + imgHeight)
             {
                if(menuSelected == 1)
                {
                    main.setChoice(1);
                    return;
                }
                menuSelected = 1;
                repaint();
             }
             //Options
             else if (y > YMenuOptions && y < YMenuOptions + imgHeight)
             {
                if(menuSelected == 2)
                {
                    main.setChoice(2);
                    return;
                }
                menuSelected = 2;
                repaint();
             }
             //Rank
             else if(y > YMenuRank && y < YMenuRank + imgHeight)
             {
                if(menuSelected == 3)
                {
                    main.setChoice(3);
                    return;
                }
                isAboutExitPressed = false;
                menuSelected = 3;
                repaint();
             }
             //Help
             else if(y > YMenuHelp && y < YMenuHelp + imgHeight)
             {
                if(menuSelected == 4)
                {
                    main.setChoice(4);
                    return;
                }
                isAboutExitPressed = false;
                menuSelected = 4;
                repaint();
             }
        }
        else if (y > h - imgExitHeight && y < h)
        {
            //About
            if(x > 0 && x < imgExitWidth)
            {
                isAboutExitPressed = true;
                menuSelected = 5;
                repaint();
            }
            //Exit
            else if(x > w - imgExitWidth && x < w)
            {
                isAboutExitPressed = true;
                menuSelected = 6;
                repaint();
            }
        }
    }


    protected void pointerReleased(int x, int y)
    {
        int imgExitWidth = imgMenuNFExit.getWidth();

        //About
        if (y > h - imgMenuNFExit.getHeight() && y < h && x > 0 && x < imgExitWidth)
        {
            main.setChoice(5);
            menuSelected = 5;
            repaint();
        }
        //Exit
        else if(y > h - imgMenuNFExit.getHeight() && y < h && x > w - imgExitWidth && x < w)
        {
            main.setChoice(6);
            menuSelected = 6;
            repaint();
        }
        else if(isAboutExitPressed == true)
        {
            isAboutExitPressed = false;
            menuSelected = 7;
            repaint();
        }
    }
}
