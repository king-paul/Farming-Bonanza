/** @Controller.java
 *  @Author: Paul King
 *  @LastUpdated: 21/11/2014
 *  @Description: This is the controller class which handles many of the basic
 *  processes that take place during the game. Utilizes all methods found within
 *  the Controllable interface.
 */

package farmgame;

import javax.swing.JOptionPane;

import interfaces.*;
import model.*;

public class Controller implements Controllable
{   
   // instances
   private String playerName;
   private double playerWorth = STARTING_MONEY;
   private LandArea farm;
   private LandPlot plot;
   private Scheduler eventQueue = new GameEventQueue();
   
   private final int xSize = 8;
   private final int ySize = 6;
   
   public Controller(String playerName)
   {
      farm = null;
      
      // set the player name to the one passed to the contructor
      this.playerName = playerName;
      // create a new game board based on the width and height constants
      farm = new LandCollection(xSize, ySize);    
      
      /*
      for(int y=0; y<ySize; y++)
      {
         for(int x=0; x<xSize; x++)
         {
            Produce p = farm.getLand(x, y).getProduce();
            
            if(p != null)
               System.out.println(p.getName());
            else
               System.out.println("null");
         }
      }*/
      
   }
   
   /** Accessor Methods **/  
   @Override
   public String getName()
   {
      return playerName;
   }
   @Override
   public double getWorth()
   {
      return playerWorth;
   }
   @Override
   public int getXSize()
   {
      return farm.getXSize();
   }
   @Override
   public int getYSize()
   {
      return farm.getYSize();
   }
   
   public double getMonthlyCost()
   {
      return farm.getMonthlyCost();
   }
   
   public double getMonhtlyRevenue()
   {
      return farm.getMonthlyRevenue();
   }
   
   public GameEvent[] getEventQueue()
   {
      // get events from the monthly event queue and put into array  
      GameEvent[] events = eventQueue.getEvents(GameCalendar.getDate());
      return events;
   }
   
   // increase the calander date by one month and the age of all
   // crops by one month
   @Override
   public void advanceCalendar() throws BankruptException
   {      
      GameCalendar.tickFoward(); // increase the month by 1
      
      // get events from the monthly event queue and put into array  
      GameEvent[] events = getEventQueue();
      
      // decrease the funds by the total maintenance cost of all plots
      playerWorth -= farm.getMonthlyCost();      
      // increase the funds by the total revenue gain from livestock
      playerWorth += farm.getMonthlyRevenue();      
      
      for(int i=0; i< events.length; i++)
      {     
         Produce p = events[i].getProduce();
         
         // get the land plot from specied x and y coordinates
         plot = events[i].getLandPlot();

         // check if the produce is still on the plot
         if(plot.getProduce() == events[i].getProduce())
         {                
            if(events[i] instanceof MaturityEvent)
            {
               // add message of this event
               GameMessage.addMessage("Your "+p.getName()+" at ("+events[i].X+", "+events[i].Y+")" +
                                      " has matured");
               ((Crop) p).setStatus(Crop.MATURE); // change the crop status to MATURE
               
            }
            if(events[i] instanceof WarningEvent)
            {
               GameMessage.addMessage("Your "+p.getName()+" at ("+events[i].X+", "+events[i].Y+")" +
                        " is one month from dying");
               ((Crop) p).setStatus(Crop.WARNING); // change the crop stauts to WARNING
               
            }
            if(events[i] instanceof DeathEvent)
            {
                 
               plot = farm.getLand(events[i].X, events[i].Y);
               
               // remove the produce and pollute the land
               if(plot.destroyProduce())
                  GameMessage.addMessage("Your "+p.getName()+" at ("+events[i].X+", "+events[i].Y+")" +
                                         " has died and the land is now polluted"); 
               else
                  JOptionPane.showMessageDialog(null, "Error: Not Produce was found on this land plot",
                                                "No Produce Error", JOptionPane.ERROR_MESSAGE);
            }
         }
      }

      if(playerWorth < 0)
         throw new BankruptException("You have run out of money.");
      
      if(playerWorth <= 500 && farm.getLandsOwned() <= 1)
         throw new BankruptException("You don't have enough money to buy any more produce and\n" +
                                     "have no land plots to sell");      
   }

