/** @Name: Sheep.java
 *  @Author: Paul King
 *  @LastUpadated: 26/10/2014
 *  @Description: Sheep is a type of livestock which is a type of produce.
 *  Constants for the the preparation cost, maintenance cost and monthly
 *  revenue are recorded here and passed to the Livestock super class.
 *  The sale value can also be returned from this class.
 */

package model;

public class Sheep extends Livestock
{
   // class constants
   private static final double PREP_COST = 1300;
   private static final double MAINT_COST = 150;
   private static final double REVENUE = 200;
   
   public Sheep() // constructor
   {
      super("Sheep", PREP_COST, MAINT_COST, REVENUE);
   }
   
   // accessor
   public double revenue()
   {
      return REVENUE;
   }
   
   // returns the sale value of sheep
   public double getWorth(int month) {
      return super.getWorth(800, 300, month);
   }   
}
