/**@File: LandCollection.java
 * @Author: Paul King
 * @DateOfVersion: 26/10/2014
 * @Descirption: This class implements all the abstract methods found in the LandArea
 * interface. Containes constants for the width and the height of the board. Used to
 * calculate maintenance cost, revenue values and crop sale values.
 */
package farmgame;

import interfaces.*;
import model.*;

public class LandCollection implements LandArea
{ 
   
   // data attributes
   private final int xSize;
   private final int ySize;   
   // a 2D array of LandPlots is used to create the gameboard
   private LandPlot[][] gameBoard;
   private int landsOwned;
   
   /* contructor for the farm class. Stores information on the x and y sizes
    * that have been passed as parameters and generates a new game board inside
    *the gameboard 2D array based the on the x and y values
    */
   public LandCollection(int xSize, int ySize)
   {
      this.xSize = xSize;
      this.ySize = ySize;
      gameBoard = new LandPlot[xSize][ySize];
      landsOwned = STARTING_LANDS;
      
      for(int y = 0; y < ySize; y++)
      {
         for(int x = 0; x < xSize; x++)
         {
            LandPlot plot = new LandPlot();
            
            // Note: this algorith only work with even widths and heights
            gameBoard[x][y] = plot;
            
            //System.out.println(plot.getProduce().getName());
            
            if(x >= xSize / 2 -1 && x <= xSize / 2 &&
               y >= ySize / 2 -1 && y <= ySize / 2 )
               gameBoard[x][y].setOwned(true);           
         }
      }
      
   }
   
   // returns a land plot from the gameboard at specified x, y coordinates
   @Override
   public LandPlot getLand(int x, int y)
   {
      return gameBoard[x][y];
   }
   
   // returns the number of lands that the player owns
   public int getLandsOwned()
   {
      return landsOwned;
   }
   
   // returns the name of a produce as a string
   @Override
   public String getProduceName(int x, int y)
   {
      Produce p = gameBoard[x][y].getProduce();
      return p.getName();
   }
   
   // returns the age of a produce
   public int getProduceAge(int x, int y)
   {
      Produce p = gameBoard[x][y].getProduce();
      return p.getAge();
   }
   
   // calculates the total monthly maintenance cost and returns the total
   @Override
   public double getMonthlyCost()
   {      
      double monthlyCost = 0;
      Produce produce;
      
      // iterate through the game board with two for loops
      for(int x=0; x<getXSize(); x++)
      {
         for(int y=0; y<getYSize(); y++)
         {
            if(gameBoard[x][y].isOwned())
            {
               monthlyCost += gameBoard[x][y].getMaintenanceRate();
               
               produce = gameBoard[x][y].getProduce();
               
               // if there is produce on the plot add maintenance cost
               // to total cost
               if(produce != null)
                  monthlyCost += produce.costMaint();
            }
         }
      } // end of loops
      
      return monthlyCost;
   }
   
   // calculates the total revenue collected from livestock and returns the total
   @Override
   public double getMonthlyRevenue()
   {
      double monthlyRevenue = 0;
      
      for(int x=0; x<getXSize(); x++)
      {
         for(int y=0; y<getYSize(); y++)
         {
            // check if there is livestock on the plot
            if(gameBoard[x][y].getProduce() instanceof Livestock)
            {        
               Produce p = gameBoard[x][y].getProduce();
               // increase the total revenue by the revenue of the plot
               monthlyRevenue += p.revenue();
            }
         }
      }
      
      return monthlyRevenue;
   }   

   // changes the status of specified land plot to make it owned, then
   // returns the land cost constant
   @Override
   public double buyLand(int x, int y)
   {
      gameBoard[x][y].setOwned(true);
      landsOwned++;
      return LAND_COST;
   }
   
   // changes the land status to make it not owned, then returns the
   // sale price of land constant
   @Override
   public double sellLand(int x, int y)
   {
      gameBoard[x][y].setOwned(false);
      landsOwned--;
      return gameBoard[x][y].getLandValue();
   }   

   // returns the width of the gameBoard (total columns)
   @Override
   public int getXSize()
   {
      return xSize;
   }

   // returns the height of the gameBoard (total rows)
   @Override
   public int getYSize()
   {
      //  
      return ySize;
   }

   // checks that the x and y coordinates are within the array dimension
   @Override
   public boolean validCoords(int x, int y)
   {      
      if((x >= 0 && x <= xSize -1) && (y >= 0   && y <= ySize -1))
         return true;
      else
         return false;
   } 
   
   /* returns the current sale price of the produce on the land plot. Throws
    * an exception if the land is not owned or has no produce on it. If the
    * produce is a crop and it is immature then the sale value is zero */
   @Override
   public double getProduceWorth(int x, int y)
   {
      int time = GameCalendar.getDate();
      Produce p = gameBoard[x][y].getProduce();        
      
      if(p instanceof Crop)
      {
         if(((Crop) p).getStatus() == Crop.IMMATURE)
            return 0;
      }
      
      // return the sale value of the crop
      return p.getWorth(time); 
   }
}
