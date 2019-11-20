/** @Name: Apple.java
 *  @Author: Paul King
 *  @LastUpadated: 26/10/2014
 *  @Description: Apple is a type of crop which is a type of produce.
 *  Constants for the the preparation cost, maintenance cost, maturity
 *  time and death time are recorded here and passed to the Crop super
 *  class. the sale value can also be returned from this class.
 */

package model;

public class Apple extends Crop
{
   // class constants
   private static final double PREP_COST = 500;
   private static final double MAINT_COST = 25;
   private static final int MATURITY = 5;
   private static final int DEATH_TIME = 7;
   
   public Apple() // constructor
   {
      super("Apples", PREP_COST, MAINT_COST, MATURITY, DEATH_TIME);
   }    
   
   // returns the sale value of apples
   public double getWorth(int month)
   {
      return super.getWorth(750, 400, month);
   }
}