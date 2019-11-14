package ses;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class app {

	private JFrame frame;

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

	public app() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//background
		frame = new JFrame();
		ImagePanel welcomepanel = new ImagePanel(new ImageIcon(this.getClass().getResource("/space2.png")).getImage());
		frame.setSize(welcomepanel.getWidth(),welcomepanel.getHeight());
		frame.getContentPane().add(welcomepanel);
		
		//planet1~8(mercury~neptune)
		ImageIcon mercury = new ImageIcon(this.getClass().getResource("/mercury.png"));
		Image mercu = mercury.getImage();
		Image merc = mercu.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon m = new ImageIcon(merc);
		JLabel lblNewLabel = new JLabel(m);
		lblNewLabel.setBounds(220,80,100,100);
		welcomepanel.add(lblNewLabel);
		
		ImageIcon venus = new ImageIcon(this.getClass().getResource("/venus.png"));
		Image venu = venus.getImage();
		Image ven = venu.getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
		ImageIcon v = new ImageIcon(ven);
		JLabel lblNewLabel2 = new JLabel(v);
		lblNewLabel2.setBounds(390,80,110,110);
		welcomepanel.add(lblNewLabel2);
		
		ImageIcon earth = new ImageIcon(this.getClass().getResource("/earth.png"));
		Image eart = earth.getImage();
		Image ear = eart.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
		ImageIcon e = new ImageIcon(ear);
		JLabel lblNewLabel3 = new JLabel(e);
		lblNewLabel3.setBounds(590,80,120,120);
		welcomepanel.add(lblNewLabel3);
		
		ImageIcon mars = new ImageIcon(this.getClass().getResource("/mars.png"));
		Image mar = mars.getImage();
		Image ma = mar.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
		ImageIcon m2 = new ImageIcon(ma);
		JLabel lblNewLabel4 = new JLabel(m2);
		lblNewLabel4.setBounds(785,80,130,130);
		welcomepanel.add(lblNewLabel4);
		
		ImageIcon jupiter = new ImageIcon(this.getClass().getResource("/jupiter.png"));
		Image jupite = jupiter.getImage();
		Image jupit = jupite.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
		ImageIcon j = new ImageIcon(jupit);
		JLabel lblNewLabel5 = new JLabel(j);
		lblNewLabel5.setBounds(1000,70,160,160);
		welcomepanel.add(lblNewLabel5);
		
		ImageIcon saturn = new ImageIcon(this.getClass().getResource("/saturn.png"));
		Image satur = saturn.getImage();
		Image satu = satur.getScaledInstance(140, 140, java.awt.Image.SCALE_SMOOTH);
		ImageIcon s = new ImageIcon(satu);
		JLabel lblNewLabel6 = new JLabel(s);
		lblNewLabel6.setBounds(1250,80,140,140);
		welcomepanel.add(lblNewLabel6);
		
		ImageIcon uranus = new ImageIcon(this.getClass().getResource("/uranus.png"));
		Image uranu = uranus.getImage();
		Image uran = uranu.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
		ImageIcon u = new ImageIcon(uran);
		JLabel lblNewLabel7 = new JLabel(u);
		lblNewLabel7.setBounds(1450,80,130,130);
		welcomepanel.add(lblNewLabel7);
		
		ImageIcon neptune = new ImageIcon(this.getClass().getResource("/neptune.png"));
		Image neptun = neptune.getImage();
		Image neptu = neptun.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
		ImageIcon n = new ImageIcon(neptu);
		JLabel lblNewLabel8 = new JLabel(n);
		lblNewLabel8.setBounds(1650,80,130,130);
		welcomepanel.add(lblNewLabel8);
		
		//SpaceElevator1
		ImageIcon spaceship = new ImageIcon(this.getClass().getResource("/spaceship.png"));
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
		
		//inputbox(mercury~neptune)
		JTextField text1 = new JTextField();
		text1.setBounds(245,40,50,20);
		welcomepanel.add(text1);
		
		JTextField text2 = new JTextField();
		text2.setBounds(420,40,50,20);
		welcomepanel.add(text2);
		
		JTextField text3 = new JTextField();
		text3.setBounds(625,40,50,20);
		welcomepanel.add(text3);

		JTextField text4 = new JTextField();
		text4.setBounds(830,40,50,20);
		welcomepanel.add(text4);

		JTextField text5 = new JTextField();
		text5.setBounds(1055,35,50,20);
		welcomepanel.add(text5);
		
		JTextField text6 = new JTextField();
		text6.setBounds(1290,40,50,20);
		welcomepanel.add(text6);
		
		JTextField text7 = new JTextField();
		text7.setBounds(1490,40,50,20);
		welcomepanel.add(text7);
		
		JTextField text8 = new JTextField();
		text8.setBounds(1690,40,50,20);
		welcomepanel.add(text8);
		
		
		//name(mercury~neptune)
		JButton button1 = new JButton("Mercury");
		button1.setBackground(Color.ORANGE);
		button1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		button1.setBounds(220,180,100,30);
		welcomepanel.add(button1);
		
		JButton button2 = new JButton("Venus");
		button2.setBackground(Color.ORANGE);
		button2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		button2.setBounds(400,190,80,30);
		welcomepanel.add(button2);
		
		JButton button3 = new JButton("Earth");
		button3.setBackground(Color.ORANGE);
		button3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		button3.setBounds(611,209,80,30);
		welcomepanel.add(button3);
		
		JButton button4 = new JButton("Mars");
		button4.setBackground(Color.ORANGE);
		button4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		button4.setBounds(807,219,80,30);
		welcomepanel.add(button4);
		
		JButton button5 = new JButton("Jupiter");
		button5.setBackground(Color.ORANGE);
		button5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		button5.setBounds(1035,245,85,30);
		welcomepanel.add(button5);
		
		JButton button6 = new JButton("Saturn");
		button6.setBackground(Color.ORANGE);
		button6.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		button6.setBounds(1280,219,85,30);
		welcomepanel.add(button6);
		
		JButton button7 = new JButton("Uranus");
		button7.setBackground(Color.ORANGE);
		button7.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		button7.setBounds(1475,209,85,30);
		welcomepanel.add(button7);
		
		JButton button8 = new JButton("Uranus");
		button8.setBackground(Color.ORANGE);
		button8.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		button8.setBounds(1675,222,85,30);
		welcomepanel.add(button8);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

//background image(space2.png)
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
