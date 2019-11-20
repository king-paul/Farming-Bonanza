/** 
 * NoProduceException.Java
 * @Author Paul King
 * @Description Exception gets thrown when the user selects
 * coordinates that dosn't have any produce type on it
 */

package farmgame;

@SuppressWarnings("serial")
public class NoProduceException extends Exception
{
   // constructor that adds an Exception to the exception class
   public NoProduceException()
   {
      super();
   }
   
   // constructor that adds an exception with a message to the
   // Exception class
   public NoProduceException(String message)
   {
      super(message);
   }   

}
