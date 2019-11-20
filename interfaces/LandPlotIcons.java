/** @File: LandPlotIcons.java
 *  @Author: Paul King
 *  @LastUpdated: 21/11/2014
 *  @Description: This is an interface that holds all the icons
 *  used to be place on land plot buttons and stores their
 *  file path.
 */

package interfaces;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public interface LandPlotIcons
{
   // Create image icons for board peices and set their filepath
   final Icon icoLandOwned = new ImageIcon("resources/grass.png"),
              icoLandUnowned = new ImageIcon("resources/dirt.png"),
              icoLandBuyable = new ImageIcon("resources/dirt_buyable.png"),
              icoLandPollutedBuyable = new ImageIcon("resources/dirt_polluted_buyable.png"),
              icoLandPollutedOwned = new ImageIcon("resources/grass_polluted.png"),
              icoLandPollutedUnowned = new ImageIcon("resources/dirt_polluted.png"),
              icoWheatImmature = new ImageIcon("resources/wheat_immature.png"),
              icoWheatMature = new ImageIcon("resources/wheat_mature.png"),
              icoWheatWarning = new ImageIcon("resources/wheat_warning.png"),
              icoApplesImmature = new ImageIcon("resources/apple_tree_ungrown.png"),
              icoApplesMature = new ImageIcon("resources/apple_tree_mature.png"),
              icoApplesWarning = new ImageIcon("resources/apple_tree_warning.png"),
              icoCow = new ImageIcon("resources/cow.png"),
              icoSheep = new ImageIcon("resources/sheep.png");              
}
