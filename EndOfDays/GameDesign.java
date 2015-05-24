
package EndOfDays;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.io.IOException;
import java.util.Random;


public class GameDesign
{

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Image Fireball333;
    private Image GameBg;
    private TiledLayer GameBackGround;
    private Image skullcrossbones;
    private Image Heart;
    private Image explosion;
    private Image peq;
    private TiledLayer ResumeExit;
    private TiledLayer PauseExit;
    private Image go;
    private TiledLayer GameOver;
    //</editor-fold>//GEN-END:|fields|0|
    private final int NUMBER_OF_FIREBALLS = 10;

    private Sprite []Fireball;
    public int Fireballseq001Delay = 150;
    public int[] Fireballseq001 = {1, 0, 2, 0};

    private Sprite []FireballExplosion;
    public int FireballExplosionseq001Delay = 20;
    public int[] FireballExplosionseq001 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    private Sprite Bomb;
    public int Bombseq001Delay = 15;
    public int []Bombseq001 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

    private Sprite Life;
    public int Lifeseq001Delay = 100;
    public int[] Lifeseq001 = {0, 1, 2, 3};

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    public GameDesign()
    {
        Fireball = new Sprite[NUMBER_OF_FIREBALLS];
        FireballExplosion = new Sprite[NUMBER_OF_FIREBALLS];

        try
        {
            Bomb = new Sprite(getSkullcrossbones(), 24, 32);
            Bomb.setFrameSequence(Bombseq001);

            Life = new Sprite(getHeart(), 28, 21);
            Life.setFrameSequence(Lifeseq001);
        }
        catch (IOException ex){}


        for (int i = 0; i < NUMBER_OF_FIREBALLS; i++)
        {
            try
            {
                Fireball[i] = new Sprite(getFireball333(), 42, 70);
                Fireball[i].setFrameSequence(this.Fireballseq001);

                FireballExplosion[i] = new Sprite(getExplosion(), 30, 30);
                FireballExplosion[i].setFrameSequence(this.FireballExplosionseq001);
            }
            catch (IOException ex) {}
        }
    }
    public Image getFireball333() throws java.io.IOException {//GEN-BEGIN:|1-getter|0|1-preInit
        if (Fireball333 == null) {//GEN-END:|1-getter|0|1-preInit
            // write pre-init user code here
            Fireball333 = Image.createImage("/Fireball333.png");//GEN-BEGIN:|1-getter|1|1-postInit
        }//GEN-END:|1-getter|1|1-postInit
        // write post-init user code here
        return this.Fireball333;//GEN-BEGIN:|1-getter|2|
    }
//GEN-END:|1-getter|2|


    public Image getGameBg() throws java.io.IOException {//GEN-BEGIN:|4-getter|0|4-preInit
        if (GameBg == null) {//GEN-END:|4-getter|0|4-preInit
            // write pre-init user code here
            Random random = new Random();
            int choice = random.nextInt(5)+1;
            if(choice == 5)
                GameBg = Image.createImage("/GameBg5.png");
            else if(choice == 4)
                GameBg = Image.createImage("/GameBg4.png");
            else if(choice == 3)
                GameBg = Image.createImage("/GameBg3.png");
            else if(choice == 2)
                GameBg = Image.createImage("/GameBg2.png");
            else if (choice == 1)
            GameBg = Image.createImage("/GameBg.png");//GEN-BEGIN:|4-getter|1|4-postInit
        }//GEN-END:|4-getter|1|4-postInit
        // write post-init user code here
        return this.GameBg;//GEN-BEGIN:|4-getter|2|
    }
//GEN-END:|4-getter|2|


