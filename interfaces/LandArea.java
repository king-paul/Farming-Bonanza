/** @Name: LandArea.java
 *  @Author: Paul King
 *  @LastUpdated: 26/10/2014
 *  @Description: This is the LandArea interface containing all the abstract methods
 *  to be implemented by any class that handles land operations.
 */

package interfaces;

import farmgame.*;

public interface LandArea
{
   final double LAND_COST = 1000;
   final double LAND_SALE = 1000;
   final int STARTING_LANDS = 4;
   
   public LandPlot getLand(int x, int y);
   public int getLandsOwned();
   public double buyLand(int x, int y);
   public double sellLand(int x, int y);
   public double getMonthlyCost();
   public double getMonthlyRevenue();
   public int getXSize();
   public int getYSize();
   public boolean validCoords(int x, int y);
   public String getProduceName(int x, int y);
   public double getProduceWorth(int x, int y);
   public int getProduceAge(int x, int y);
}
