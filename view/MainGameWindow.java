/** 
 * @Name: MainGameWindow.java
 * @Author: Paul King
 * @DateOfVersion: 23/11/2014
 * @Desciription: This file contains the class for the main frame that displays at
 * all times while the game is running. Many gui components and their layout have
 * been built inside the MainGameWindow constructor. Two inner nested classes have
 * been created to handle the action listening of buttons and radio buttons on the
 * user interface.
 */

package view;

import farmgame.*;
import interfaces.*;

// import gui packages
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

@SuppressWarnings("serial")
public class MainGameWindow extends JFrame implements LandPlotIcons, ActionListener, WindowListener
{
   // controller oject
   private Controllable game; 
   // create an instance of gameboard panel to go in the gameBoard
   private GameBoardPanel gameBoard;
   
   // dynamic labels on window
   private JLabel lblNameValue;
   private static JLabel lblBalanceValue;
   private static JLabel lblDateValue;
   private static JLabel lblMonthlyMaintValue;
   private static JLabel lblMonthlyRevenueValue;
   
   // create a text area to store the messages
   private static JTextArea messageText = new JTextArea();  
   
   // create a green color for text
   final Color DARK_GREEN = new Color(0, 128, 0); 
   
   // constructor for the main game window
   public MainGameWindow(String frameName, Controllable game)
   {     
      // create a new JFrame with the titlebar text 
      super(frameName); 
      
      this.game = game;
      gameBoard = new GameBoardPanel(this, game);
      
      // set the width and height of the window and make it visible
      this.setSize(760, 650);      
      // disable resizing of window
      this.setResizable(false);
      // add the window listener defined in this class to this frame      
      this.addWindowListener(this);
      // stop the frame from closing by default when the X button is clicked
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

      // Ensure that the screen always appears on the gameBoard of the screen
      /** code obtained from StackOverflow. Not sure hot this works.
       * http://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-gameBoarded-regardless-of-the-monitor-resolution
       **/
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);     
      
      // create a container to put content on
      Container content = this.getContentPane();
      
      // Create panels for the border layout      
      JPanel north = new JPanel();
      JPanel west = new JPanel();
      JPanel south = new JPanel(); 
      JPanel center = new JPanel();
      JPanel southWest = new JPanel();
      
      // create a scroll pane for a message game board
      JScrollPane farmPane = new JScrollPane(gameBoard);
      // add the scroll pane to the center panel
      center.add(farmPane);

      // set the layout for the container
      content.setLayout(new BorderLayout(5,5));
      
      // set the layout for the gameboard panel
      gameBoard.setLayout(new GridLayout(6, 8, 0, 0));      
      //gameBoard.setBackground(Color.BLACK);
      
      JLabel titleLabel = new JLabel("FARMING GAME");
      titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
      
      // create a blank label to fill up space
      JLabel lblBlank = new JLabel();
      
      ImageIcon logo = new ImageIcon("resources/logo.png");
      JLabel lblTitle = new JLabel();
      lblTitle.setIcon(logo);
      
      // add label to the north panel
      north.add(lblTitle);
       
      // create buttons to go on south panel
      JButton btnNextMonth = new JButton("Go To Next Month");
      JButton btnAbortGame = new JButton("Abort Game");
      // add action listeners to the buttons
      btnNextMonth.addActionListener(this);
      btnAbortGame.addActionListener(this);
      
      // set the with and heigh dimension for the buttons
      Dimension buttonDimension = new Dimension(200, 32);      
      btnNextMonth.setPreferredSize(buttonDimension);
      btnAbortGame.setPreferredSize(buttonDimension);
      
      south.setLayout(new BorderLayout());

      //JPanel tooltipPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // goes on south
      
      // add message label to south panel
      //tooltipPanel.add(lblTooltip);
      
      // create fonts for the window labels
      Font staticLabel = new Font("Arial", Font.PLAIN, 18);
      Font dynamicLabel = new Font("Arial", Font.PLAIN, 18);
      Font staticMoneyLabel = new Font("Arial", Font.BOLD, 14);
      Font dynamicMoneyLabel = new Font("Arial", Font.PLAIN, 14);
      
