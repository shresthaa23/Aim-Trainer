import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
   /* INTERFACE */
   public interface MouseInterface
   { 
   
      public int getScore(int clicks, long startTime, long endTime);
      
      public void check(int checker, JButton[][] buttons);
      
      public boolean checkGame (long ends, int clicked);
           
   }