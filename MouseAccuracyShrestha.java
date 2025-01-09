/* ALEX SHRESTHA - PORTFOLIO 3 */
import javax.swing.*;
import java.awt.*; 
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/* FINAL */
   public class MouseAccuracyShrestha
   {           
      static JFrame frame; 
      static JPanel panel, gridPanel;
      static JButton[][] buttons = new JButton[50][50];
      static JButton ready, highScore, again, clickCount;    
      static MouseTimed mt;
      static MouseSpeed ms;
      static int clicked, checker, looping, choice;
      static long starts, ends;
   
      public static void main(String[] args)
      {
         System.out.print("Mouse Accuracy Game:");
         start();
      }      
      
      /* DETERMINING CHOICE OF GAME AND OPTIONS */ 
      public static void start()
      {
         Scanner sc = new Scanner (System.in);
         try {
            System.out.print(" Timed (1) or Speed (2) ? ");            
            choice = sc.nextInt();   
            if (choice == 1)  {
               System.out.print("How many seconds up to one minute? ");
               mt = new MouseTimed(sc.nextInt()); }
            else if (choice == 2) {
               System.out.print("How many buttons do you want to hit under a 100? ");
               ms = new MouseSpeed(sc.nextInt()); }
            else if (choice != 1 || choice != 2) 
                  throw new ArithmeticException("NO - YOU SAID A BAD NUMBER - LEAVE");
         }
         catch (Exception e) {
            System.out.println("Thats the wrong number");
            ms = new MouseSpeed(1);
            choice = 2; 
         }
        labelling();
      }           
      /* ACTION LISTENERS FOR THE BUTTONS */
      public static void buttonActions()
      {
         for (int r = 0; r < 10; r++)
            for (int c = 0; c < 10; c++)
               buttons[r][c].addActionListener (e -> 
               { 
                  clicked++;
                  checker--;
                  changeCounter();
                  if (choice == 2)
                  {
                     if (ms.checkGame(ends, clicked))
                     {
                        ends = System.currentTimeMillis();
                        scoring();
                     }
                     else
                        ms.check(checker, buttons);
                  }
                  else
                  {
                     if (mt.checkGame(ends, clicked))
                        scoring();
                     else
                        mt.check(checker, buttons);  
                  }
               } );
      }
      /* THE PLAY OF GAME FOR THE SPEED VERSION */
      public static void playSpeed()
      {
         starts = System.currentTimeMillis();
         while (looping < 17)
         {
            buttons[(int) (Math.random()*10)]  [(int) (Math.random()*10)].setEnabled (true);        
            looping++;
         }
      }
      /* THE PLAY OF GAME FOR THE TIMED VERSION */
      public static void playTime()
      {
         starts = System.currentTimeMillis();
         ends = starts + mt.getTime() * 1000;
         while (looping < 17)
         {
            buttons[(int) (Math.random()*10)]  [(int) (Math.random()*10)].setEnabled (true);        
            looping++;
         }
      }
      /* GETS THE SCORE AND SOMEWHAT SORTS IT */ 
      public static void scoring()
      {
        turnOff();
        int scores[] = new int[2];
        if (choice == 1)   
            scores[1] = mt.getScore(clicked, starts, ends);
         else
            scores[1] = ms.getScore(clicked, starts, ends);  
                      
         if (scores[1] > scores[0])
            scores[0] = scores[1];

         addScores(scores[0]);
    }      
     /* PLACES SCORE ON THE BUTTON */
     public static void addScores(int scores)
     {
         highScore.setText("High Score: " + String.valueOf(scores) + " AKA you hit " + clicked + " buttons in about " + ((int) ends - (int) starts) + " miliseconds");
         System.out.println("Recent Score: " + String.valueOf(scores) + " AKA you hit " + clicked + " buttons in about " + ((int) ends - (int) starts) + " miliseconds");
         highScore. setFont(new Font("Arial", Font. PLAIN, 10));
         ready.setEnabled(true);
     }
     /* TURNS OFF ALL BUTTONS AND USED WHEN NEEDED */
     public static void turnOff()
     {
         for (int r = 0; r < 10; r++)
            for (int c = 0; c < 10; c++)
               buttons[r][c].setEnabled(false);
      }
      /* CHANGES THE TEXT OF THE COUNT BUTTON TO SAY THE NUMBER OF CLICKS */
      public static void changeCounter()
      {
         clickCount.setText(String.valueOf(clicked));
         clickCount.setFont(new Font("Arial", Font.PLAIN, 40));
      }         
            
   
/********************************** BUILDING FRAME / PANELS **************************************************************************************/

     /* CREATING BUTTONS WITH LABELS */
      public static void labelling()    
      { 
         int p = 1; 
         for (int r = 0; r < 10; r++)
         {
            for (int c = 0; c < 10; c++)
            {
               buttons[r][c] = new JButton(String.valueOf(p));
               p++;
            }
         }
        sidePanel();
      }
      /* CREATES SIDE PANEL FOR SCORES AND READY */
      public static void sidePanel() 
      {
         panel = new JPanel();
         panel.setBackground(Color.LIGHT_GRAY);
         panel.setBounds(1000, 0, 350, 900);
         gridPanelling();
      }
      /* CREATES GRID FOR BUTTONS TO BE PLACED ON */
      public static void gridPanelling() 
      {
         gridPanel = new JPanel();
         gridPanel.setBounds(0, 0, 1000, 900);
         gridPanel.setLayout(new GridLayout(10,10,0,0));
         readyMethod();
      }
      /* SETS UP READY BUTTON */
      public static void readyMethod() 
      {
         ready = new JButton("Click When Ready");
         ready.setPreferredSize(new Dimension(350, 150));
         panel.add(ready);
         ready.setEnabled(true);
         buttonActions();
         if (choice == 2)
            ready.addActionListener (e-> { checker = 17; looping = 0; playSpeed();  ready.setEnabled(false); } );      
        else
            ready.addActionListener (e-> { checker = 17; looping = 0; playTime(); ready.setEnabled(false); } );     
         highScoreFrame();
      }
      /* SETS UP HIGH SCORE ON SIDE */
      public static void highScoreFrame()
      {
         highScore = new JButton("High Scores This Session");
         highScore.setFont(new Font("Arial", Font.PLAIN, 40));
         highScore.setPreferredSize(new Dimension(350, 275));
         panel.add(highScore);
         highScore.setEnabled(false);
         highScore.setBackground(Color.DARK_GRAY);        
         clickCounter();
      }   
      /* SETS UP BUTTON THAT DISPLAYS NUMBER OF CLICKED BUTTONS */
      public static void clickCounter()
      {
         clickCount = new JButton("Click Count");
         clickCount.setFont(new Font("Arial", Font.PLAIN, 40));
         clickCount.setPreferredSize(new Dimension(350, 275));
         panel.add(clickCount);
         clickCount.setBackground(Color.DARK_GRAY);   
         clickCount.setEnabled(false);     
         restart();
      }         
      /* SETS UP RESTART BUTTON */
      public static void restart() 
      {
         again = new JButton("Play Again?");
         again.setPreferredSize(new Dimension(350, 150));
         panel.add(again);
         again.addActionListener(e -> { playAgain(); } );
         framing();
      }
      /* FINALIZING FRAME */
      public static void framing()      
      {
         frame = new JFrame("Mouse Accuracy");
         frame.getContentPane().setBackground(Color.LIGHT_GRAY);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLayout(null);
         frame.setSize(1350,950);
         frame.setVisible(true);
         frame.add(panel);
         frame.add(gridPanel);
         buttonFrame();      
      }       
     /* ADD BUTTONS ON GRID */  
     public static void buttonFrame()
     {
         for (int r = 0; r < 10; r++)
            for (int c = 0; c < 10; c++)
            {
               gridPanel.add(buttons[r][c]);
               buttons[r][c].setEnabled(false);
            } 
      }         
      /* SETS CODE IN ORDER TO PLAY AGAIN*/
      public static void playAgain()
      {
         ready.setEnabled(true);
         turnOff();
         clicked = 0;
         clickCount.setText(String.valueOf(clicked));
      }                  
}
   
   