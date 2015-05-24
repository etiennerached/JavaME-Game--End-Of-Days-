package EndOfDays;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import javax.microedition.midlet.*;


public class Main extends MIDlet implements Runnable, CommandListener
{
    private Menu menu; //Menu Canvas
    private Help help; //Help canvas
    private About about; //About Canvas
    private Options options; //Options Canvas
    private HighScores highScores;//HighScores Canvas
    private Thread t; //Thread to start the game
    private RunGame runGame; //Run Game GameCanvas
    private Display d; //The current displayable
    private boolean endApp; //indicate wheather the app is to be terminated or not
    private short levelNumber; //hold the number of the current level
    private int score; //the game score
    private SaveLoadData saveLoadData; //Class to store and retrieve the options and highscore
    private int index; //used to find the highscore
    private int prevChoice; //hold the previous selected menu choice
    private int choice; //hold the current menu choice
    private int []highScoresInt; //Hold all the highscores
    private int []optionsData; //Hold the options data
    private Form form; //to enter the highscore info (name)
    private Command cDone; //Done command for the "form"
    private TextField textField;//to enter the name of the highscorer in the form
    private InputStream is; //Input stream for the music
    private Player player; //Player for the music
    private Image backgroundImage; //Background Image (to be used in all canvas)
    private Image logoImage; //Logo image (to be used in all canvas)

