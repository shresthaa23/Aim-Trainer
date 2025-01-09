import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

   public class MouseSpeed implements MouseInterface
   {
      private int numButtons;
      private int looper;
      private long ends;
      
      /* CONSTRUCTOR */
      public MouseSpeed(int buttons) 
      {
         if (buttons <= 100)
            numButtons = buttons;   
         else
            numButtons = 5;
         looper = 0;
      }
      
       /* GETTERS */
      public int getNumButtons()
      { return numButtons; }      
   
      public int getLooper()
      { return looper; }
      
      public long getEndTime()
      { return ends; }
      
     
      /* METHODS */
      /* CALCULATES A SCORE IN SOME MATH WAY AND RETURNS IT */
      public int getScore(int clicks, long startTime, long endTime)
      {
         return ((clicks*100) - (((int) endTime - (int) startTime)/1000));
      }
      /* CHECKS IF GAME HAS ENDED - MEANING NUMBER OF BUTTONS CLICKED IS SUFFICIENT */
      public boolean checkGame(long ends, int clicked)
      {
         if (clicked == numButtons)
            return true;
        return false;
      }
     /* CHECKS IF GAME HAS ENDED - MEANING TIME HAS RUN OUT */                                 
      public void check(int checker, JButton[][] buttons)
      {          
         if (checker <= 16)
         {
            for (int r = 0; r < 10; r++)
               for (int c = 0; c < 10; c++)
                  buttons[r][c].setEnabled(false);
            looper = 0;
            while (looper < 17)
            {
               buttons[(int) (Math.random()*10)]  [(int) (Math.random()*10)].setEnabled (true);        
               looper++;
            }
            checker = 17;
         }
      }
}