      // create static labels for player information
      JLabel lblPlayer = new JLabel("Farmer:");
      lblPlayer.setFont(staticLabel);
      lblPlayer.setForeground(Color.RED);      
      JLabel lblDate = new JLabel("Date:");
      lblDate.setFont(staticLabel); 
      lblDate.setForeground(Color.RED);
      JLabel lblBalance = new JLabel("Balance:");
      lblBalance.setFont(staticLabel);
      lblBalance.setForeground(Color.RED);
      
      // create static labels for monthly finance information
      JLabel lblMonthlyFinance = new JLabel("Monthly Finance");
      lblMonthlyFinance.setFont(staticMoneyLabel);
      JLabel lblMonthlyCost = new JLabel("Maintenance:");
      lblMonthlyCost.setFont(staticMoneyLabel);
      lblMonthlyCost.setForeground(Color.RED);
      JLabel lblMonthlyRevenue = new JLabel("Revenue:");
      lblMonthlyRevenue.setFont(staticMoneyLabel);
      lblMonthlyRevenue.setForeground(Color.RED);
      
      // create the dynamic labels for the values of the player information      
      lblNameValue = new JLabel(game.getName());
      lblNameValue.setFont(dynamicLabel);
      lblNameValue.setPreferredSize(new Dimension(100, 20));
      
      //lblNameValue.setForeground(Color.WHITE);
      lblDateValue = new JLabel(GameCalendar.getDateString());
      lblDateValue.setFont(dynamicLabel);
      //lblDateValue.setForeground(Color.WHITE);
      lblMonthlyMaintValue = new JLabel("$"+((Controller) game).getMonthlyCost());
      lblMonthlyMaintValue.setFont(dynamicMoneyLabel);
      //lblMonthlyMaintValue.setForeground(Color.WHITE);
      lblMonthlyRevenueValue = new JLabel("$"+((Controller) game).getMonhtlyRevenue());
      lblMonthlyRevenueValue.setFont(dynamicMoneyLabel);
      //lblMonthlyRevenueValue.setForeground(Color.WHITE);
      lblBalanceValue = new JLabel("$"+game.getWorth());
      lblBalanceValue.setFont(new Font("Arial", Font.BOLD, 16));
      lblBalanceValue.setForeground(DARK_GREEN);
      
      // create border for the info panel
      Border grayBorder = BorderFactory.createLineBorder(Color.GRAY);
      
