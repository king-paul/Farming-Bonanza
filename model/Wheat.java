/** @Name: Wheat.java
 *  @Author: Paul King
 *  @LastUpadated: 26/10/2014
 *  @Description: Wheat is a type of crop which is a type of produce.
 *  Constants for the the preparation cost, maintenance cost, maturity
 *  time and death time are recorded here and passed to the Crop super
 *  class. the sale value can also be returned from this class.
 */

package model;

public class Wheat extends Crop
{
   // class constants
   private static final double PREP_COST = 300;
   private static final double MAINT_COST = 10;
   private static final int MATURITY = 3;
   private static final int LIFESPAN = 6; 

   public Wheat() // constructor
   {
      super("Wheat", PREP_COST, MAINT_COST, MATURITY, LIFESPAN);
   }
   
   // returns the sale value of wheat
   public double getWorth(int month)
   {
      return super.getWorth(300, 200, month);
   }
}