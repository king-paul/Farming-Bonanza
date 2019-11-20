/** @Name: GameCalendar.java
 *  @Author: Paul King
 *  @LastUpdated: 26/10/2014
 *  @Description: This class contains a series of static constants,
 *  variables and methods used controll a game calendar for the
 *  current month. Contains month and year variables and methods to
 *  return the date or move forward one month.
 */
package farmgame;
public class GameCalendar
{
   // constants for the starting year and month names
   private static final int START_YEAR = 2000;
   private static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                                           "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
   // attibutes
   private static int year = START_YEAR;
   private static int month = 0;
   
   // accessors
   public static int getDate() { return month; }
   
   // returns the month and the year in a string represntation
   public static String getDateString() {
      return MONTHS[month%12] + ", " + year;
   }
   
   // moves forward one month
   public static void tickFoward() {
      month ++;
      if(month % 12 == 0)
         year++;
   }
   
}
