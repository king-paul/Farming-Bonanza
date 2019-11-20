/** @File: LandPlotListener.java
 *  @Author: Paul King
 *  @DateOfVersion: 23/11/2014
 *  @Desciription: This class used to handle the listeners that have been
 *  added to the land plot buttons on the game board. When a land plot
 *  button is clicked, the 'actionPerformed' method is fired which triggers
 *  other methods based on which radio button is selected. A static variable
 *  action stores the command that is created whenever a radio button is 
 *  selected.
 *  Actions for buying land, selling land, recultivating land,
 *  buying produce and selling produce is handled here.
 */

package view;

// import packages from project
import interfaces.*;
import farmgame.*;
import model.*;
// import java gui packages
import javax.swing.*;
import java.awt.event.*;

public class LandPlotListener implements ActionListener, LandPlotIcons
{  
   private Controllable game;
   private GameBoardPanel gameBoard;
   private MainGameWindow gameWindow;
    
   // data members
   private final LandPlotButton landPlot;
   private static String action;
   
   // constructors
   public LandPlotListener(LandPlotButton landplot, MainGameWindow gameWindow, Controllable controller)
   {
      this.landPlot = landplot;
      this.game = controller;
      this.gameWindow = gameWindow;
   }
      
   public static void setAction(String landAction)
   {
      action = landAction;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {     
      // if a radio button is selected get the action selected
      if(action!= null)
      {
         if(action.equals("Buy Land"))        
            buyLand();
         if(action.equals("Sell Land"))
            sellLand();
         if(action.equals("Recultivate Land"))
            recultivate();
         if(action.equals("Buy Produce"))
            buyProduce();  
         if(action.equals("Sell Produce"))
            sellProduce();
      }
      
      gameWindow.updateStatLabels();
   }
   
   private void buyLand()
   { 
      if(((Controller)game).isAdjacent(landPlot.X, landPlot.Y))
      {
         // dispay a yes/no dialog confirming the user on the purchase
         int selection = JOptionPane.showConfirmDialog(null, "Would you like to buy this land plot for $"+LandArea.LAND_COST+"?", 
                                                      "Land Prurchase Confirmation", JOptionPane.YES_NO_OPTION);
         
         // if the user did not click yes, exit this method
         if(selection != JOptionPane.YES_OPTION)
            return;
         
         if(game.buyLand(landPlot.X, landPlot.Y))
         {
            // point to reference of game board from MainGameWindow class
            gameBoard = gameWindow.getGameBoard(); 
            
            if(game.isDevelopable(landPlot.X, landPlot.Y))
               landPlot.setIcon(icoLandOwned);
            else
               landPlot.setIcon(icoLandPollutedOwned);
                // change the icon to grass
            
            gameBoard.showBuyable(true); // update the visible land plots
            
            JOptionPane.showMessageDialog(null, "The Land plot has been purchased.", "Land Pruchase Succesful", JOptionPane.INFORMATION_MESSAGE);
            MainGameWindow.addMessage("Land plot ("+landPlot.X+", "+landPlot.Y+") has been bought.");
         }
         else
         {
            MainGameWindow.addMessage(GameMessage.getMessage());
         }
      }
      else
      {
         // the plot is not adjacent to any owned lands        
         GameMessage.setMessage("You can only purchase a land plot that is adjacent to one that you own.");
         JOptionPane.showMessageDialog(null, "The Land plot could not be purchased.", "Land Pruchase Failed", JOptionPane.WARNING_MESSAGE);
      }
   }
   
   private void buyProduce()
   {
      ShopDialog shop = new ShopDialog();
      
      int option = shop.getOptionSelected();
      String command = shop.getProduceSelected();
      
      Produce produce = null;
      Icon produceIcon = null;      
      
      if(command.equals("Wheat"))
      {
         produce = new Wheat();
         produceIcon = icoWheatImmature;
      }
      else if(command.equals("Apples"))
      {
         produce = new Apple();
         produceIcon = icoApplesImmature;
      }
      else if(command.equals("Cows"))
      {
         produce = new Cow();
         produceIcon = icoCow;
      }
      else if(command.equals("Sheep"))
      {
         produce = new Sheep();
         produceIcon = icoSheep;
      }
      
      if(option == JOptionPane.OK_OPTION)
      {    
         if( game.buyProduce(landPlot.X, landPlot.Y, produce))
         {
            landPlot.setIcon(produceIcon);
            MainGameWindow.addMessage(produce.getName()+" has been bought for land plot "+landPlot.X+", "+ landPlot.Y);
         }
         else
            MainGameWindow.addMessage(GameMessage.getMessage());
      }   
   }
   
   private void sellLand()
   {
      // prompt yes/no dialoge to confirm sale of land
      int selection = JOptionPane.showConfirmDialog(null, "Would you like to sell this land plot for $"+LandArea.LAND_SALE+"?",
                                    "Land Sale Confirmation", JOptionPane.YES_NO_OPTION);
      
      // leave method if the user does not click yes
      if(selection != JOptionPane.YES_OPTION)
         return;
      
      if(game.sellLand(landPlot.X, landPlot.Y))
      {
         // check to see that the land is not polluted
         if(game.isDevelopable(landPlot.X, landPlot.Y))
            landPlot.setIcon(icoLandUnowned); 
         else
            landPlot.setIcon(icoLandPollutedUnowned);
               
         MainGameWindow.addMessage("Land plot "+landPlot.X+", "+landPlot.Y+"has been sold.");
         JOptionPane.showMessageDialog(null, "The Land plot has been sold.", "Land Sale Succesful", JOptionPane.INFORMATION_MESSAGE); 
      }
      else
      {
         MainGameWindow.addMessage(GameMessage.getMessage());
         JOptionPane.showMessageDialog(null, "This Land plot could not be sold.", "Land Sale Failed", JOptionPane.WARNING_MESSAGE);
         
      }
   }
   
   private void recultivate()
   {
      LandPlot plot = new LandPlot();
      
      // prompt yes/no dialoge to confirm recultivation of land
      int selection = JOptionPane.showConfirmDialog(null, "Would you like to recultivate this land for $"+plot.getRecultivateCost()+"?",
                                    "Land Sale Confirmation", JOptionPane.YES_NO_OPTION);
      
      // leave method if the user does not click yes
      if(selection != JOptionPane.YES_OPTION)
         return;
      
      if(game.recultivate(landPlot.X, landPlot.Y))
      {
         landPlot.setIcon(icoLandOwned); // change to unpoluted grass icon
         
         MainGameWindow.addMessage("Land plot "+landPlot.X+", "+landPlot.Y+"has been recultivated.");
         JOptionPane.showMessageDialog(null, "The land has been recultivated", "Recultivate Successful", JOptionPane.INFORMATION_MESSAGE);   
      }
      else
      {
         MainGameWindow.addMessage(GameMessage.getMessage());
         JOptionPane.showMessageDialog(null, "The land could not be recultivated", "Recultivate Failed", JOptionPane.WARNING_MESSAGE);
      }
   }
   
   // triggered when the user clicks on a land plot while the sell produce radio button is selected
   private void sellProduce()
   {      
      double salePrice;
      int option;
      
      Produce produce = game.getProduce(landPlot.X, landPlot.Y);
      
      if(produce == null)
      {
         MainGameWindow.addMessage("There is no produce on that landplot to be sold.");
         return;
      }      
      
      salePrice = produce.getWorth(GameCalendar.getDate());
      
      if(produce instanceof Crop && ((Crop) produce).getStatus() == Crop.IMMATURE)
      {
         option = JOptionPane.showConfirmDialog(null, "The produce on this land plot is immature. You can destroy it now and receive $0\n"+
                                                      "and the land will need to be recultivated.\n"+
                                                      "Would you like to do this?", "Destroy Produce?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); 
      }
      else
      {
         option = JOptionPane.showConfirmDialog(null, "The produce at this land plot is currently worth $"+salePrice +
                                                       "\nWould you like to sell it?", "Purhcase confirmation", JOptionPane.YES_NO_OPTION);
      }
      
      if((option == JOptionPane.YES_OPTION))
      {
         if(game.sellProduce(landPlot.X, landPlot.Y))
         {            
            if(produce instanceof Crop && ((Crop) produce).getStatus() == Crop.IMMATURE)
            {
               landPlot.setIcon(icoLandPollutedOwned);
               MainGameWindow.addMessage(produce.getName()+" at ("+landPlot.X+", "+landPlot.Y+") has been destroyed.");               
            }
            else
            {
               landPlot.setIcon(icoLandOwned);
               MainGameWindow.addMessage(produce.getName()+" at ("+landPlot.X+", "+landPlot.Y+") has been sold for $"+salePrice);
               //JOptionPane.showMessageDialog(null, "The Produce has been sold", "Sale Successful", JOptionPane.INFORMATION_MESSAGE);
            }
            
         }
         else
            MainGameWindow.addMessage(GameMessage.getMessage());
      }
      
   }
   
}