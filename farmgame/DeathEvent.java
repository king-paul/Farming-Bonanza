/** @Name: DeathEvent.java
 *  @Author: Paul King
 *  @LastUpdated: 16/10/2014
 *  @Description: A DeathEvent is a type of game event that
 *  inherits the GameEvent superclass. It occurs whenever
 *  a crop on a landplot has died.
 */

package farmgame;

import model.*;

public class DeathEvent extends GameEvent
{
   public DeathEvent(int time, LandPlot plot, int x, int y, Produce p)
   {
      super(time, plot, x, y, p);
   }
}
