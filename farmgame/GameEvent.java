/** @Name: GameEvent.java
 *  @Author: Paul King
 *  @LastUpdated: 16/10/2014
 *  @Description: This is the super class for all types of game events.
 *  A game event occurs whenever a crop matures, is one month away from
 *  dying or has died. The time, landplot and produce type is passed to
 *  this class.
 */

package farmgame;

import model.*;

public abstract class GameEvent
{
   // data arrtibutes
   private int time;
   private Produce p;
   LandPlot plot;
   
   public final int X;
   public final int Y;
   
// constructor
   public GameEvent(int time, LandPlot plot, Produce p)
   {
      this.time = time;
      this.plot = plot;
      this.p = p;
      X = 0;
      Y = 0;
   }

   // constructor   
   public GameEvent(int time, LandPlot plot, int x, int y, Produce p)
   {
      this.time = time;
      this.plot = plot;
      this.p = p;      
      X = x;
      Y = y;
   }
   
   // accessors
   public int getTime() { return time; }
   public Produce getProduce() { return p; }
   public LandPlot getLandPlot() { return plot; }
   
}
