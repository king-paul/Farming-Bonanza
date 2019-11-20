/** @File: GameBoardPanel.java
 *  @Author: Paul King
 *  @DateOfVersion: 23/11/2014
 *  @Desciription: This class created a JPanel inside the constructor that
 *  places all the land plot JButtons on the panel in a grid layout. It also
 *  places neccessary icons on the buttons to represent what is on the land
 *  plot. This panel gets added to the main game window in MainGameWindow.java.
 */

package view;

// import packages from project
import farmgame.*;
import interfaces.*;
import model.*;
// import packages java gui packages
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameBoardPanel extends JPanel implements LandPlotIcons
{
   private Controllable game;

   // get the the max rows and columns from the controller
   private final int COLUMNS = 8;//game.getXSize();
   private final int ROWS = 6;//game.getYSize();

   private LandPlotButton[][] gameBoardTiles = new LandPlotButton[COLUMNS][ROWS];
   
   public GameBoardPanel(MainGameWindow gameWindow, Controllable game)
   {
      this.game = game;
      
      // set the width and height of the game board
      this.setPreferredSize(new Dimension((COLUMNS*64), (ROWS*64)));
      
      LandPlotListener listener;
      
      for(int y =0; y < ROWS; y++)
      {
         for(int x=0; x < COLUMNS; x++)
         {  
            // intitialize the button at each space
            gameBoardTiles[x][y] = new LandPlotButton(x, y);
            // instantiate the listener
            listener = new LandPlotListener(gameBoardTiles[x][y], gameWindow, game);
            
            // add am actoion listener or every land plot button
            gameBoardTiles[x][y].addActionListener(listener);
            // add the button to the JPanel
            this.add(gameBoardTiles[x][y]);            
            
            // Assign the appropriate labels to all of the icons
            Produce produce = game.getProduce(x, y);      

            if(game.isOwned(x, y))
            {
               if(produce == null)
               {
                  if(game.isDevelopable(x, y))
                     gameBoardTiles[x][y].setIcon(icoLandOwned);                 
                  else
                     gameBoardTiles[x][y].setIcon(icoLandPollutedOwned);
               }
               if(produce instanceof Wheat)
               {
                  if( ((Crop) produce).getStatus() == Crop.IMMATURE )
                     gameBoardTiles[x][y].setIcon(icoWheatImmature);
                  if( ((Crop) produce).getStatus() == Crop.MATURE )
                  gameBoardTiles[x][y].setIcon(icoWheatMature);
                  if( ((Crop) produce).getStatus() == Crop.WARNING )
                     gameBoardTiles[x][y].setIcon(icoWheatWarning);
               }
               if(produce instanceof Apple)
               {
                  if( ((Crop) produce).getStatus() == Crop.IMMATURE )
                     gameBoardTiles[x][y].setIcon(icoApplesImmature);
                  if( ((Crop) produce).getStatus() == Crop.MATURE )
                  gameBoardTiles[x][y].setIcon(icoApplesMature);
                  if( ((Crop) produce).getStatus() == Crop.WARNING )
                     gameBoardTiles[x][y].setIcon(icoApplesWarning);
               }
               if(produce instanceof Cow)
                  gameBoardTiles[x][y].setIcon(icoCow);
               if(produce instanceof Sheep)
                  gameBoardTiles[x][y].setIcon(icoSheep);               
            }
            else
            {
               if(game.isDevelopable(x, y))
                  gameBoardTiles[x][y].setIcon(icoLandUnowned);
               else
                  gameBoardTiles[x][y].setIcon(icoLandPollutedUnowned); 
            }
            
         }
      } // end of for loops   
      
   } // end of method
   
   // redraws all the icons on the landplot to match what is on them   
   public void redrawIcons()
   {
      LandPlot plot;
      GameEvent[] eventQueue = game.getEventQueue();
      
      for(int i=0; i< eventQueue.length; i++)
      { 
         // get the land plot from specified x and y coordinates
         plot = eventQueue[i].getLandPlot();
         
         Produce produce = plot.getProduce();         
         LandPlotButton plotButton = gameBoardTiles[eventQueue[i].X][eventQueue[i].Y];
         
         // check if the produce is still on the plot
         if(plot.getProduce() == eventQueue[i].getProduce())
         {
            if(eventQueue[i] instanceof MaturityEvent)
            {    
               if(produce instanceof Wheat)                            
                  plotButton.setIcon(icoWheatMature);
               else if(produce instanceof Apple)
                  plotButton.setIcon(icoApplesMature);               
            }
            else if(eventQueue[i] instanceof WarningEvent)
            {
               if(produce instanceof Wheat)                          
                  plotButton.setIcon(icoWheatWarning);
               else if(produce instanceof Apple)
                  plotButton.setIcon(icoApplesWarning);               
            }
            else if(eventQueue[i] instanceof DeathEvent)
            {
               plotButton.setIcon(icoLandPollutedOwned);               
            }
         }
      } // end of for loop    
   
   } // end of method

   // method that shows and hides all unowned land plots that are currently buyable
   public void showBuyable(boolean visible)
   {
      for(int y=0; y< game.getYSize(); y++)
      {
         for(int x=0; x< game.getYSize(); x++)
         { 
            if(((Controller) game).isAdjacent(x, y))
            {
               if(visible)
               {
                  if(game.isDevelopable(x, y))
                     gameBoardTiles[x][y].setIcon(icoLandBuyable);
                  else
                     gameBoardTiles[x][y].setIcon(icoLandPollutedBuyable);
               }
               else
               {
                  if(game.isDevelopable(x, y))
                     gameBoardTiles[x][y].setIcon(icoLandUnowned);
                  else
                     gameBoardTiles[x][y].setIcon(icoLandPollutedUnowned);
               }
            }
         } // end of x for loops
      } // end y for loop
   } // end of method
   
} // end of class
