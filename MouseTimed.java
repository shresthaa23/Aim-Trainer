import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class MouseTimed implements MouseInterface 
{
   private int time;
   private int looping;
   
   /* CONSTRUCTOR */
   public MouseTimed(int timeGiven) 
   {
      if (timeGiven <= 60)
         time = timeGiven;
      else
         time = 10;
   }
   
   /* GETTERS */
   public int getTime()
   {  return time; }
   
   public int getLooping()
   { return looping; }
   
      
   /* METHODS */
   /* CALCULATES A SCORE IN SOME MATH WAY AND RETURNS IT */
   public int getScore(int clicks, long startTime, long endTime)
   {
      return ((clicks*100) / (((int) endTime - (int) startTime)/1000));
   }
   /* CHECKS IF GAME HAS ENDED - MEANING TIME HAS RUN OUT */
   public boolean checkGame(long ends, int clicked)
   {
      if (ends <= System.currentTimeMillis())
      {
         return true;
      }
      return false;
   }
   /* CHECKS TO SEE IF A BUTTON HAS BEEN PRESSED - IF SO THEN TURNS OFF AND TURNS ON NEW SET */ 
   public void check(int checker, JButton[][] buttons)
   {
      if (checker <= 16)
      {
         for (int r = 0; r < 10; r++)
            for (int c = 0; c < 10; c++)
               buttons[r][c].setEnabled(false);
         looping = 0;
         while (looping < 17)
         {
            buttons[(int) (Math.random()*10)]  [(int) (Math.random()*10)].setEnabled (true);        
            looping++;
         }
         checker = 17;
      }
   }           
}

