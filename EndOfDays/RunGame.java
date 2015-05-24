
package EndOfDays;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.lcdui.game.TiledLayer;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

public class RunGame extends GameCanvas implements Runnable
{
    private final int NUMBER_OF_FIREBALLS = 10;
    private final int RANDOM_X_MOVE = 5;
    private int RANDOM_Y_MOVE = 5;
    private GameDesign gameDesign;
    private Sprite []Fireball;
    private Sprite []FireballExplosion;
    private Sprite Bomb;
    private Sprite Life;
    private int [][]FireballStatus;
    private int []BombStatus;
    private int []LifeStatus;
    private TiledLayer ResumeExit;
    private TiledLayer PauseExit;
    private LayerManager layerManager;
    private boolean running;
    private boolean isPaused;
    private boolean FireballPressed;
    private boolean isGameOver;
    private boolean isVibrateOn;
    private Main main;
    private Graphics g;
    private int w;
    private int h;
    private int FireballWidth;
    private int FireballHeight;
    private int FireballExplosionHeight;
    private int FireballExplosionWidth;
    private int BombX;
    private int BombY;
    private int BombWidth;
    private int BombHeight;
    private int LifeX;
    private int LifeY;
    private int LifeWidth;
    private int LifeHeight;
    private int Score;
    private int YSpeed;
    private int XSpeed;
    private int FireballX;
    private int FireballY;
    private int BombDropRate;
    private int LifeDropRate;
    private int RemainingLife;
    private int AddLife;
    private int isSoundOn;
    private int LEVEL1SCORE;
    private int LEVEL2SCORE;
    private int LEVEL3SCORE;
    private int LEVEL4SCORE;
    private int LEVEL5SCORE;
    private int LEVEL6SCORE;
    private int LEVEL7SCORE;
    private int LEVEL8SCORE;
    private int LEVEL9SCORE;
    private int timePlayedInSeconds;
    private Random random;
    private InputStream inputStream;
    private Player playerExplosion;
    private Player playerMusic;
    private Image GameOverImage;

    
    
    public RunGame(Main main) throws MediaException
    {
        super (true);
        this.setFullScreenMode(true);
        this.main = main;
        running = true;
        isPaused = false;
        isGameOver = false;
        Score = 0;
        XSpeed = -2;
        YSpeed = 1;
        random = new Random();
        gameDesign = new GameDesign();
        FireballX = 0;
        FireballY = 0;
        BombX = 0;
        BombY = 0;
        FireballPressed = false;
        BombStatus = new int [3];
        LifeStatus = new int [3];

        //Initialize isSoundOn
        isSoundOn = main.getSoundStatus();

        //Initialize isVibrateOn
        if(main.getVibrateStatus() == 0)
        {
            isVibrateOn = true;
        }
        else
        {
            isVibrateOn = false;
        }

        //
        //Initialize according to difficulty
        //
        //if easy
        if(main.getDifficulty() == 0)
        {
            RANDOM_Y_MOVE = 3;
            RemainingLife = 50;
            BombDropRate = 50;//2%
            LifeDropRate = 50;//2%
            AddLife = 10;
        }
        //if normal
        else if(main.getDifficulty() == 1)
        {
            RANDOM_Y_MOVE = 5;
            RemainingLife = 25;
            BombDropRate = 25;//4%
            LifeDropRate = 25;//4%
            AddLife = 10;
        }
        //if hard
        else if(main.getDifficulty() == 2)
        {
            RANDOM_Y_MOVE = 7;
            RemainingLife = 10;
            BombDropRate = 17;//6%
            LifeDropRate = 17;//6%
            AddLife = 10;
        }

        //initialize levelscore
        LEVEL1SCORE = RANDOM_Y_MOVE * 10; //50 -5
        LEVEL2SCORE = (LEVEL1SCORE * 5);//100 -6
        LEVEL3SCORE = (LEVEL2SCORE * 4);//200 -10
        LEVEL4SCORE = (LEVEL3SCORE * 3);//600 -24
        LEVEL5SCORE = (LEVEL4SCORE * 2);//1200 -40
        LEVEL6SCORE = (LEVEL5SCORE * 2);//2400 -68
        LEVEL7SCORE = (LEVEL6SCORE * 2);//4800 -120
        LEVEL8SCORE = (LEVEL7SCORE * 2);//9600 -213
        LEVEL9SCORE = (LEVEL8SCORE * 2);//19200 -384

        timePlayedInSeconds = 0;

        
        try
        {
            //initialize Sprites and Layers and Layer Manager
            ResumeExit = gameDesign.getResumeExit();
            PauseExit = gameDesign.getPauseExit();

            Bomb = gameDesign.getBomb();
            BombWidth = Bomb.getWidth();
            BombHeight = Bomb.getHeight();

            Life = gameDesign.getLife();
            LifeWidth = Life.getWidth();
            LifeHeight = Life.getHeight();

            Fireball =  new Sprite[NUMBER_OF_FIREBALLS];
            Fireball = gameDesign.getFireball();

            FireballExplosion = new Sprite[NUMBER_OF_FIREBALLS];
            FireballExplosion = gameDesign.getFireballExplosion();

            FireballWidth = Fireball[0].getWidth();
            FireballHeight = Fireball[0].getHeight();
            FireballExplosionWidth = FireballExplosion[0].getWidth();
            FireballExplosionHeight = FireballExplosion[0].getHeight();

            //Initialize Fireball, life and Bomb Status
            BombStatus[0] = 0;
            BombStatus[1] = 0;
            BombStatus[2] = 0;

            LifeStatus[0] = 0;
            LifeStatus[1] = 0;
            LifeStatus[2] = 0;

            FireballStatus = new int[NUMBER_OF_FIREBALLS][3];
            for(int i=0; i < NUMBER_OF_FIREBALLS; i++)
            {
                FireballStatus[i][0] = 0;
                FireballStatus[i][1] = 0;
                FireballStatus[i][2] = 0;

                FireballExplosion[i].setRefPixelPosition(FireballExplosionWidth/2, FireballExplosionHeight/2);
                Fireball[i].setRefPixelPosition(FireballWidth/2, FireballHeight/2);
            }

            layerManager = new LayerManager();
            gameDesign.updateLayerManagerForLevel1_2(layerManager);

        }
        catch (IOException ex)
        {
        }


        //Initialize media players
        initializePlayers();
    }


