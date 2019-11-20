/** @Name: Produce.java
 *  @Author: Paul King
 *  @LastUpadated: 26/10/2014
 *  @Description: This the top level super class for any produce type that can
 *  be placed on a land plot. Contains data attributes and accessors for the
 *  name, preparation cost and maintenance cost of the produce, all of which
 *  value's are passed up to the contructor in this class. Contains abstract
 *  methods implemented by subclasses.
 */

package model;

public abstract class Produce
{   
   // data attributes
   private final String name;
   private final double costPreparation;
   private final double costMaintenance;
   
   // constructor
   public Produce(String name, double costPreparation, double costMaintenance)
   {
      this.name = name;
      this.costPreparation = costPreparation;
      this.costMaintenance = costMaintenance;
   }
   
   // accessors
   public String getName() { return name; }
   public double costMaint() { return  costMaintenance; }
   public double costPrep() { return  costPreparation; }
  
   // abstract methods
   public abstract double getWorth(int month);
   public abstract double revenue();
   public abstract int getAge();
   
}

