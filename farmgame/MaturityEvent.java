/** @Name: MaturityEvent.java
 *  @Author: Paul King
 *  @LastUpdated: 16/10/2014
 *  @Description: A Maturity Event is a type of game event that
 *  inherits the GameEvent superclass. It occurs whenever
 *  a crop on a landplot has matured.
 */

package farmgame;
import model.*;

public class MaturityEvent extends GameEvent
{
   public MaturityEvent(int time, LandPlot plot, int x, int y, Produce p)
   {
      super(time, plot, x, y, p);
   }
   
}