    public TiledLayer getGameBackGround() throws java.io.IOException {//GEN-BEGIN:|10-getter|0|10-preInit
        if (GameBackGround == null) {//GEN-END:|10-getter|0|10-preInit
            // write pre-init user code here
            GameBackGround = new TiledLayer(4, 3, getGameBg(), 160, 120);//GEN-BEGIN:|10-getter|1|10-midInit
            int[][] tiles = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }
            };//GEN-END:|10-getter|1|10-midInit
            // write mid-init user code here
            for (int row = 0; row < 3; row++) {//GEN-BEGIN:|10-getter|2|10-postInit
                for (int col = 0; col < 4; col++) {
                    GameBackGround.setCell(col, row, tiles[row][col]);
                }
            }
        }//GEN-END:|10-getter|2|10-postInit
        // write post-init user code here
        return GameBackGround;//GEN-BEGIN:|10-getter|3|
    }
//GEN-END:|10-getter|3|

    public void updateLayerManagerForLevel1(LayerManager lm) throws java.io.IOException {//GEN-LINE:|11-updateLayerManager|0|11-preUpdate
        // write pre-update user code here
        getResumeExit().setPosition(560, 0);//GEN-BEGIN:|11-updateLayerManager|1|11-postUpdate
        getResumeExit().setVisible(true);
        lm.append(getResumeExit());
        getPauseExit().setPosition(560, 0);
        getPauseExit().setVisible(true);
        lm.append(getPauseExit());
        getGameBackGround().setPosition(0, 0);
        getGameBackGround().setVisible(true);
        lm.append(getGameBackGround());//GEN-END:|11-updateLayerManager|1|11-postUpdate
        // write post-update user code here
    }//GEN-BEGIN:|11-updateLayerManager|2|
//GEN-END:|11-updateLayerManager|2|

    public Image getExplosion() throws java.io.IOException {//GEN-BEGIN:|45-getter|0|45-preInit
        if (explosion == null) {//GEN-END:|45-getter|0|45-preInit
            // write pre-init user code here
            explosion = Image.createImage("/explosion.png");//GEN-BEGIN:|45-getter|1|45-postInit
        }//GEN-END:|45-getter|1|45-postInit
        // write post-init user code here
        return this.explosion;//GEN-BEGIN:|45-getter|2|
    }
//GEN-END:|45-getter|2|

    public Image getPeq() throws java.io.IOException {//GEN-BEGIN:|55-getter|0|55-preInit
        if (peq == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            peq = Image.createImage("/peq.png");//GEN-BEGIN:|55-getter|1|55-postInit
        }//GEN-END:|55-getter|1|55-postInit
        // write post-init user code here
        return this.peq;//GEN-BEGIN:|55-getter|2|
    }
//GEN-END:|55-getter|2|

    public TiledLayer getPauseExit() throws java.io.IOException {//GEN-BEGIN:|56-getter|0|56-preInit
        if (PauseExit == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            PauseExit = new TiledLayer(2, 1, getPeq(), 40, 40);//GEN-BEGIN:|56-getter|1|56-midInit
            int[][] tiles = {
                { 1, 2 }
            };//GEN-END:|56-getter|1|56-midInit
            // write mid-init user code here
            for (int row = 0; row < 1; row++) {//GEN-BEGIN:|56-getter|2|56-postInit
                for (int col = 0; col < 2; col++) {
                    PauseExit.setCell(col, row, tiles[row][col]);
                }
            }
        }//GEN-END:|56-getter|2|56-postInit
        // write post-init user code here
        return PauseExit;//GEN-BEGIN:|56-getter|3|
    }
//GEN-END:|56-getter|3|

    public TiledLayer getResumeExit() throws java.io.IOException {//GEN-BEGIN:|57-getter|0|57-preInit
        if (ResumeExit == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
            ResumeExit = new TiledLayer(2, 1, getPeq(), 40, 40);//GEN-BEGIN:|57-getter|1|57-midInit
            int[][] tiles = {
                { 3, 2 }
            };//GEN-END:|57-getter|1|57-midInit
            // write mid-init user code here
            for (int row = 0; row < 1; row++) {//GEN-BEGIN:|57-getter|2|57-postInit
                for (int col = 0; col < 2; col++) {
                    ResumeExit.setCell(col, row, tiles[row][col]);
                }
            }
        }//GEN-END:|57-getter|2|57-postInit
        // write post-init user code here
        return ResumeExit;//GEN-BEGIN:|57-getter|3|
    }
