
package EndOfDays;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.rms.*;

public class SaveLoadData
{
    private final short HIGH_SCORE_NUMBER = 10;


    public SaveLoadData()
    {

    }

    //Create Record stores if they don't exist with the application
    public void createRecordStores()
    {
        RecordStore rsHighScores = null;
        RecordStore rsOptions = null;
        try
        {
            rsHighScores = RecordStore.openRecordStore("HighScores", true);
            rsOptions = RecordStore.openRecordStore("Options", true);

            if(rsHighScores.getNumRecords() != HIGH_SCORE_NUMBER || rsHighScores.getNumRecords() == 0 || rsOptions.getNumRecords() == 0 || rsOptions.getNumRecords() != 3)
            {
                for(int i = 0; i < HIGH_SCORE_NUMBER; i++)
                {
                    String s = 0 +"";
                    rsHighScores.addRecord(s.getBytes(), 0, s.getBytes().length);
                }
                
                byte []sound = "0".getBytes(); // 0 = on | 1 = off
                byte []vibrate = "0".getBytes();// 0 = on | 1 = off
                byte []difficulty = "1".getBytes(); // 0 = easy | 1 = normal | 2 = hard
                
                rsOptions.addRecord(sound, 0, sound.length);
                rsOptions.addRecord(vibrate, 0, vibrate.length);
                rsOptions.addRecord(difficulty, 0, difficulty.length);
            }
        }
        catch (RecordStoreException ex){}
        finally
        {
            try
            {
                if (rsHighScores != null)
                {
                    rsHighScores.closeRecordStore();
                }
                if (rsOptions != null)
                {
                    rsOptions.closeRecordStore();
                }
            }
            catch (RecordStoreException ex){}
        }
    }

    public void setScore(String []names)
    {
        RecordStore rs = null;
        try
        {
            rs = RecordStore.openRecordStore("HighScores", false);
            byte []b = null;
            for(int i=1;  i <= HIGH_SCORE_NUMBER; i++)
            {
                b = names[i-1].getBytes();
                rs.setRecord(i, b, 0, b.length);
            }
        }
        catch (RecordStoreException ex){}
        finally
        {

            try
            {
                if(rs != null)
                    rs.closeRecordStore();
            }
            catch (RecordStoreException ex){}
        }
    }
    
    public String []getHighScores()
    {
        String []HighScores = new String[HIGH_SCORE_NUMBER];
        RecordStore rs = null;

        try
        {
            rs = RecordStore.openRecordStore("HighScores", false);

            for(int i = 1; i <= HIGH_SCORE_NUMBER; i++)
            {
                HighScores[i-1] = new String(rs.getRecord(i));
            }
        }
        catch (RecordStoreException ex)
        {
        }
        finally
        {
            try
            {
                if(rs != null)
                    rs.closeRecordStore();
            }
            catch (RecordStoreException ex) {}
        }
        
        return HighScores;
    }

    public int []getIntegerHighScores()
    {
        int []highScores = new int[HIGH_SCORE_NUMBER/2];


        RecordStore rs =null;
        int i = 0;
        String s;

        try
        {
            rs = RecordStore.openRecordStore("HighScores", false);

            for (int j = 2; j <= HIGH_SCORE_NUMBER; j+=2)
            {
                s = new String(rs.getRecord(j));

                if(s == null || s.equals(""))
                {
                    highScores[i++] = 0;
                }
                else
                {
                    highScores[i++] = Integer.parseInt(s);
                }
           }
        }
        catch (RecordStoreException ex){}
        finally
        {
            try
            {
                rs.closeRecordStore();
            }
            catch (RecordStoreException ex){}
            
        }
        return highScores;        
    }
    
    public int[] getOptions()
    {
        RecordStore rs = null;
        int Sound = 0;
        int Vibrate = 0;
        int Difficulty = 0;
        int []Options = new int[3];

        try
        {
            rs = RecordStore.openRecordStore("Options", false);

            Sound = Integer.parseInt(new String(rs.getRecord(1)));
            Vibrate = Integer.parseInt(new String(rs.getRecord(2)));
            Difficulty = Integer.parseInt(new String(rs.getRecord(3)));
        }
        catch (RecordStoreException ex){}
        finally
        {
            try
            {
                rs.closeRecordStore();
            }
            catch (RecordStoreException ex){}
        }

        Options[0] = Sound;
        Options[1] = Vibrate;
        Options[2] = Difficulty;

        return Options;
    }

    public void setOptions(int []Options)
    {
        RecordStore rs = null;
        try
        {
            rs = RecordStore.openRecordStore("Options", false);

            byte b[];

            b= String.valueOf(Options[0]).getBytes();
            rs.setRecord(1, b , 0, b.length);

            b= String.valueOf(Options[1]).getBytes();
            rs.setRecord(2, b , 0, b.length);

            b= String.valueOf(Options[2]).getBytes();
            rs.setRecord(3, b , 0, b.length);
        }
        catch (RecordStoreException ex){}
        finally
        {
            try
            {
                rs.closeRecordStore();
            }
            catch (RecordStoreException ex){}
        }

    }

/*
 *
    public boolean getMusicStatus()
    {

    }

    public void setMusicStatus(boolean b)
    {

    }

    public boolean getVibrateStatus()
    {

    }

    public void setVibrateStatus()
    {

    }

    public int getDifficulty()
    {

    }

    public void setDifficulty()
    {

    }

* */

}
