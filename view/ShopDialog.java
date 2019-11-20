/** @File: ShopDialog.java
 *  @Author: Paul King
 *  @DateOfVersion: 23/11/2014
 *  @Description: This class contructs a dialog box with a radio button
 *  list that asks the player which kind of produce they wish to buy.
 *  Also states the price and maintenance cost of each produce. When
 *  the user clicks on a land plot while the buy produce radio button
 *  is selected this dialog is then displayed.
 */

package view;

// import packages from project
import model.*;
// import java gui packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ShopDialog extends JOptionPane
{
   // data attributes
   private int option;
   private String command = "Wheat";
   
   public ShopDialog() // constructor
   {
      Object options[] = {"Buy Produce", "Cancel"};
      option = JOptionPane.showOptionDialog(null, new ShopPanel(),  
                                            "Radio Test", JOptionPane.OK_CANCEL_OPTION,  
                                            JOptionPane.PLAIN_MESSAGE, null, options, null);
   }
   
   /*************
    * Accessors *
    *************/   
   public int getOptionSelected()
   {
      return option;
   }
   
   public String getProduceSelected()
   {
      return command;
   }
   
   /* Inner class is created which extends Jpanel to place a panel on the dialog
    * box. Radio buttons and labels are placed inside the Shop Panel. */
   private class ShopPanel extends JPanel
   {  
      Produce wheat = new Wheat();  
      Produce apples = new Apple();
      Produce cows = new Cow();
      Produce sheep = new Sheep();
      
      public ShopPanel() // constructor
      {
         // set the properties of the frame      
         this.setSize(400,250);
         this.setLayout(new GridLayout(5,3));
         //this.setLayout(new FlowLayout());
      
         // create the heading lables
         JLabel lblProduce = new JLabel("Produce Type");
         JLabel lblCostPrep = new JLabel("Price per unit");
         JLabel lblCostMaint = new JLabel("Maintenance cost");
         
         // create radio buttons
         JRadioButton rbWheat = new JRadioButton("Wheat");
         rbWheat.setSelected(true); // make this selected by default
         JRadioButton rbApples = new JRadioButton("Apples");
         JRadioButton rbCows = new JRadioButton("Cows");
         JRadioButton rbSheep = new JRadioButton("Sheep");      
         
         // create a radio button group and add the radio buttons to it
         ButtonGroup bgProduce = new ButtonGroup();
         bgProduce.add(rbWheat);
         bgProduce.add(rbApples);      
         bgProduce.add(rbCows);
         bgProduce.add(rbSheep);
         
         // create the price labels
         JLabel lblCostPrepWheat = new JLabel("$"+wheat.costPrep());
         JLabel lblCostPrepApples = new JLabel("$"+apples.costPrep());
         JLabel lblCostPrepCows = new JLabel("$"+cows.costPrep());
         JLabel lblCostPrepSheep = new JLabel("$"+sheep.costPrep());
         
         // create the maintenance cost labels
         JLabel lblCostMaintWheat = new JLabel("$"+wheat.costMaint());
         JLabel lblCostMaintApples = new JLabel("$"+apples.costMaint());
         JLabel lblCostMaintCows = new JLabel("$"+cows.costMaint());
         JLabel lblCostMaintSheep = new JLabel("$"+sheep.costMaint());
         
         // add the components to the shop frame
         this.add(lblProduce);
         this.add(lblCostPrep);
         this.add(lblCostMaint);
         this.add(rbWheat);
         this.add(lblCostPrepWheat);
         this.add(lblCostMaintWheat);
         this.add(rbApples);
         this.add(lblCostPrepApples);
         this.add(lblCostMaintApples);
         this.add(rbCows);
         this.add(lblCostPrepCows);
         this.add(lblCostMaintCows);
         this.add(rbSheep); 
         this.add(lblCostPrepSheep);
         this.add(lblCostMaintSheep);
         
         // create action listener class for the radio buttons
         OptionListener listener = new OptionListener();
         
         // add thelistener to all the radio buttons
         rbWheat.addActionListener(listener);
         rbApples.addActionListener(listener);
         rbCows.addActionListener(listener);
         rbSheep.addActionListener(listener);
         
      } // end of constructor
      
      // inner class that listens to the action of which radio button is selected
      // on the shop dialog
      class OptionListener implements ActionListener
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            command = e.getActionCommand();
         }      
      }
      
   } // end of inner class
   
} // end of class