    public void run()
    {
        //start fireball thread
        FireballMovement fireballMovement = new FireballMovement();
        fireballMovement.start();

        //start bomb thread
        RunBomb runBomb = new RunBomb();
        runBomb.start();

        //start life thread
        RunLife runLife = new RunLife();
        runLife.start();

        //initialize 2 fireballs for the start
        initializeFireball(0);
        initializeFireball(1);

        //Start counting time
        CountTime countTime = new CountTime();
        countTime.start();

        //start music
        if(isSoundOn == 0)
        {
            try
            {
                playerMusic.start();
            }
            catch (MediaException ex) {ex.printStackTrace();}
        }
        
        g = getGraphics();
        
        try { Thread.sleep(20); }
        catch (InterruptedException ex){}


        while(running)
        {
            if(!this.isShown())
            {
                this.pause();
            }

            if(!isPaused)
            {
                continueRun();
            }
            
            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException ex){}
        }
    }

    public void continueRun()
    {
        g = getGraphics();
        w = getWidth();
        h = getHeight();

        // move fireballs
        for(int i = 0; i < NUMBER_OF_FIREBALLS; i++)
        {
            //if the fireball is visible
            if(FireballStatus[i][2] == 1)
            {
                //if the X fireball touch the edges of the screen
                if(Fireball[i].getX() >= w-42 || Fireball[i].getX() <= 0)
                {
                    FireballStatus[i][0] = FireballStatus[i][0] * -1;
                }
                Fireball[i].move(FireballStatus[i][0], FireballStatus[i][1]);

                //if the fireball leave the screen
                if(Fireball[i].getY() > 328)
                {
                    if(RemainingLife > 0)
                    {
                        RemainingLife--;
                        initializeFireball(i);
                    }
                    else
                    {
                        isGameOver = true;
//                        running = false;
                    }

                    //Keep the fireballs falling even if the game is over
                    if(isGameOver == true)
                    {
                        initializeFireball(i);
                    }
                }
            }
        }


        //Move the Bomb
        if(BombStatus[2] == 1)
        {
            //Check width edges
            BombX = Bomb.getX();
            if(BombX <= 0 || BombX >= w- BombWidth)
            {
                BombStatus[0] = BombStatus[0] * -1;
            }
            Bomb.move(BombStatus[0], BombStatus[1]);

            if(Bomb.getY() >= h)
            {
                BombStatus[0] = 0;
                BombStatus[1] = 0;
                BombStatus[2] = 0;
                Bomb.setVisible(false);
            }
        }

        //Move the Heart
        if(LifeStatus[2] == 1)
        {
            LifeX = Life.getX();

            //check width edges
            if(LifeX <= 0 || LifeX >= w-LifeWidth)
            {
                LifeStatus[0] = LifeStatus[0] * -1;
            }
            Life.move(LifeStatus[0], LifeStatus[1]);

            if(Life.getY() >= h)
            {
                LifeStatus[0] = 0;
                LifeStatus[1] = 0;
                LifeStatus[2] = 0;
                Life.setVisible(false);
            }
        }

        //Draw the screen
        layerManager.setViewWindow(0, 0 , w, h);
        layerManager.paint(g, 0, 0);

        //Draw Life
        if(RemainingLife > 10)
            g.setColor(0x00FF00);
        else
            g.setColor(0xFF0000);
        g.drawString("Life x " + (RemainingLife), 0, h , Graphics.BASELINE | Graphics.LEFT);

        //Draw Score
        g.drawString((Score)+"", w/2, h , Graphics.BASELINE | Graphics.HCENTER);

        //Draw Level
        g.drawString("Level: " + YSpeed, w , h, Graphics.BASELINE | Graphics.RIGHT);

        if(isGameOver)
        {
            //Initialize GameOver Image
            if(GameOverImage == null)
            {
                try
                {
                    GameOverImage = gameDesign.getGo();
                }
                catch (IOException ex){ex.printStackTrace();}
            }

            //Draw game over image
            g.drawImage(GameOverImage, (w/2) - (GameOverImage.getWidth()/2), (h/2) - (GameOverImage.getHeight()/2), Graphics.TOP | Graphics.LEFT);
 
        }

        this.flushGraphics(0, 0, w, h);
    }

    protected void pointerPressed(int x, int y)
    {
        FireballPressed = false;

        //Process Fireball, Life, bombs
        if(!isPaused && isGameOver == false)
        {
            //check if pointer touched the fireball(s)
            for(int i = 0; i < NUMBER_OF_FIREBALLS; i++)
            {
                //if fireball is visible
                if(FireballStatus[i][2] == 1)
                {
                    FireballX = Fireball[i].getX();
                    FireballY = Fireball[i].getY();

                    if( (x > (FireballX + 5) && x < (FireballX + 37)) && (y < (FireballY + 70) && y > (FireballY + 38)) )
                    {
                        FireballPressed = true;
                        playExplosionSound();
                        Fireball[i].setPosition(-50, -72);
                        FireballStatus[i][2] = 0;
                        Score+= (YSpeed + main.getDifficulty()) * RANDOM_Y_MOVE;
                        Score+= timePlayedInSeconds * main.getDifficulty();
                        new RunFireballExplosion(i,x,y,FireballX, FireballY).start();

                        break;
                    }
                }
            }

            //check if pointer touched the bomb
            if(BombStatus[2] == 1 && FireballPressed == false)
            {
                BombX = Bomb.getX();
                BombY = Bomb.getY();

                if(( x > BombX && x < (BombX + BombWidth) ) && ( y > BombY && y < (BombY + BombHeight)))
                {
                    //vibrate phone
                    vibratePhone();
                    //hide the bomb
                    BombStatus[2] = 0;
                    Bomb.setVisible(false);

                    //destroy all fireballs
                    for (int i = 0; i < NUMBER_OF_FIREBALLS; i++)
                    {
                       if(FireballStatus[i][2] == 1)
                       {
                           pointerPressed((Fireball[i].getX() + 10), (Fireball[i].getY() + 45));
                       }
                    }
                }
            }

            //check if pointer touched the heart
            if(LifeStatus[2] == 1 && FireballPressed == false)
            {
                LifeX = Life.getX();
                LifeY = Life.getY();

                if((x > LifeX && x < (LifeX + LifeWidth)) && ( y > LifeY && y < (LifeY + LifeHeight) ) )
                {
                    RemainingLife += AddLife ;
                    LifeStatus[2] = 0;
                    Life.setVisible(false);
                }
            }
        }
        
        //**** Exit - Pause Game
        if(this.isShown() && FireballPressed == false && isGameOver == false)
        {
            //**** Exit - Pause Game
            if(x > 600 && y < 40)
            {
                if(isPaused)
                {
                    exit();
                }
                else
                {
                    pause();
                }
            }
            if (x > 560 && x < 600 && y < 40)
            {
                if(isPaused)
                {
                    resume();
                }
                else
                {
                    pause();
                }
            }
        }


        //if Game is Over
        if(isGameOver == true)
        {
            closePlayers();
            running = false;
            main.setScore(Score);
            main.setChoice(7);
        }
    }

    public void initializeFireball(int index)
    {
        //Create a new fireball
        FireballStatus[index][0] = random.nextInt(RANDOM_X_MOVE) + XSpeed ;
        FireballStatus[index][1] = random.nextInt(RANDOM_Y_MOVE)+YSpeed;
        FireballStatus[index][2] = 1;
        Fireball[index].setPosition(random.nextInt(640 - FireballWidth), -FireballHeight);


        //Random Bomb Creation
        if(random.nextInt(BombDropRate) == 0 && BombStatus[2] == 0)
        {
            Bomb.setPosition(random.nextInt(640-BombWidth), -BombHeight);
            BombStatus[0] = random.nextInt(RANDOM_X_MOVE) + XSpeed;
            BombStatus[1] = random.nextInt(4)+1;
            Bomb.setFrameSequence(gameDesign.Bombseq001);
            BombStatus[2] = 1;
            Bomb.setVisible(true);
        }

        //Random Life Creation
        if(random.nextInt(LifeDropRate) == 0 && LifeStatus[2] == 0)
        {
            Life.setPosition(random.nextInt(640-LifeWidth), -LifeHeight);
            LifeStatus[0] = random.nextInt(RANDOM_X_MOVE) + XSpeed;
            LifeStatus[1] = random.nextInt(4)+1;
            Life.setFrameSequence(gameDesign.Lifeseq001);
            LifeStatus[2] = 1;
            Life.setVisible(true);
        }
    }


    //Play Explosion sound
    public void playExplosionSound()
    {
        if(isSoundOn == 0 || isSoundOn == 1)
        {
            try
            {
                playerExplosion.start();
            }
            catch (MediaException ex){}
        }
    }

    //Vibrate the phone
    public void vibratePhone()
    {
        if(isVibrateOn == true)
        {
            Display.getDisplay(main).vibrate(300);
        }
    }


    //Pause game
    public void pause()
    {
        //pause music player
        if(isSoundOn == 0)
        {
            try
            {
                playerMusic.stop();
            }
            catch (MediaException ex){ex.printStackTrace();}
        }
        //show pause screen
        PauseExit.setVisible(false);
        ResumeExit.setVisible(true);
        layerManager.paint(g, 0, 0);
        g.setColor(0xFF0000);
        g.drawString("Paused", w/2, h/2, Graphics.BASELINE | Graphics.HCENTER);
        this.flushGraphics(0,0,w,h);
        isPaused = true;
    }

    //Resume game
    public void resume()
    {
        //resume music player
        if(isSoundOn == 0)
        {
            try
            {
                playerMusic.start();
            }
            catch (MediaException ex){ex.printStackTrace();}
        }
        PauseExit.setVisible(true);
        ResumeExit.setVisible(false);
        isPaused = false;
    }

    //exit canvas
    public void exit()
    {
        closePlayers();
        running = false;
        main.setChoice(0);
    }

    //initialie the media players
    public void initializePlayers()
    {
        //Initializing media player for the explosion
        if(isSoundOn == 0 || isSoundOn == 1)
        {
            try
            {
                inputStream = getClass().getResourceAsStream("/explosion.mp3");
                playerExplosion = Manager.createPlayer(inputStream, "audio/mpeg");
                playerExplosion.realize();
                playerExplosion.prefetch();
            }
            catch (MediaException ex) {}
            catch (IOException ex){}
        }

        //Initializing media player for the music
        if(isSoundOn == 0)
        {
            try
            {
                inputStream = getClass().getResourceAsStream("/m.mid");
                playerMusic = Manager.createPlayer(inputStream, "audio/midi");
                playerMusic.realize();
                playerMusic.prefetch();
                playerMusic.setLoopCount(-1);
            }
            catch (MediaException ex) {}
            catch (IOException ex){}
        }
    }

    //close the media players
    public void closePlayers()
    {

        try
        {
            if(isSoundOn == 0 || isSoundOn == 1)
            {
                inputStream.close();
                playerExplosion.stop();
                playerExplosion.close();
            }
            if(isSoundOn == 0)
            {
                playerMusic.stop();
                playerMusic.close();
            }
        }
        catch (MediaException ex){ex.printStackTrace();}
        catch (IOException ex){ex.printStackTrace();}
    }









    //Run the fireball(s)
    private class FireballMovement extends Thread
    {
        public FireballMovement()
        {
        }

        public void run()
        {
            while(running)
            {
                if(!isPaused)
                {
                    for(int i = 0; i < NUMBER_OF_FIREBALLS; i++)
                    {
                        //if fireball is visible, make it move
                        if(FireballStatus[i][2] == 1)
                        {
                            Fireball[i].nextFrame();
                        }
                    }
                }

                try
                {
                    Thread.sleep(gameDesign.Fireballseq001Delay);
                }
                catch (InterruptedException ex){}
            }
        }
    }

    //Run the fireball(s) explosion
    private class RunFireballExplosion extends Thread
    {
        private int FireballIndex;
        private int x;
        private int y;
        private int length;
        private int counter;
        private int FireballX;
        private int FireballY;

        public RunFireballExplosion(int FireballIndex, int x, int y, int FireballX, int FireballY)
        {
            this.FireballIndex = FireballIndex;
            this.x = x;
            this.y = y;
            this.counter = 1;
            this.FireballX = FireballX;
            this.FireballY = FireballY;

            FireballExplosion[FireballIndex].setFrameSequence(gameDesign.FireballExplosionseq001);
            length = gameDesign.FireballExplosionseq001.length;
        }

        public void run()
        {
            FireballExplosion[FireballIndex].setPosition(FireballX+5, FireballY+38);
            FireballExplosion[FireballIndex].setVisible(true);

            //Run Explosion Effect
            while(counter < length && running == true)
            {
                if(!isPaused)
                {
                    FireballExplosion[FireballIndex].nextFrame();
                    counter++;
                }

                try
                {
                    Thread.sleep(gameDesign.FireballExplosionseq001Delay);
                }
                catch (InterruptedException ex){}
            }
            FireballExplosion[FireballIndex].setVisible(false);


            //Check Score to add a new Fireball
            if(Score > LEVEL1SCORE && Score <= LEVEL2SCORE && YSpeed < 2)
            {
                YSpeed = 2;
                initializeFireball(2);
            }
            else if(Score > LEVEL2SCORE && Score <= LEVEL3SCORE && YSpeed < 3)
            {
                YSpeed = 3;
                initializeFireball(3);
            }
            else if(Score > LEVEL3SCORE && Score <= LEVEL4SCORE && YSpeed < 4)
            {
                YSpeed = 4;
                initializeFireball(4);
            }
            else if(Score > LEVEL4SCORE && Score <= LEVEL5SCORE && YSpeed < 5)
            {
                YSpeed = 5;
                initializeFireball(5);
            }
            else if(Score > LEVEL5SCORE && Score <= LEVEL6SCORE && YSpeed < 6)
            {
                YSpeed = 6;
                initializeFireball(6);
            }
            else if(Score > LEVEL6SCORE && Score <= LEVEL7SCORE && YSpeed < 7)
            {
                YSpeed = 7;
                initializeFireball(7);
            }
            else if(Score > LEVEL7SCORE && Score <= LEVEL8SCORE && YSpeed < 8)
            {
                YSpeed = 8;
                initializeFireball(8);
            }
            else if(Score > LEVEL8SCORE && Score <= LEVEL9SCORE && YSpeed < 9)
            {
                YSpeed = 9;
                initializeFireball(9);
            }
            else if(Score > LEVEL9SCORE  && YSpeed < 10 )
            {
                YSpeed = 10;
                initializeFireball(9);
            }

            //Re-Start the exploded fireball
            initializeFireball(FireballIndex);


            //Remove Variable's pointers to kill the thread and set the instance for gc
            this.FireballIndex = 0;
            this.x = 0;
            this.y = 0;
            this.counter = 0;
            this.length = 0;
            FireballX = 0;
            FireballY = 0;
        }
    }

    //Run the bomb that will destroy everything
    private class RunBomb extends Thread
    {
        public RunBomb()
        {
        }

        public void run()
        {
            while(running)
            {
                if(!isPaused)
                {
                    if(BombStatus[2] == 1)
                    {
                        Bomb.nextFrame();
                    }
                }

                try
                {
                    Thread.sleep(gameDesign.Bombseq001Delay);
                }
                catch (InterruptedException ex){}
            }
        }
    }

    //Run the heart on the screen
    private class RunLife extends Thread
    {
        public RunLife()
        {
        }

        public void run()
        {
            while(running)
            {
                if(!isPaused)
                {
                    if(LifeStatus[2] == 1)
                    {
                        Life.nextFrame();
                    }
                }

                try
                {
                    Thread.sleep(gameDesign.Lifeseq001Delay);
                }
                catch (InterruptedException ex){}
            }
        }
    }

    private class CountTime extends Thread
    {

        public CountTime()
        {
        }
        public void run()
        {
            while(running)
            {
                if(!isPaused)
                {
                    timePlayedInSeconds++;
                }

                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex){}
            }
        }

    }
}
