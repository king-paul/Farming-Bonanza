/** @Name: Crop.java
 *  @Author: Paul King
 *  @LastUpadated: 26/10/2014
 *  @Description: The class is a produce type and inherits the Produce class.
 *  Different types of crops iherit from this class. Crops have an age,
 *  maturity times and death times. A variable for the crop's status has also 
 *  been included. The sale value of a crop is returned using the getWorth
 *  method within this class
 */

package model;

public abstract class Crop extends Produce
{   
   // data mambers
   private final int maturity;   
   private final int lifespan;
   private int status;
   private int age;
   
   // public constants
   public static final int IMMATURE = 0;
   public static final int MATURE = 1;
   public static final int WARNING = 2;
   
   // constructor
   public Crop(String name, double costPreparation, double costMaintenance,
               int maturity, int lifespan)
   {
      // pass values to superclass constructor
      super(name, costPreparation, costMaintenance);
      this.maturity = maturity;
      this.lifespan = lifespan;
      age = 0;
   }
   
   // accessors
   public int getMaturity() { return maturity ;}
   public int getLifespan() { return lifespan; }
   public int getStatus() { return status; }
   @Override
   public int getAge() { return age; }
   public final double revenue() { return 0; }
   
   // mutators
   public void setStatus(int status) {
      this.status = status;
   }
   public void mature(){
      age ++;
   }
   
   // returns the value of crops based on the crop sale equation  
   public double getWorth(double baseValue, double multiplier, int month)
   {
      double roundedValue = Math.round((baseValue + multiplier * Math.sin(Math.PI / 12 * month)) *100);
      return roundedValue / 100;
   }
   


}