import javax.swing.*;
import java.awt.*; 
import java.util.ArrayList;
import java.util.*;
//Christmas - 2021

public class PortfolioMemoryShrestha
{        
     static JFrame frame;   
     static JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18;  
     static JButton easy, mid, hard, ready, fail, wins, end, restart;
     static ArrayList<JButton> buttons = new ArrayList<JButton>();
     static ArrayList<JButton> difficulty = new ArrayList<JButton>();
     static int range, checks, count, Lcount, cc, xx; //Lcount is the count of failed attempts - checks + count is to check order - range is number of buttons per difficulty

     public static void main(String args[])
	{    
           framing();
           difficultyM();                  
           frame.setVisible(true);  
     }    
     
/************************************************************************************************************************/   
/*******************************************SET UP METHODS***********************************************************/      
         public static void framing()     //sets up the frame 
         {
               frame=new JFrame("Game of Memory");
               frame.setSize(1350,900);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.getContentPane().setBackground(Color.cyan);
               frame.setLayout(new FlowLayout());
         }       

         public static void difficultyM()     //sets up first page and transition
         {
           easy = new JButton("EASY - 6");         //chooses how many boxes
           mid = new JButton("MID - 12"); 
           hard = new JButton("HARD - 18"); 
           
           easy.addActionListener (e -> { range = 6; transition(); } );       
           mid.addActionListener (e -> { range = 12; transition(); } );
           hard.addActionListener (e -> { range = 18; transition(); } ); 
               
           difficulty.add(easy);                         //adding them into arraylist
           difficulty.add(mid);
           difficulty.add(hard);  
           
           for (JButton q: difficulty) 
            {
               q.setFont(new Font("TimesRoman", Font.PLAIN, 100)); //button formatting to make it look nice
               q.setPreferredSize(new Dimension(1000,250)); 
               frame.add(q);   
            }
         }       
         
         public static void transition() 
         {
            easy.setVisible(false);  //removes the buttons and transitions to game
            mid.setVisible(false);
            hard.setVisible(false);
            labels();                        
            buttonFraming();
            ready();
         }         
                                
         public static void buttonFraming()       //sets up the second page with random order buttons 
         {
            Collections.shuffle(buttons);             //shuffles the orders of the buttons
            for (JButton q: buttons)
            {
               q.setFont(new Font("TimesRoman", Font.PLAIN, 100));
               q.setPreferredSize(new Dimension(200,200));
               q.setForeground(Color.BLACK);
               frame.add(q);                  
               q.setEnabled(false);                      //set false originally so that any clicks before ready don't matter
            }
         }
            
         public static void ready()                      //used after studying order of random buttons then to test it actually
         {
            ready = new JButton("Click Here When Ready");
            ready.setFont(new Font("TimesRoman", Font.BOLD, 100));
            ready.setPreferredSize(new Dimension(1200,250));
            frame.add(ready);            
            
            ready.addActionListener       
            (e-> {
               for (JButton q: buttons)   {                       
                  q.setBackground(Color.BLACK);          //hides the numbers on the buttons and now allows buttons to be pressed actually
                  q.setEnabled(true);  }
                  
               ready.setVisible(false);                        //removes the ready button to make it clean
               failCounter();                                                   //adds the # of fails button to prepare
            }  );
         }                      
         
        public static void failCounter()
        {           
            fail = new JButton("FAIL COUNT: " + String.valueOf(Lcount));      //string method
            fail.setFont(new Font("TimesRoman", Font.BOLD, 100));
            fail.setPreferredSize(new Dimension(1200,250));
            frame.add(fail);
         }         
/*******************************************WIN LOSS CHECK***********************************************************/      
        public static void checkOrder()            //checks to make sure the 1st button is 1st, 2nd is 2nd and so on
        {            
            if (checks != count)                            //checks and count originally start at 0 and should equally go up one at the same time - if not then its wrong
            {
               for (JButton q: buttons)               // if its wrong then it resets and all buttons are open again
                  q.setEnabled(true);
               checks = 0;                                     //numbers must resent too
               count = 0;
               cc = 1;
               Lcount++;
               fail.setText("FAIL COUNT: " + String.valueOf(Lcount));         //this changes the number on the fail count in real time - Lcount is the number of fails
            }   
            else
               cc++; 
            checkWin();    
        }
        
