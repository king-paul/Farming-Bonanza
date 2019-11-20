/**
 * @Name: MainMenu.java
 * @author: Paul King
 * @LastUpdated: 23/11/2014
 * @Description: This class contains the opening title screen which
 * allows the user to start a new game. When a previous game has been
 * has ended hte player returns to this main menu.
 */

package view;

import interfaces.Controllable;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import farmgame.Controller;

@SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener
{
   private Controllable game;

   public MainMenu()
   {
   // set the size of the frame and make it non-resizable
      this.setSize(800, 680);
      this.setResizable(false);
      
      //  set the frame to always appear on the center of the screen
      /** code obtained from StackOverflow. Not sure hot this works.
       * http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-gameBoarded-regardless-of-the-monitor-resolution
       **/
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
      
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // create container to go on the frame
      Container container = this.getContentPane();
      
      container.setLayout(new BorderLayout());
      
      //container.setBackground(arg0);
      
      JButton btnNewGame = new JButton("Begin New Game");
      JButton btnQuit = new JButton("Quit");
      
      JPanel center = new JPanel();
      JLabel imageLabel = new JLabel();
      imageLabel.setIcon(new ImageIcon("resources/title_screen.png"));

      center.add(imageLabel); 

      JPanel south = new JPanel(new FlowLayout());
      south.setPreferredSize(new Dimension(800, 40));
      
      south.add(btnNewGame);
      south.add(btnQuit);
      
      container.add(center, BorderLayout.CENTER);
      container.add(south, BorderLayout.SOUTH);
      
      btnNewGame.addActionListener(this);
      btnQuit.addActionListener(this);
      
      this.setVisible(true);
   }
   
// accessor to return the contoller
   public Controllable getController()
   {
      return game;
   }   
   
   @Override
   public void actionPerformed(ActionEvent e)
   {      
      String playerName = null;
      
      String command = e.getActionCommand();         
      
      if(command == "Begin New Game")
      {    
         do {
            // prompt the user for the player name
             playerName = JOptionPane.showInputDialog("Enter your Name", "player");
             
             if(playerName!= null && playerName.length() < 1)
                JOptionPane.showMessageDialog(null, "Name Required", "The name can't be blank.",
                                              JOptionPane.WARNING_MESSAGE);
             
         } while(playerName!= null && playerName.length() < 1);
         
         if(playerName != null)
         {          
            // create instance of the game controller
            game = new Controller(playerName);
            
            // create an instance of the mainGameWindow
            MainGameWindow gameWindow = new MainGameWindow("Farming Bonanza", game);
            // make the program shut down when the window is closed
            //gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // open the window
            gameWindow.setVisible(true);               
            this.setVisible(false);
         }
      }
      
      if(command == "Quit")      
      {
         // dispose of the window and close the program
         this.dispose();
         System.exit(0);
      }
      
   } // end of actionPerformed method


}
