package Ele;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
/*
 * Draw Background(Fixed)
 */
public class Background extends JPanel {
	private int tempX, tempY;
	
	Image background;
	Image beam;
	Image mercury;
	Image venus;
	Image earth;
	Image mars;
	Image jupiter;
	Image saturn;
	Image uranus;
	Image neptune;
	
	public Background() {
		tempX = SESControl.Width / 11;
		tempY = SESControl.Height / 11;
	}
	
	public void paint(Graphics g) {
		/************************Draw background, planet, SES***************************/
		
		background =Toolkit.getDefaultToolkit().getImage("image/space2.png");
		beam =Toolkit.getDefaultToolkit().getImage("image/beam.png");
		g.drawImage(background,0,180,1590,800,this);
		
		/**********************planet(mercury~)********************/
		mercury =Toolkit.getDefaultToolkit().getImage("image/planet1.png");
		venus =Toolkit.getDefaultToolkit().getImage("image/planet2.png");
		earth =Toolkit.getDefaultToolkit().getImage("image/planet3.png");
		mars =Toolkit.getDefaultToolkit().getImage("image/planet4.png");
		jupiter =Toolkit.getDefaultToolkit().getImage("image/planet5.png");
		saturn =Toolkit.getDefaultToolkit().getImage("image/planet6.png");
		uranus =Toolkit.getDefaultToolkit().getImage("image/planet7.png");
		neptune =Toolkit.getDefaultToolkit().getImage("image/planet8.png");
		

		/***********************************Moving SES, stop position-> with beam************************/
		for (int i=1 ; i<=4 ; i++) {
			g.drawImage(SESControl.getses()[i].getImage(),SESControl.getses()[i].getcurX(),SESControl.getses()[i].getcurY(), tempY+50, tempY+50, this);
		}
		

	}
}
