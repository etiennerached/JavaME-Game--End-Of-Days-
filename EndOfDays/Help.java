package EndOfDays;

import javax.microedition.lcdui.*;

public class Help extends Canvas
{
    private int w;
    private int h;
    private Main main;
    private Image back;


    public Help(Main main)
    {
        this.main = main;
        this.setFullScreenMode(true);
    }

    public void paint(Graphics g)
    {
        w = getWidth();
        h = getHeight();
        try
        {
            String sStart = "-Start:";
            String sStartContent = "Start a new game, use the stylus pen to destroy the meteors or collect extra lives.";
            String sOptions = "-Options:";
            String sOptionsContent = "Use the options to turn on or off the sound and vibration, and to set the difficulty level.";
            String sRank = "-Rank:";
            String sRankContent = "Check your highest Scores";
            String sAbout = "-About:";
            String sAboutContent = "Information about this game.";

            back = main.getBackgroundImage();

//            Image logoImg = Image.createImage("/Logo.png");
//            Image helpImg = Image.createImage("/HelpFocus.png");

//            int helpImgHeight = logoImg.getHeight() + 10 + helpImg.getHeight() + 10;

            g.setColor(0xFF7800);
            g.fillRect(0, 0, w, h);

//            g.drawImage(logoImg, w/2, 0, Graphics.TOP | Graphics.HCENTER);
//            g.drawImage(helpImg, w/2, logoImg.getHeight() + 10, Graphics.TOP | Graphics.HCENTER);
            g.drawImage(back, 0, 0, Graphics.TOP | Graphics.LEFT);
            Font fontBold = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
            Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);


            g.setColor(0x0000FF);
            int nextInt = 10;
            int x = 10;
            nextInt = this.write(g, sStart, x, nextInt, w - 20, fontBold, Graphics.LEFT);
            nextInt = this.write(g, sStartContent, x, nextInt, w - 20, font, Graphics.LEFT);

            nextInt = this.write(g, sOptions, x, nextInt, w - 20, fontBold, Graphics.LEFT);
            nextInt = this.write(g, sOptionsContent, x, nextInt, w - 20, font, Graphics.LEFT);

            nextInt = this.write(g, sRank, x, nextInt, w - 20, fontBold, Graphics.LEFT);
            nextInt = this.write(g, sRankContent, x, nextInt, w - 20, font, Graphics.LEFT);

            nextInt = this.write(g, sAbout, x, nextInt, w - 20, fontBold, Graphics.LEFT);
            nextInt = this.write(g, sAboutContent, x, nextInt, w - 20, font, Graphics.LEFT);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    protected  void pointerPressed(int x, int y)
    {
        if(x > 0 && x < back.getWidth() && y > h- back.getHeight() && y < h)
        {
            main.setChoice(0);
        }
    }

    

    // Wrapping TEXT
    private Font m_font;
    private String m_txt;
    private int m_length;
    private int m_width;
    private int m_position;
        private int m_start;
    public int write(Graphics g, String txt, int x, int y,
                             int width, Font font,
                             int alignment )
    {
        m_font = font;
        m_txt = txt;
        m_length = txt.length();
        m_width = width;
        //reseting
        m_position =0;
        m_start = 0;

        int fontHight = m_font.getHeight() + 1;
        String s;
        g.setFont(m_font);
        while(hasMoreLines())
        {
            s = nextLine().trim();
            g.drawString(s, x, y, Graphics.TOP|alignment );
            y += fontHight;
        }
        return y;
    }

    //below is the implementation  for the  "helper" functions

    private boolean hasMoreLines()
    {
        return (m_position<(m_length-1));
    }

    private String nextLine(){
         int maxLength = m_txt.length();
         int next = next();
         if(m_start>=maxLength || next>maxLength)
              return null;
         String s =m_txt.substring(m_start, next);
         m_start = next;
         if((m_txt.length()-1>m_start )&& ((m_txt.charAt(m_start)=='\n') ||
            (m_txt.charAt(m_start)==' '))){
              m_position++;
              m_start++;
         }
         return s;
    }

    private int next()
    {
         int i=getNextWord(m_position);
         int lastBreak = -1;
         String line;
         line= m_txt.substring(m_position, i);
         int lineWidth = m_font.stringWidth(line);
         while (i<m_length && lineWidth<= m_width){
             if(m_txt.charAt(i)==' ' )
                lastBreak = i;
             else if(m_txt.charAt(i)== '\n'){
                lastBreak =i;
                break;
             }
             if(++i<m_length){
                i= getNextWord(i);
                line = m_txt.substring(m_position, i);
                lineWidth = m_font.stringWidth(line);
             }
         }
         if(i==m_length && lineWidth<= m_width)
            m_position = i;
         else if(lastBreak == m_position)
            m_position++;
         else if(lastBreak < m_position)
            m_position =i;
         else
            m_position = lastBreak;
         return m_position;
    }

    private int getNextWord(int startIndex)
    {
        int space = m_txt.indexOf(' ', startIndex);
        int newLine = m_txt.indexOf('\n', startIndex);
        if(space ==-1)
           space = m_length;
        if(newLine ==-1)
           newLine = m_length;
        if(space<newLine)
           return space;
        else
          return newLine;
    }
}