   // gets the produce off a specified land plot and returns it
   @Override
   public Produce getProduce(int x, int y)
   {
      // set the land plot to point to the correct index on the gameboard
      plot = farm.getLand(x, y);
      
      Produce produce = plot.getProduce();
      
      return produce;
   }

   // checks if the land is polluted and returns a true or false value
   @Override
   public boolean isDevelopable(int x, int y)
   {
      // set the land plot to point to the correct index on the gameboard
      plot = farm.getLand(x, y);
      
      if(!plot.isPolluted())
         return true;
      else            
         return false;
   }

   // check if a specified plot is currently owned
   @Override
   public boolean isOwned(int x, int y)
   {
      plot = farm.getLand(x, y);
      if(plot.isOwned())
         return true;
      else
         return false;
   }

   public boolean isAdjacent(int x, int y)
   {
      // check if the plot is one the is currently owned
      plot = farm.getLand(x, y);
      if(plot.isOwned())
      {
         return false;
      }
      
      // check if the plot to the left is owned
      if(x > 0 && x < (farm.getXSize() - 1))
      {
         plot = farm.getLand(x+1, y);
         
         if(plot.isOwned())
            return true;
      }
      // check if the plot to the right is owned
      if(x < (farm.getXSize()) && x > 0)
      {
         plot = farm.getLand(x-1, y);
         
         if(plot.isOwned())
            return true; 
      }
      // check if the plot above is owned
      if(y > 0)
      {
         plot = farm.getLand(x, y-1);
         
         if(plot.isOwned()) 
            return true;         
      }
      // check if the plot below is owned
      if(y < farm.getYSize() - 1)
      {
         plot = farm.getLand(x, y+1);
         if(plot.isOwned())
            return true;
          
      }
      
      // the plot is not adjacent to any available
      return false;
   }
   
   /* checks if valid land coordinates are entered, they are adjacent to another
    * owned land, the land is not already owned, it is not polluted and the player
    * has enough money to buy a new plot. If all of the above are met then the owned
    * variable for the land plot is set to true and money is subtracted from the
    * player's balance.
    */
   @Override
   public boolean buyLand(int x, int y)
   { 
      // check if the coordinates are valid
      if(!farm.validCoords(x, y))
      {
         JOptionPane.showMessageDialog(null, "Invalid Coordinates detected. x:"+x+" y:"+y, 
                                       "Error: Invalid Coordinates", JOptionPane.ERROR_MESSAGE);         
         return false;
      }
      
      // set the land plot to point to the correct index on the gameboard
      plot = farm.getLand(x, y);
      
      // check if the land is laready owned
      if(plot.isOwned())
      {
         GameMessage.setMessage("You already own that land plot.");
         return false;
      }
      // check if the player has enough money
      if(playerWorth < LandArea.LAND_COST)
      {
         GameMessage.setMessage("You do not have enough money to buy a land plot.");
         return false;
      }  
      
      // purchase the land plot
      playerWorth -= farm.buyLand(x, y); 
      return true;
   }

   // method is used to recultivate land if it is polluted. Returns false if it is not polluted
   // or the player does not own that land plot
   @Override
   public boolean recultivate(int x, int y)
   {
      // check if the coordinates are valid
      if(!farm.validCoords(x, y))
      {
         JOptionPane.showMessageDialog(null, "Invalid Coordinates detected. x:"+x+" y:"+y, 
                                       "Error: Invalid Coordinates", JOptionPane.ERROR_MESSAGE);  
         return false;
      }
      
      // set the land plot to point to the correct index on the gameboard
      plot = farm.getLand(x, y);      
    
      if(!plot.isOwned())
      {
         GameMessage.setMessage("You can not recultivate a land plot that you do not own");
         return false;
      }
      
      if(!plot.isPolluted())
      {
         GameMessage.setMessage("You can not recultivate a land that is not polluted");
         return false;
      }
      
         playerWorth -= plot.recultivate();           
         return true;
   }

