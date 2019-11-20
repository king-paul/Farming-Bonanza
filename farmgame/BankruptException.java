/**
 * @Name: BankruptException.java
 * @Author: Paul King
 * @LastUpdated: 26/10/2014
 * @Description: An exception class to signal when the player goes bankrupt.
 */

package farmgame;

@SuppressWarnings("serial") // supress all serial warnings
public class BankruptException extends Exception
{
   // add an exception to the exception class
   public BankruptException() // constructor
   {
      super();
   }
   
   // add an exception with a message to the exception class
   public BankruptException(String message)
   {
      super(message);
   }
}
