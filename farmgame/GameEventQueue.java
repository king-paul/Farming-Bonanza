package farmgame;
/**
 * A linked list-based priority queue customised for the storage of game incident events.<p>
 *
 * This queue is "lazy" in that it does not delete events once they have been retrieved, nor
 * does it provide a facility to do so.
 *
 * @version 1.0
 * @author  Peter Tilmanis
 */

import java.util.Vector;

import interfaces.*;

public class GameEventQueue implements Scheduler
{
   // reference to start of list
   private GameEventNode head;
   
   /**
    * Creates the priority queue.
    */
   public GameEventQueue()
   {
      head = null;
   }
   
   /**
    * Add a game incident event to the priority queue.
    *
    * @param   input    the game incident event to add.
    */
   public void addEvent(GameEvent input)
   {
      GameEventNode newNode = new GameEventNode(input);
      
      // if list is empty, add to start
      if (head == null)
      {
         head = newNode;
      }
      // otherwise, find the place to add
      else
      {
         // temp references to move about with
         GameEventNode previous;
         GameEventNode current;
         
         // first set current to head
         current = head;
         previous = null;
         
         // cycle through until the position is found
         while ( (current != null) && (current.getTime() < newNode.getTime()) )
         {
            previous = current;
            current = current.getNext();
         }
         
         // establish the links to fit the new data in
         newNode.setNext(current);
         
         if (previous != null)
         {
            previous.setNext(newNode);
         }
         else
         {
            head = newNode;
         }
      }
   }

   
   /**
    * Returns all the events scheduled to take effect at a particular time.
    *
    * @param   time     The time to retrieve events for.
    * @return           An array of all events to take effect at that time.
    */
   @SuppressWarnings({ "rawtypes", "unchecked" })
   public GameEvent[] getEvents(int time)
   {
      // start processing at the head
      GameEventNode current = head;
      
      // create a temporary vector to hold intermediate information
      Vector temp = new Vector();
      
      // continue processing while neither the target time has passed, nor
      // the end of list reached
      while ((current != null) && (current.getTime() <= time))
      {
         // if there is a time match, add the event to the vector.
         if (current.getTime() == time)
            temp.addElement(current.getEvent());

         // move to the next node
         current = current.getNext();
      }
      
      // create a result array of the appropriate size
      GameEvent[] result = new GameEvent[temp.size()];
      
      // run through the temporary vector...
      for (int i = 0; i < temp.size(); i++)
      {
         // ... and add its contents to the array, one by one.
         result[i] = (GameEvent) temp.elementAt(i);
      }
      
      // return the result array
      return result;
   }
   
}
