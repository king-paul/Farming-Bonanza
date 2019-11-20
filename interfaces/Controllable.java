/** @Name: Controllable.java
 *  @Author: Paul King
 *  @LastUpdated: 26/10/2014
 *  @Description: This is the Controllable interface containing all the abstract methods
 *  to be implemented by a controller class.
 */

package interfaces;

import farmgame.*;
import model.*;

public interface Controllable
{
   // Constants for the start of the game   
   final double STARTING_MONEY = 2000;
   
   public String getName();
   public double getWorth();   
   public int getXSize();
   public int getYSize();
   public void advanceCalendar() throws BankruptException;
   public GameEvent[] getEventQueue();

   public Produce getProduce(int x, int y);
   public boolean isDevelopable(int x, int y);
   public boolean isOwned(int x, int y);
   public boolean buyLand(int x, int y);
   public boolean recultivate(int x, int y);
   public boolean sellLand(int x, int y);

   public boolean buyProduce(int x, int y, Produce produce);
   public boolean sellProduce(int x, int y);
   
}
