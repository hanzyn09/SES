package ses;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * Draw DestinationPlanet Button(Mercury~neptune)
 */
public class DestinationPlanet extends JPanel {

	private int x;
	private static int ButtonHeight;
	private JButton[] DestinationButton;

	public DestinationPlanet () {

		ButtonHeight = 350;

		x = SESControl.Width / 11;

		this.setLayout(null);
		this.setSize(SESControl.Width - (x * 9 + 5) - 20, ButtonHeight); 
		this.setBackground(new Color(40,35,40));

		
		/********************************Image of Destination Planet1~8 Button**************************************/
		DestinationButton = new JButton[9];

		for (int i=1 ; i<=8 ; i++) {
			DestinationButton[i] = new JButton();
			ImageIcon icon = new ImageIcon("image//"+"planet"+i+".png");
			Image ico = icon.getImage();
			Image ic = ico.getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH);
			DestinationButton[i].setIcon(new ImageIcon(ic));
			DestinationButton[i].setBackground(new Color(40,35,40));
			
		}

		/********************************Location of Destination Planet1~8 Button**************************************/
		for (int i=1 ; i<=8 ; i++) {
			if (i<=4) {
				DestinationButton[i].setBounds(80, (4-i)*80, 70, 70);
			}
			else {
				DestinationButton[i].setBounds(150,  (8-i)*80, 70, 70);
			}
			this.add(DestinationButton[i]);
		}

	}

	public static int getPanelHeight () { return ButtonHeight; }
	public JButton[] getDestinationButton () { return DestinationButton; }
}
