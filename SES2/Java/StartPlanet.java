package ses;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
/*
 * Button for StartPlanet (DepartPlanet)
 */
public class StartPlanet extends JPanel {
	private int x;
	private int y;

	private JButton[] StartButton;

	public StartPlanet () {
		
		this.setLayout(null);
		this.setSize(SESControl.Width, 200);
		this.setBackground(new Color(40,35,40));
		
		x = SESControl.Width / 11;
		y = SESControl.Height / 5;
		
		StartButton = new JButton[9];	// Create 8 buttons for each floor.
		
		for (int i=1 ; i<=8 ; i++) {
			StartButton[i] = new JButton();
			ImageIcon icon = new ImageIcon("image//"+"planet"+i+".png");
			Image ico = icon.getImage();
			Image ic = ico.getScaledInstance(150,150,java.awt.Image.SCALE_SMOOTH);
			StartButton[i].setIcon(new ImageIcon(ic));
			StartButton[i].setBackground(new Color(40,35,40));
		}

		for (int i=0 ; i<8 ; i++) {
			StartButton[i+1].setBounds(x*(i+1)+30,0, x, y);
			this.add(StartButton[i+1]);
		}
		
	}

	JButton[] getStartButton () { return StartButton ;} 
}