   /* Checks if coordinates are valid and that the land plot is currently owned.
    * If it is then it is sold back fo x dollars. Adjacent land may become
    * isolated without an algorithm to resolve this.
    */
   @Override
   public boolean sellLand(int x, int y)
   {
      int landsOwned = farm.getLandsOwned();      
      
      // check if the coordinates are valid
      if(!farm.validCoords(x, y))
      {
         JOptionPane.showMessageDialog(null, "Error: Invalid coordinats ("+x+","+y, "Invalid coordinats",
                                       JOptionPane.ERROR_MESSAGE);
         return false;
      }
      
      // set the land plot to point to the correct index on the gameboard
      plot = farm.getLand(x, y);
      
      // check that the play is not selling the last land
      if(landsOwned == 1)
      {
         GameMessage.setMessage("You can not sell your last land plot");
         return false;
      }      
      
      // check that the land is currently owned
      if(!plot.isOwned())
      {
         GameMessage.setMessage("You do not own that land plot");
         return false;         
      }      
      else
      {
         // remove ownership of land plot
         plot.setOwned(false);
         
         // pollute the land if there is produce on it when sold
         if(plot.getProduce() != null)
            plot.destroyProduce();
         
         // increase the funds by the land sale value
         playerWorth += farm.sellLand(x, y);
         return true;
      }
         
   }

   /* Attempts to buy a produce and add it to the land. Checks to see that you have enough
    * money, you ownd the land plot, it is not polluted and you don't already have another
    * produce on it.
    */
   @Override
   public boolean buyProduce(int x, int y, Produce produce)
   {
      int month = GameCalendar.getDate();
      
      // check if the coordinates are valid
      if(!farm.validCoords(x, y))
      {
         JOptionPane.showMessageDialog(null, "Invalid Coordinates detected. x:"+x+" y:"+y, 
                                       "Error: Invalid Coordinates", JOptionPane.ERROR_MESSAGE);  
         return false;
      }
      
      // set the land plot to point to the correct index on the gameboard
      plot = farm.getLand(x, y);
      
      if(playerWorth < produce.costPrep())
      {
         GameMessage.setMessage("You don't have enough money for this purchase.");
         return false;
      }
      if(!plot.isOwned())
      {
         GameMessage.setMessage("You can only place produce on land plots that you own");
         return false;
      }
      if(plot.isPolluted())
      {
         GameMessage.setMessage("You must recultive polluted land before you can place produce on it");
         return false;
      }      
      if(plot.getProduce() != null)  
      {
         GameMessage.setMessage("You already have produce on that land plot");
         return false;
      }
      else
      {         
         // subtract the player funds by the produce cost
         playerWorth -=  (Math.round(produce.costPrep() * 100) / 100);
         
         // round off the player funds to 2 decimals
         playerWorth = Math.round(playerWorth * 100);
         playerWorth /= 100;
         
         // add the produce to the plot
         plot.addProduce(produce);
         
         // check if the produce bought was a crop
         if(produce instanceof Crop) 
         {
            // define the maturity, death and warning values
            int maturityMonth = month + ((Crop) produce).getMaturity();
            int deathMonth = month + ((Crop) produce).getLifespan();
            int warningMonth = month + ((Crop) produce).getLifespan() - 1;
            
            // add the maturitym death and warning events for crop to the
            // event queue
            eventQueue.addEvent(new MaturityEvent(maturityMonth, plot, x, y, produce));
            eventQueue.addEvent(new DeathEvent(deathMonth, plot, x, y, produce));
            eventQueue.addEvent(new WarningEvent(warningMonth, plot, x, y, produce));            
         }
         
         return true; // the purchase was successful
      }
         
   }

   /* Checks if there is produce on the land plot and if there ism it calculate the
    * sale price before removing the produce and increasing the funds by the sale price
    */
   @Override
   public boolean sellProduce(int x, int y)
   {      
      // check if the coordinates are valid
      if(!farm.validCoords(x, y))
      {
         JOptionPane.showMessageDialog(null, "Invalid Coordinates detected. x:"+x+" y:"+y, 
                                       "Error: Invalid Coordinates", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      
      // set the land plot to point to the correct index on the gameboard
      plot = farm.getLand(x, y);
      Produce produce = plot.getProduce();
      
      // check if the plot is owned and there is produce on it
      if(plot.isOwned() && produce != null)
      {      
         // increase the player by money by the sale value
         playerWorth += farm.getProduceWorth(x, y); 
         
         // round off the player funds to 2 decimals
         playerWorth = Math.round(playerWorth * 100);
         playerWorth /= 100;
         
         // if the produce is an immature crop, remove and the produce and
         // pollute the landPlot
         if(produce instanceof Crop && ((Crop) produce).getStatus() == Crop.IMMATURE)
            plot.destroyProduce();
         else // otherwise just remove the produce from the land plot
            plot.removeProduce();
            
         return true;
      }
      
      return false;
   }   
   
}