//GEN-END:|57-getter|3|

    public Image getSkullcrossbones() throws java.io.IOException {//GEN-BEGIN:|74-getter|0|74-preInit
        if (skullcrossbones == null) {//GEN-END:|74-getter|0|74-preInit
            // write pre-init user code here
            skullcrossbones = Image.createImage("/skullcrossbones.png");//GEN-BEGIN:|74-getter|1|74-postInit
        }//GEN-END:|74-getter|1|74-postInit
        // write post-init user code here
        return this.skullcrossbones;//GEN-BEGIN:|74-getter|2|
    }
//GEN-END:|74-getter|2|

    public Image getHeart() throws java.io.IOException {//GEN-BEGIN:|75-getter|0|75-preInit
        if (Heart == null) {//GEN-END:|75-getter|0|75-preInit
            // write pre-init user code here
            Heart = Image.createImage("/Heart.png");//GEN-BEGIN:|75-getter|1|75-postInit
        }//GEN-END:|75-getter|1|75-postInit
        // write post-init user code here
        return this.Heart;//GEN-BEGIN:|75-getter|2|
    }
//GEN-END:|75-getter|2|

    public Image getGo() throws java.io.IOException {//GEN-BEGIN:|76-getter|0|76-preInit
        if (go == null) {//GEN-END:|76-getter|0|76-preInit
            // write pre-init user code here
            go = Image.createImage("/go.png");//GEN-BEGIN:|76-getter|1|76-postInit
        }//GEN-END:|76-getter|1|76-postInit
        // write post-init user code here
        return this.go;//GEN-BEGIN:|76-getter|2|
    }
//GEN-END:|76-getter|2|

    public TiledLayer getGameOver() throws java.io.IOException {//GEN-BEGIN:|77-getter|0|77-preInit
        if (GameOver == null) {//GEN-END:|77-getter|0|77-preInit
            // write pre-init user code here
            GameOver = new TiledLayer(1, 1, getGo(), 250, 85);//GEN-BEGIN:|77-getter|1|77-midInit
            int[][] tiles = {
                { 1 }
            };//GEN-END:|77-getter|1|77-midInit
            // write mid-init user code here
            for (int row = 0; row < 1; row++) {//GEN-BEGIN:|77-getter|2|77-postInit
                for (int col = 0; col < 1; col++) {
                    GameOver.setCell(col, row, tiles[row][col]);
                }
            }
        }//GEN-END:|77-getter|2|77-postInit
        // write post-init user code here
        return GameOver;//GEN-BEGIN:|77-getter|3|
    }
//GEN-END:|77-getter|3|




    public void updateLayerManagerForLevel1_2(LayerManager lm) throws java.io.IOException
    {
        Bomb.setPosition(-40, -70);
        Bomb.setVisible(false);
        lm.append(Bomb);

        Life.setPosition(-32,-32);
        Bomb.setVisible(false);
        lm.append(Life);

        for (int i = 0; i < NUMBER_OF_FIREBALLS; i++)
        {
            FireballExplosion[i].setPosition(-40, -70);
            FireballExplosion[i].setVisible(false);
            lm.append(FireballExplosion[i]);

            Fireball[i].setPosition(-40, -70);
            Fireball[i].setVisible(true);
            lm.append(Fireball[i]);
        }

        getResumeExit().setPosition(560, 0);
        getResumeExit().setVisible(false);
        lm.append(getResumeExit());

        getPauseExit().setPosition(560, 0);
        getPauseExit().setVisible(true);
        lm.append(getPauseExit());

        getGameBackGround().setPosition(0, 0);
        getGameBackGround().setVisible(true);
        lm.append(getGameBackGround());
    }

    public Sprite  []getFireball()
    {
        return Fireball;
    }

    public Sprite []getFireballExplosion()
    {
        return FireballExplosion;
    }

    public Sprite getBomb()
    {
        return Bomb;
    }

    public Sprite getLife()
    {
        return Life;
    }
}
