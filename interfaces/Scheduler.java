/** @Name: Scheduler.java
 *  @Author: Paul King
 *  @LastUpdated: 26/10/2014
 *  @Description: This is the Scheduler interface that contains abstract
 *  methods to be implemented by any class that contains an event queue
 *  for the game events.
 */

package interfaces;

import farmgame.*;

public interface Scheduler
{
   public void addEvent(GameEvent ge);
   public GameEvent[] getEvents(int time);
}
