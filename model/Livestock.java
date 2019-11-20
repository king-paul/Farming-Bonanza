/** @Name: Livestock.java
 *  @Author: Paul King
 *  @LastUpadated: 26/10/2014
 *  @Description: This class is a proeuce type and inherits from the Produce class.
 *  Different types of livestock inherit from this class. Livestocks all have different
 *  monthy revenue values. The sale value of livestock is returned using the getWorth
 *  method within this class.
 */

package model;

public abstract class Livestock extends Produce
{
   // data attribute
   private double monthlyRevenue;
   
   // constructor
   public Livestock(String name, double costPreparation, double costMaintenance,
                    double monthlyRevenue)
   {
      super(name, costPreparation, costMaintenance);
      this.monthlyRevenue = monthlyRevenue;
   }
   
   // accessors
   public double revenue() { return monthlyRevenue; }
   public int getAge(){ return 0; }
   
   // calculate the sale value
   public double getWorth(double baseValue, double multiplier, int month)
   {
      return baseValue + multiplier * Math.cos(Math.PI / 12 * month);
   }
   
}
