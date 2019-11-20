/** @File: LandPlot.java
 *  @Author: Paul King
 *  @DateOfVersion: 21/11/2014
 *  @Description: This class is used to serve all purposes relevant to an
 *  individual land plot. Contains the constant values for the land cost,
 *  land salve value, maintenance cost and recultivate cost. Contains
 *  variables to determine wheter the plot is owned, if it is polluted and
 *  what proeuce is occupying it. Contains methods to change the state of
 *  these attributes.
 */

package farmgame;

// import interfaces
import interfaces.*;
// import neccesary package from project
import model.*;

public class LandPlot
{   
   private final double LAND_MAINTENANCE_COST = 20;  
   private final double RECULTIVATE_COST = 300;
   
   // data attibutes
   private boolean owned;
   private boolean polluted;
   private Produce produce;
   
   public LandPlot() // constructor
   {
      owned = false;
      polluted = false;
      produce = null;
   }
   
   // accessors
   public boolean isOwned()
   { 
      return owned; 
   }
   public boolean isPolluted()
   { 
      return polluted; 
   }
   public Produce getProduce()
   {
      return produce;
   }
   public double getMaintenanceRate() {
      return LAND_MAINTENANCE_COST;
   }
   public double getRecultivateCost() {
      return RECULTIVATE_COST;
   }
   
   // mutators
   
   // sets the owned value of a land plot
   public void setOwned(boolean owned) {
      this.owned = owned;
   }

   // adds a produce type to the land plot
   public void addProduce(Produce type) {
      if(produce == null)
         produce = type;
   }  
   
   // calculations
   
   // adds the produce maintenance cost to the land maintenance cost
   // and returns the total
   public double getMonthlyCost() {
      return produce.costMaint() + LAND_MAINTENANCE_COST;
   }
   
   // returns the revnue of the produce on the land plot. If the produce
   // is a crop it will return zero revenue
   public double getMonthlyRevenue() {
      return produce.revenue();
   }
   
   // returns the total value of the land plot
   public double getLandValue() {
      if(produce == null)
         return LandArea.LAND_SALE;
      else
         return LandArea.LAND_SALE +produce.getWorth(GameCalendar.getDate());
   }
  
   // calculates the current sale price of the produce on the land plot
   // if there is no produce it returns zero
   public double getWorth() 
   {
      if(produce instanceof Crop)
         return ((Crop)produce).getWorth(GameCalendar.getDate());
      else if(produce instanceof Livestock)
         return ((Livestock)produce).getWorth(GameCalendar.getDate());
      else
         return 0;
   }

   // removes the produce from the land plot when sold and returns
   // the sale price of it
   public double removeProduce() {
      produce = null;
      return getWorth();
   }
   // removes the the produce from the land plot if there is one
   // pollutes the land
   public boolean destroyProduce() {
      if(produce != null)
      {
         produce = null;
         pollute();
         return true;
      }
      else
      {
         return false;
      }
   }
   // pollutes the land plot
   public void pollute() {
      this.polluted = true;
   }
   // removes polution from land plot returns the recultivate cost constant
   public double recultivate() {
      this.polluted = false;
      return RECULTIVATE_COST;
   }
   
}
