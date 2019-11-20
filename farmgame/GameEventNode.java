package farmgame;

/**
 * A class to wrap a game incident event object with the necessary links to
 * function in a linked list based data structure.
 *
 * @version 1.0
 * @author  Peter Tilmanis
 */
public class GameEventNode
{
   private GameEventNode next;
   private GameEvent data;
   
   /**
    * Create a data structure node to wrap the given event into.
    *
    * @param   data     The game incident event to wrap into a node.
    */
   public GameEventNode(GameEvent data)
   {
      this.data = data;
      this.next = null;
   }
   
   /**
    * Modify the next reference of the node.
    *
    * @param   next     The new next reference to give the node.
    */
   public void setNext(GameEventNode next)
   {
      this.next = next;
   }
   
   /**
    * An accessor for the node's next reference.
    *
    * @return           The next reference of this node.
    */
   public GameEventNode getNext()
   {
      return next;
   }
   
   /**
    * An accessor for the game incident event object that the node is storing.
    *
    * @return           The game incident event object being stored.
    */
   public GameEvent getEvent()
   {
      return data;
   }
   
   /**
    * An accessor for the time at which the game incident event should take place.
    * If no game incident event is being stored, it will return -1.
    *
    * @return           The trigger time for the game incident event being held.
    */
   public int getTime()
   {
      if (data == null)
         return -1;
      else
         return data.getTime();
   }
}
