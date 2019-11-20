/** @File: LandPlotButton.java
 *  @Author: Paul King
 *  @DateOfVersion: 23/11/2014
 *  @Desciription: This is a class that extends the JButton class
 *  but has additional constants for x and y values. It is used
 *  to go on the game board created in GameBoardPanel.java. 
 */

package view;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class LandPlotButton extends JButton
{
   protected final int X;
   protected final int Y;
   
   public LandPlotButton(int x, int y)
   {
      this.X = x;
      this.Y = y;
   }

}