      // create stats panel for the west side
      JPanel statPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));//(new GridLayout(3, 2, 5, 0));
      //statPanel.setBackground(Color.GRAY);
      statPanel.setPreferredSize(new Dimension(200, 80));
      
      JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));//new GridLayout(3, 2));
      summaryPanel.setPreferredSize(new Dimension(200, 70));
      
      //statPanel.setBackground(Color.LIGHT_GRAY);
      summaryPanel.setBackground(Color.LIGHT_GRAY);
      
      // add the border to the panels
      //statPanel.setBorder(grayBorder);
      summaryPanel.setBorder(grayBorder);
      
      // add the lables to the west panel
      statPanel.add(lblPlayer);
      statPanel.add(lblNameValue);      
      statPanel.add(lblDate);
      statPanel.add(lblDateValue);
      statPanel.add(lblBalance);
      statPanel.add(lblBalanceValue); 
      summaryPanel.add(lblMonthlyFinance);
      summaryPanel.add(lblBlank);
      summaryPanel.add(lblMonthlyCost);
      summaryPanel.add(lblMonthlyMaintValue);
      summaryPanel.add(lblMonthlyRevenue);
      summaryPanel.add(lblMonthlyRevenueValue);

      // add the infor panels to the west panel
      west.add(statPanel);      
      west.add(summaryPanel);
      
      // add the panels to the container
      content.add(north, BorderLayout.NORTH); 
      
      content.add(south, BorderLayout.SOUTH);
      //content.add(east, BorderLayout.EAST);
      content.add(west, BorderLayout.WEST);
      content.add(center, BorderLayout.CENTER);

      /**
       * Create a set of radio buttons for different land plot actions
       */
      Font fontRadioButtons = new Font("Arial", Font.PLAIN, 20);
      
      // create label to go above radio buttons
      JLabel lblLandActions = new JLabel("Land Actions"); 
      lblLandActions.setFont(fontRadioButtons);
      
      // create radio button group to hold radio buttons
      ButtonGroup bgLandActions = new ButtonGroup();
      
      // create the radio buttons
      JRadioButton rbBuyLand = new JRadioButton("Buy Land"),
                   rbSellLand = new JRadioButton("Sell Land"),
                   rbRecultivate = new JRadioButton("Recultivate Land"),
                   rbBuyProduce = new JRadioButton("Buy Produce"),
                   rbSellProduce = new JRadioButton("Sell Produce");
      
      // set the font for the radio buttons
      rbBuyLand.setFont(fontRadioButtons);
      rbSellLand.setFont(fontRadioButtons);
      rbRecultivate.setFont(fontRadioButtons);
      rbBuyProduce.setFont(fontRadioButtons);
      rbSellProduce.setFont(fontRadioButtons);   
      
      // add action listeners to the radio buttons
      rbBuyLand.addActionListener(this);
      rbSellLand.addActionListener(this);
      rbRecultivate.addActionListener(this);
      rbBuyProduce.addActionListener(this);
      rbSellProduce.addActionListener(this);
      
      // Add the radio buttons to the radio buttons group
      bgLandActions.add(rbBuyLand);
      bgLandActions.add(rbSellLand);
      bgLandActions.add(rbRecultivate);
      bgLandActions.add(rbBuyProduce);
      bgLandActions.add(rbSellProduce);
      
      west.setPreferredSize(new Dimension(210, 600));
      west.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      // create the panel for the radio buttons and set it's properties
      JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      optionsPanel.setPreferredSize(new Dimension(200, 224));       
      //optionsPanel.setBorder(grayBorder); // add the grey border to the panel
      
      // add the buttons and radio buttons to the options panel  
      optionsPanel.add(lblLandActions);
      optionsPanel.add(rbBuyLand);
      optionsPanel.add(rbSellLand);
      optionsPanel.add(rbRecultivate);
      optionsPanel.add(rbBuyProduce);
      optionsPanel.add(rbSellProduce);
      
      // add the options panel to the est panel
      west.add(optionsPanel);
      
      // set the properties of the message text area    
      messageText.setEditable(false);    
      //messageText.setBackground(Color.LIGHT_GRAY);
      
      // set the messageText text area to autimatically scroll to the bottom
      // whenever a new message gets added
      DefaultCaret caret = (DefaultCaret) messageText.getCaret();
      caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
      
      // set the intro message
      messageText.setText("Welcome to the farming game.\n" +
                          "To interact with the land plots select an option from the radio buttons.\n"); 
      
      // set the font for the text area
      Font messageFont = new Font("arial", Font.BOLD, 12);
      float fontSize = messageFont.getSize() + 1.0f;
      messageText.setFont(messageFont.deriveFont(fontSize));
      
      JScrollPane messageBox = new JScrollPane(messageText);    
      messageBox.setPreferredSize(new Dimension(515, 120));
      messageBox.setBackground(Color.lightGray);
      
      //southWest.setLayout(new GridLayout(5, 1, 10, 10));
      southWest.setLayout(new FlowLayout(FlowLayout.LEFT));
      southWest.setPreferredSize(new Dimension(220, 130));

      south.setPreferredSize(new Dimension(500, 130));
      
      // add the buttons to the south west panel
      southWest.add(btnNextMonth);
      southWest.add(btnAbortGame);
      
      JPanel southEast = new JPanel();
      
      JPanel messagePanel = new JPanel(new FlowLayout());
      //messagePanel.add(tooltipPanel);
      messagePanel.add(messageBox);
      
      south.setLayout(new BorderLayout());
      south.add(messagePanel, BorderLayout.CENTER);
      south.add(southWest, BorderLayout.WEST);
      south.add(southEast, BorderLayout.EAST);
      
   } // end of constructor
    
   // refresh all the labels in the info panel
   public void updateStatLabels()
   {
      lblBalanceValue.setText("$"+game.getWorth());
      lblDateValue.setText(GameCalendar.getDateString());
      lblMonthlyMaintValue.setText("$"+((Controller) game).getMonthlyCost());
      lblMonthlyRevenueValue.setText("$"+((Controller) game).getMonhtlyRevenue());
   }
   
   /* static methods for adding, changing and clearing the messages 
    * in the text panel */   
   public static void addMessage(String message)
   {
      messageText.append(message +"\n");
   }
   
   public static void setMessage(String message)
   {
      messageText.setText(message + "\n");
   }
   
   public static void clearMessages()
   {
      messageText.setText(null);
   }  
   
   // static accessor to return the game board
   public GameBoardPanel getGameBoard()
   {
      return gameBoard;
   }   
   
   private void endGameConfirmation()
   {
      // confirm the the use want to end the game
      int option = JOptionPane.showConfirmDialog(null, "Are you sure you wish to end this game? Progress will not be saved!",
                                                 "Abort Game Confirmation", JOptionPane.OK_OPTION);
      // check that ok was clicked
      if(option == JOptionPane.OK_OPTION)
      {
         // close this frame and re-open the main menu
         this.setVisible(false);
         Driver.main(null);
      }
   }
   
   // event listening method that is triggerd whenever a button or radio button is clicked
   @Override
   public void actionPerformed(ActionEvent arg)
   {
      // get the text from the button or radio button
      String command = arg.getActionCommand();
      
      if(command.equals("Buy Land"))
      {
         // set a tooltip message telling the user what to do next
         setMessage("Hover the mouse cursor over an adjacent land plot that is highlighted and\n" +
                    "click to purchase an unowned land plot.");
         // highlight all the land plots that can be bought
         gameBoard.showBuyable(true);
         // set the action to perfrom when the land plots are clicked
         LandPlotListener.setAction(command);
      }
      else
      {
         // hide all the buyable landplots
         gameBoard.showBuyable(false);
      }
      
      if(command.equals("Sell Land"))
      { 
         setMessage("Click on a land plot you currently own in order to sell it.");         
         LandPlotListener.setAction(command); 
      }
      
      if(command.equals("Recultivate Land"))
      {
         setMessage("Click on a land plot that you own which is polluted in order to\n" +
                    "recultivate the land.");
         LandPlotListener.setAction(command); 
      }
      
      if(command.equals("Buy Produce"))
      {
         setMessage("Click on a vacant land plot that you own to purchase produce to place on it.");
         LandPlotListener.setAction(command); 
      }
      
      if(command.equals("Sell Produce"))
      {
         setMessage("Click on a land plot that has produce on it to sell it for the sale price.");
         LandPlotListener.setAction(command); 
      }
      
      if(command.equals("Go To Next Month"))
      {  
         try
         {
            game.advanceCalendar(); // perform actions in controller  
            gameBoard.redrawIcons(); // replace any icons where events occur             
            updateStatLabels(); // update the info panel
            
            if(GameMessage.getMessages().length() > 0) // if the string has at least one character in it
               addMessage(GameMessage.getMessages()); // adds message of monthly summary to the message box
            
            GameMessage.clearMessages(); // remove all messages from the game message arraylist
            clearMessages(); // remove all messages from the messages text area
         }
         catch(BankruptException ex)
         {
            updateStatLabels();
            JOptionPane.showMessageDialog(null, "You have gone bankrupt. Reason: "+ex.getMessage(), "BankruptAlert",
                                          JOptionPane.WARNING_MESSAGE);
            // close this frame and re-open the main menu
            this.setVisible(false);
            Driver.main(null);
         }
      }
      if(command.equals("Abort Game"))
      {
         endGameConfirmation();
      }   
   } 
   
// event that is triggered whenver the MainGameWindow frame is about to close
   @Override
   public void windowClosing(WindowEvent e)
   {      
      endGameConfirmation();
   }

   @Override
   public void windowActivated(WindowEvent arg0)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowClosed(WindowEvent arg0)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowDeactivated(WindowEvent arg0)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowDeiconified(WindowEvent arg0)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowIconified(WindowEvent arg0)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void windowOpened(WindowEvent arg0)
   {
      // TODO Auto-generated method stub
      
   }
   
} // end of class