    public void startApp()
    {
        try
        {
            //Initialize data
            backgroundImage = Image.createImage("/Background.png");
            logoImage = Image.createImage("/Logo.png");

            menu = new Menu(this);
            help = new Help(this);
            about = new About(this);
            options = new Options(this);
            highScores = new HighScores(this);

            saveLoadData = new SaveLoadData();
            saveLoadData.createRecordStores();
            optionsData = saveLoadData.getOptions();

            loadIntro();
        }
        catch (Exception ex){}

        endApp = false;
        //show menu
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run()
    {
        prevChoice = 1;
        choice = 0;
        levelNumber = 1;
        score = 0;

        while(!endApp)
        {
            if(prevChoice != choice)
            {
                prevChoice = choice;
                startMidlet();
            }

            try
            {
                Thread.sleep(15);
            }
            catch (InterruptedException ex){}
        }
    }

    public void startMidlet()
    {
        d = Display.getDisplay(this);

        switch(choice)
        {
            //menu
            case 0:
            {
                if(menu == null)
                {
                    try
                    {
                        menu = new Menu(this);
                    }
                    catch (IOException ex) {}
                }
                //load music if not loaded
                loadIntro();

                menu.setFullScreenMode(true);
                d.setCurrent(menu);

                menu.repaint();
                levelNumber = 1;

                //play music
                playIntro();
            }
            break;

            //Start Game
            case 1:
            {
                nullify();//free memory for the game
                if(levelNumber == 1)
                {
                    highScoresInt = saveLoadData.getIntegerHighScores();
                    score = 0;
                }
                try
                {
                    runGame = new RunGame(this);
                }
                catch (MediaException ex){}

                d.setCurrent(runGame);
                t = new Thread(runGame);
                t.start();
            }
            break;

            //Options
            case 2:
            {
                if(options == null)
                {
                    options = new Options(this);
                }
                d = Display.getDisplay(this);
                d.setCurrent(options);
                options.repaint();
            }
            break;

            //High Score
            case 3:
            {
                if(highScores == null)
                {
                    highScores = new HighScores(this);
                }
                d = Display.getDisplay(this);
                d.setCurrent(highScores);
                highScores.repaint();
            }
            break;

            //Help
            case 4:
            {
                if(help == null)
                {
                    help = new Help(this);
                }
                d = Display.getDisplay(this);
                d.setCurrent(help);
                help.repaint();
            }
            break;

            //About
            case 5:
            {
                if(about == null)
                {
                    about = new About(this);
                }
                d = Display.getDisplay(this);
                d.setCurrent(about);
                about.repaint();
            }
            break;

            //Exit
            case 6:
            {
                //Save Options
                saveLoadData.setOptions(optionsData);
                //Destroy application
                destroyApp(true);
            }
            break;

            //Search HighScore when game is over,
            //to know if the new highscore will replace an old one
            case 7:
            {
                if(findHigherScore() == true)
                {
                    createForm();
                }
                else
                {
                    choice = 0;
                }
            }
            break;
        }
    }

    public void createForm()
    {
        form = new Form("New Record!");
        Ticker ticker = new Ticker("Your score is: " + score);
        textField = new TextField("Enter your name:", "", 6, TextField.ANY);
        cDone = new Command("Done", Command.OK, 0);

        form.append(textField);
        form.setTicker(ticker);
        form.addCommand(cDone);
        form.setCommandListener(this);

        d.setCurrent(form);
    }

    public boolean findHigherScore()
    {
        int length = highScoresInt.length;

        for(int i = 0; i < length; i++)
        {
            if (score > highScoresInt[i])
            {
                index = i;
                return true;
            }
        }
        return false;
    }

    public void loadIntro()
    {
        //if play sound or Music is on in options
        if(optionsData[0] == 0 || optionsData[0] == 1)
        {
            try
            {
                if(is == null || player == null)
                {
                    is = getClass().getResourceAsStream("/ex.wav");
                    player = Manager.createPlayer(is, "audio/x-wav");
                    player.realize();
                    player.prefetch();
                }
            }
            catch (IOException ex){}
            catch (MediaException ex){}
        }
    }

    public void playIntro()
    {
        //if play sound or Music is on in options
        if(optionsData[0] == 0 || optionsData[0] == 1)
        {
            try
            {
                //run sound
                player.start();
            }
            catch(Exception e){}
        }
    }
    public synchronized void setChoice(int c)
    {
        choice = c;
    }

    public synchronized void setScore(int score)
    {
        this.score = score;
    }

    public void nullify()
    {
        try
        {
            if(is != null)
            {
                is.close();
                is = null;
            }
            if(player != null)
            {
                player.close();
                player = null;
            }
            menu = null;
            about = null;
            help = null;
            highScores = null;
            System.gc();
        }
        catch (IOException ex) {ex.printStackTrace();}
    }

    public void pauseApp()
    {
    }

    public void destroyApp(boolean unconditional)
    {
        nullify();
        endApp = true;
        this.notifyDestroyed();
    }

    public void commandAction(Command command, Displayable displayable)
    {
        if(displayable == form)
        {
            if(command == cDone)
            {
                String highScoreName = textField.getString();
                if(highScoreName == null || highScoreName.equals(""))
                {
                    highScoreName = "Player";
                }
                
                String []highScoreStrings = saveLoadData.getHighScores();

                int length = highScoreStrings.length;
                for(int i = length-1; i >= (index*2)+2; i-=2)
                {
                    highScoreStrings[i]=highScoreStrings[i-2];
                    highScoreStrings[i-1] = highScoreStrings[i-3];
                }
                highScoreStrings[(index*2)]=highScoreName;
                highScoreStrings[(index*2)+1]= String.valueOf(score);
                saveLoadData.setScore(highScoreStrings);
 
                form = null;
                textField = null;
                cDone = null;
                System.gc();
                choice = 3;
            }
        }
    }

    public int getSoundStatus()
    {
        return optionsData[0];
    }

    public void setSoundStatus(int sound)
    {
        optionsData[0] = sound;
    }

    public int getVibrateStatus()
    {
        return optionsData[1];
    }

    public void setVibrateStatus(int vibrate)
    {
        optionsData[1] = vibrate;
    }

    public int getDifficulty()
    {
        return optionsData[2];
    }

    public void setDifficulty(int difficulty)
    {
        optionsData[2] = difficulty;
    }

    public int[] getOptionsData()
    {
        return optionsData;
    }

    public void setOptionsData(int []optionsData)
    {
        this.optionsData = optionsData;
    }

    public Image getBackgroundImage()
    {
        return backgroundImage;
    }

    public Image getLogoImage()
    {
        return logoImage;
    }
}
