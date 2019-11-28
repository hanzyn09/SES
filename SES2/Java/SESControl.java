package ses;
/*
 * Control SES
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.JFrame;


public class SESControl extends JFrame implements ActionListener {
	public static int Width = 1760;
	public static int Height = 900;

	public static SES[] ses = null;


	private boolean request;
	private static int startPlanet;
	private static int DestPlanet;
	private static Background background;
	private static StartPlanet startBtn;
	private static DestinationPlanet DestBtn;


	public SESControl () {

		/*************************Draw Fixed Background(SES, background, buttons)******************************/
		this.setSize(Width, Height);
		this.setTitle("SES");
		this.setLayout(null);
		this.setBackground(new Color(40,35,40));

		background = new Background();
		background.setSize(Width, Height);
		this.add(background);

		DestBtn = new DestinationPlanet ();
		DestBtn.setLocation(Width/11 * 9+70 , ((Height / 15 * 2)/ 7)-14);
		this.add(DestBtn);

		startBtn = new StartPlanet ();
		startBtn.setLocation(0,0);
		this.add(startBtn);

		for (int i=1 ; i<=8 ; i++) {
			startBtn.getStartButton()[i].setActionCommand("start"+i);	
			startBtn.getStartButton()[i].addActionListener(this);
		}

		for (int i=1 ; i<=8 ; i++) {
			DestBtn.getDestinationButton()[i].setActionCommand("dest"+i);	
			DestBtn.getDestinationButton()[i].addActionListener(this);
		}

		ses = new SES[5];
		for (int i=1 ; i<5 ; i++) {
			ses[i] = new SES(i);
		}
		for (int i=1 ; i<5 ; i++) {
			ses[i].setDrawBackground(background);
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		startPlanet=0;
		DestPlanet=0;
		request = false;
	}


	/************************************Set Request Planet***************************/
	private synchronized void inputFloor (int ID) {	
		if ( !SESControl.getses()[ID].getAllDestF().contains(startPlanet) ) {
			SESControl.getses()[ID].setDestF(startPlanet);
		}
		if ( !SESControl.getses()[ID].getAllDestF().contains(DestPlanet) ) {
			SESControl.getses()[ID].setDestF(DestPlanet);
		}
	}
	
	
	/*********************************Find the Nearest SES************************/
	private synchronized int findSES () {

		int tempID = 0;
		String tempState = null;
		int[] EVID = new int [5];
		int[] EVcurrentFloor = new int [5];

		for ( int i=1 ; i<5 ; i++) {
			EVID[i] = i;
			EVcurrentFloor[i] = ses[i].getcurF();
		}

		/*****************************find nearest ses*********************/
		for (int i=2 ; i<ses.length ; i++) {
			int rememberID = EVID[i];
			int remember = EVcurrentFloor[i];

			int j = i;
			while (--j >= 1 && Math.abs(startPlanet - remember ) < Math.abs(startPlanet - EVcurrentFloor[j] ) ) {
				EVcurrentFloor[j+1] = EVcurrentFloor[j];
				EVID[j+1] = EVID[j];
			}
			EVID[j+1] = rememberID;
			EVcurrentFloor[j+1] = remember;
		}

		if (DestPlanet - startPlanet > 0) { tempState = "Right"; }
		else if (DestPlanet - startPlanet < 0) { tempState = "LEFT"; }


		/****************************If a request comes in while moving, same direction-> Accept the request****************/
		for (int i=1 ; i<EVID.length ; i++) {
			/*************if Request is moving Right****************/
				if ( tempState.equals("Right")) {
					/************SES is Moving right || Moving LEFT || Moving Default Right || Stop********************/
					if ( ses[EVID[i]].getSESState().equals("Right")) {
						if ( ses[EVID[i]].getcurF() < startPlanet ) {
							if ( ses[EVID[i]].getDestF() >= startPlanet ) {
								tempID = EVID[i];
								inputFloor(tempID);
								Collections.sort(SESControl.getses()[tempID].getAllDestF());
								break;
							}

						}
					}
					else if ( ses[EVID[i]].getSESState().equals("LEFT") ) {

						if ( ses[EVID[i]].getAllDestF().size() == 1) {
							if ( ses[EVID[i]].getDestF() - startPlanet == 0 || Math.abs( ses[EVID[i]].getDestF() - startPlanet) == 1 ) {
								tempID = EVID[i];
								inputFloor(tempID);
								break;
							}
						}
					}
					else if ( ses[EVID[i]].getSESState().equals("DEFAULTRight") ) {
						if ( ses[EVID[i]].getcurF() < startPlanet ) {
							tempID = EVID[i];
							inputFloor(tempID);
							Collections.sort(SESControl.getses()[tempID].getAllDestF());
							break;
						}
					}
					else if ( ses[EVID[i]].getSESState().equals("STOP") ) {
						tempID = EVID[i];
						inputFloor(tempID);
						Collections.sort(SESControl.getses()[tempID].getAllDestF());
						break;
					}
				}
				

				/*************if Request is moving LEFT****************/
				else if ( tempState.equals("LEFT")) 

					/*************SES is Moving LEFT || Moving RIGHT || Moving Default LEFT || STOP********************/
					if ( ses[EVID[i]].getSESState().equals("LEFT") ) {
						if ( ses[EVID[i]].getcurF() > startPlanet ) {
							if ( ses[EVID[i]].getDestF() <= startPlanet ) {
								tempID = EVID[i];
								inputFloor(tempID);
								Collections.sort(SESControl.getses()[tempID].getAllDestF());
								Collections.reverse(SESControl.getses()[tempID].getAllDestF());
								break;
							}
						}
					}
					else if ( ses[EVID[i]].getSESState().equals("Right") ) {
						if ( ses[EVID[i]].getAllDestF().size() == 1) {

							if ( ses[EVID[i]].getDestF() - startPlanet == 0 || Math.abs( ses[EVID[i]].getDestF() - startPlanet) == 1 ) {
								tempID = EVID[i];
								inputFloor(tempID);
								break;
							}
						}
					}
					else if ( ses[EVID[i]].getSESState().equals("DEFAULTLEFT") ) {
						if ( ses[EVID[i]].getcurF() > startPlanet ) {
							tempID = EVID[i];
							inputFloor(tempID);
							Collections.sort(SESControl.getses()[tempID].getAllDestF());
							Collections.reverse(SESControl.getses()[tempID].getAllDestF());
							break;

						}
					}
					// If Elevator state STOP,
					else if ( ses[EVID[i]].getSESState().equals("STOP") ) {
						tempID = EVID[i];
						inputFloor(tempID);
						Collections.sort(SESControl.getses()[tempID].getAllDestF());
						Collections.reverse(SESControl.getses()[tempID].getAllDestF());
						break;
					}
				}
			
		
		return tempID;
	}
	
	
	/*************************When Request is True, Action*************************/
	
	public void actionPerformed (ActionEvent e) {

		if (e.getActionCommand().substring(0,1).equals("s") ) {
			startPlanet=0;
			request = true;	
			for (int i=1 ; i<=8 ; i++) {
				if (e.getActionCommand().equals("start"+i)) {
					startPlanet = i;
					break;
				}
			}
		}
		else if ( e.getActionCommand().substring(0,1).equals("d") ) {
			DestPlanet=0;
			if (request) {
				for (int i=1 ; i<=8 ; i++) {	
					if (e.getActionCommand().equals("dest"+i)) {
	
							DestPlanet = i;
							break;	
	
					}
				}
				if ( startPlanet - DestPlanet != 0) {	

					int ID = findSES ();
					if (ses[ID].getSESFlag() == false ) {
						if ( startPlanet - ses[ID].getcurF() > 0 ) { 
							ses[ID].setSESState("Right");
						}
						else if (startPlanet - ses[ID].getcurF() < 0) {
							ses[ID].setSESState("LEFT");
						}
						else {
							if ( DestPlanet - ses[ID].getcurF() > 0 ) {
								ses[ID].setSESState("Right");
							}
							else if ( DestPlanet - ses[ID].getcurF() < 0  ) {
								ses[ID].setSESState("LEFT");
							}
						}
						ses[ID].setSESFlag(true);
						ses[ID].start();
					}

					request = false;
				}
			}
		} 
	}
	


	public static synchronized Background getbackground () { return background; }
	public static  SES[] getses() { return ses; }
}