        public static void checkWin()                                               //checks to see if all buttons are pressed finally in the right order
        {
            if (checks == count && count == range)                            //has to make sure checks/count are equal to the total number of buttons - if so then you won
            {
               for (JButton q: buttons)                                             
                     q.setVisible(false);                                                    //removes buttons from frame
               fail.setVisible(false);
               
               wins = new JButton ("Congrats! You Won! It took " + (Lcount+1) + " tries");                //displays and formats final win message
               wins.setFont(new Font("TimesRoman", Font.BOLD, 35));
               wins.setPreferredSize(new Dimension(1200,250));
               frame.add(wins);
               postGame();
            }
         }                     
  
/*******************************************ENDING METHOD***********************************************************/                
         public static void postGame()                                        //under win message displays option to quit or play again
         {
            end = new JButton ("Click here to end the game");              //button to quit - system exit 0 closes system
            end.setFont(new Font("TimesRoman", Font.BOLD, 35));
            end.setPreferredSize(new Dimension(1200,250));
            frame.add(end);
            end.addActionListener (e-> { System.exit(0); } );
            
            restart = new JButton ("Click here to play again");
            restart.setFont(new Font("TimesRoman", Font.BOLD, 35));
            restart.setPreferredSize(new Dimension(1200,250));
            frame.add(restart);
            restart.addActionListener                                               //restarts game
            (e-> { 
               restart.setVisible(false);                                              //removes the buttons from the screen
               end.setVisible(false);
               wins.setVisible(false);
               count = 0;                                                                       //resets the numbers to 0 (other numbers will be changed when called in their own methods)
               Lcount = 0;
               difficultyM();                                                                    //recalls difficulty starting method like in main to restart process
            } );
         }


/*******************************************LABEL METHODS***********************************************************/            
         
         public static void labels()         //creates buttons with their texts each - just up to 18 since thats the max
         {    
            b1 = new JButton ("1"); b2 = new JButton ("2");
            b3 = new JButton ("3");  b4 = new JButton ("4");
            b5 = new JButton ("5");  b6 = new JButton ("6");
            b7 = new JButton ("7");  b8 = new JButton ("8");
            b9 = new JButton ("9");  b10 = new JButton ("10");
            b11 = new JButton ("11");  b12 = new JButton ("12");
            b13 = new JButton ("13");  b14 = new JButton ("14");
            b15 = new JButton ("15");  b16 = new JButton ("16");
            b17= new JButton ("17");  b18 = new JButton ("18");
            listeners();
         }
      
         public static void listeners()      //adds the listeners up to the max - 18 
         {
               b1.addActionListener (e-> { checks = 1; count++; b1.setEnabled(false);  checkOrder();   }  );         //checks is to check the order of the buttons pressed
               b2.addActionListener (e-> { checks = 2; count++; b2.setEnabled(false); checkOrder();   }  );        //count is also used to check order 
               b3.addActionListener (e-> { checks = 3; count++; b3.setEnabled(false); checkOrder();   }  );        //set to not enabled because don't want to be pressed before hand 
               b4.addActionListener (e-> { checks = 4; count++; b4.setEnabled(false); checkOrder();   }  );       
               b5.addActionListener (e-> { checks = 5; count++; b5.setEnabled(false); checkOrder();   }  );
               b6.addActionListener (e-> { checks = 6; count++; b6.setEnabled(false); checkOrder();   }  );
               b7.addActionListener (e-> { checks = 7; count++; b7.setEnabled(false); checkOrder();   }  );
               b8.addActionListener (e-> { checks = 8; count++; b8.setEnabled(false); checkOrder();   }  );
               b9.addActionListener (e-> { checks = 9; count++; b9.setEnabled(false); checkOrder();   }  );
               b10.addActionListener (e-> { checks = 10; count++; b10.setEnabled(false); checkOrder();   }  );
               b11.addActionListener (e-> { checks = 11; count++; b11.setEnabled(false); checkOrder();   }  );
               b12.addActionListener (e-> { checks = 12; count++; b12.setEnabled(false); checkOrder();   }  );
               b13.addActionListener (e-> { checks = 13; count++; b13.setEnabled(false); checkOrder();   }  );
               b14.addActionListener (e-> { checks = 14; count++; b14.setEnabled(false); checkOrder();   }  );
               b15.addActionListener (e-> { checks = 15; count++; b15.setEnabled(false); checkOrder();   }  );
               b16.addActionListener (e-> { checks = 16; count++; b16.setEnabled(false); checkOrder();   }  );
               b17.addActionListener (e-> { checks = 17; count++; b17.setEnabled(false); checkOrder();   }  );
               b18.addActionListener (e-> { checks = 18; count++; b18.setEnabled(false); checkOrder();   }  );   
               inArrays();
         }        
         
         public static void inArrays()
         {
            if (range == 6)
            {
               buttons.add(b1);  buttons.add(b2);        //adds the specific number of buttons in the arraylist
               buttons.add(b3);  buttons.add(b4);       //specic number of buttons is from the original difficulty choice
               buttons.add(b5);  buttons.add(b6);
            }
            else if (range == 12)
            {
               buttons.add(b1);  buttons.add(b2);
               buttons.add(b3);  buttons.add(b4);
               buttons.add(b5);  buttons.add(b6);
               buttons.add(b7);  buttons.add(b8);
               buttons.add(b9);  buttons.add(b10);
               buttons.add(b11);  buttons.add(b12);
            }
            else
            {    
               buttons.add(b1);  buttons.add(b2);
               buttons.add(b3);  buttons.add(b4);
               buttons.add(b5);  buttons.add(b6);
               buttons.add(b7);  buttons.add(b8);
               buttons.add(b9);  buttons.add(b10);
               buttons.add(b11);  buttons.add(b12);
               buttons.add(b13);  buttons.add(b14);
               buttons.add(b15);  buttons.add(b16);
               buttons.add(b17);  buttons.add(b18);
            }
         }      
}         
