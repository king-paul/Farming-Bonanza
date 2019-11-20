/** @File: GameMessage.java
 *  @Author: Paul King
 *  @LastUpdated: 23/11/2014
 *  @Description: This class contains static methods that receive any
 *  messages about events or purchase or sale failures when the user
 *  clicks on a land plot.
 */

package farmgame;

import java.util.ArrayList;

public class GameMessage
{
   // creates an array list to hold multiple messages and a string 
   // variable to hold a single message
   private static ArrayList<String> messages = new ArrayList<String>();
   private static String singleMessage;
   
   /** Accessors **/
   public static String getMessage()
   {
      return singleMessage;
   }
   
   public static String getMessages()
   {
      String temp = "";
      
      for(String message : messages)
      {
         // add the message to the temporary string         
         temp += message +"\n";                
      }
      return temp;
   }
   
   /** Mutators **/
   public static void setMessage(String message)
   {
      singleMessage = message;
   }
   
   public static void addMessage(String message)
   {
      messages.add(message);
   }
   
   // remove all messages from the arraylist
   public static void clearMessages()
   {
      messages.clear();
   }

}
