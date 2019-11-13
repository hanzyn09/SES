package ses;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class app {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app window = new app();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public app() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		ImagePanel welcomepanel = new ImagePanel(new ImageIcon("C:\\ses\\space2.png").getImage());
		frame.setSize(welcomepanel.getWidth(),welcomepanel.getHeight());
		frame.getContentPane().add(welcomepanel);
		
		//planet1~8(mercury~neptune)
		ImageIcon mercury = new ImageIcon("C:\\ses\\mercury.png");
		Image mercu = mercury.getImage();
		Image merc = mercu.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon m = new ImageIcon(merc);
		JLabel lblNewLabel = new JLabel(m);
		lblNewLabel.setBounds(220,80,100,100);
		welcomepanel.add(lblNewLabel);
		
		ImageIcon venus = new ImageIcon("C:\\ses\\venus.png");
		Image venu = venus.getImage();
		Image ven = venu.getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
		ImageIcon v = new ImageIcon(ven);
		JLabel lblNewLabel2 = new JLabel(v);
		lblNewLabel2.setBounds(390,80,110,110);
		welcomepanel.add(lblNewLabel2);
		
		ImageIcon earth = new ImageIcon("C:\\ses\\earth.png");
		Image eart = earth.getImage();
		Image ear = eart.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
		ImageIcon e = new ImageIcon(ear);
		JLabel lblNewLabel3 = new JLabel(e);
		lblNewLabel3.setBounds(590,80,120,120);
		welcomepanel.add(lblNewLabel3);
		
		ImageIcon mars = new ImageIcon("C:\\ses\\mars.png");
		Image mar = mars.getImage();
		Image ma = mar.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
		ImageIcon m2 = new ImageIcon(ma);
		JLabel lblNewLabel4 = new JLabel(m2);
		lblNewLabel4.setBounds(790,80,130,130);
		welcomepanel.add(lblNewLabel4);
		
		ImageIcon jupiter = new ImageIcon("C:\\ses\\jupiter.png");
		Image jupite = jupiter.getImage();
		Image jupit = jupite.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
		ImageIcon j = new ImageIcon(jupit);
		JLabel lblNewLabel5 = new JLabel(j);
		lblNewLabel5.setBounds(1000,60,160,160);
		welcomepanel.add(lblNewLabel5);
		
		ImageIcon saturn = new ImageIcon("C:\\ses\\saturn.png");
		Image satur = saturn.getImage();
		Image satu = satur.getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH);
		ImageIcon s = new ImageIcon(satu);
		JLabel lblNewLabel6 = new JLabel(s);
		lblNewLabel6.setBounds(1250,80,140,140);
		welcomepanel.add(lblNewLabel6);
		
		ImageIcon uranus = new ImageIcon("C:\\ses\\uranus.png");
		Image uranu = uranus.getImage();
		Image uran = uranu.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
		ImageIcon u = new ImageIcon(uran);
		JLabel lblNewLabel7 = new JLabel(u);
		lblNewLabel7.setBounds(1450,80,130,130);
		welcomepanel.add(lblNewLabel7);
		
		ImageIcon neptune = new ImageIcon("C:\\ses\\neptune.png");
		Image neptun = neptune.getImage();
		Image neptu = neptun.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
		ImageIcon n = new ImageIcon(neptu);
		JLabel lblNewLabel8 = new JLabel(n);
		lblNewLabel8.setBounds(1650,80,130,130);
		welcomepanel.add(lblNewLabel8);
		
		//SpaceElevator1
		ImageIcon spaceship = new ImageIcon("C:\\ses\\spaceship.png");
		Image spaceshi = spaceship.getImage();
		Image spacesh = spaceshi.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
		ImageIcon s1 = new ImageIcon(spacesh);
		JLabel space1 = new JLabel(s1);
		space1.setBounds(10,250,150,150);
		welcomepanel.add(space1);

		//SpaceElevator2
		JLabel space2 = new JLabel(s1);
		space2.setBounds(10,370,150,150);
		welcomepanel.add(space2);
		
		//SpaceElevator3
		JLabel space3 = new JLabel(s1);
		space3.setBounds(10,490,150,150);
		welcomepanel.add(space3);

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class ImagePanel extends JPanel{
	private Image img;
	public ImagePanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setLayout(null);
	}
	public int getWidth() {
		return img.getWidth(null);
	}
	public int getHeight() {
		return img.getHeight(null);
	}
	public void paintComponent(Graphics g) {
		g.drawImage(img,0,0,null);
	}
}
