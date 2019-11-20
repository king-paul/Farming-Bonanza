/** @Name: Cow.java
 *  @Author: Paul King
 *  @LastUpadated: 26/10/2014
 *  @Description: Cow is a type of livestock which is a type of produce.
 *  Constants for the the preparation cost, maintenance cost and monthly
 *  revenue are recorded here and passed to the Livestock super class.
 *  The sale value can also be returned from this class.
 */

package model;

public class Cow extends Livestock
{
   // class constants
   private static final double PREP_COST = 1500;
   private static final double MAINT_COST = 200;
   private static final double REVENUE = 300;
   
   public Cow() // constructor
   {
      super("Cows", PREP_COST, MAINT_COST, REVENUE);
   }
   
   // accessor
   public double revenue()
   {
      return REVENUE;
   }
   
   // returns the sale value of cows
   public double getWorth(int month) {
      return super.getWorth(1000, REVENUE, month);
   }
   
}
