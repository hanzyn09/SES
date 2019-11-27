package Ele;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.JFrame;


public class SESControl extends JFrame implements ActionListener {

	// define main windows size (it will assign all other windows and frame size)
	public static int Width = 1760;
	public static int Height = 900;

	public static SES[] ses = null;


	private boolean buttonOrder;

	private static int tempStartFloor;
	private static int tempStopFloor;

	// Each Panel is added in this Frame.
	private static Background drawWin;
	private static StartPlanet aButton;
	private static DestinationPlanet dButton;


	public SESControl () {

		this.setSize(Width, Height);
		this.setTitle("SES");
		this.setLayout(null);
		this.setBackground(new Color(40,35,40));

		tempStartFloor=0;
		tempStopFloor=0;
		buttonOrder = false;


		// Create drawWindow and add in this Frame.
		drawWin = new Background();
		drawWin.setSize(Width, Height);
		this.add(drawWin);

		// Draw Destination Button In This Frame
		dButton = new DestinationPlanet ();
		dButton.setLocation(Width/11 * 9+70 , ((Height / 15 * 2)/ 7)-14);
		this.add(dButton);

		// Draw Arrival Button in this Frame
		aButton = new StartPlanet ();
		aButton.setLocation(0,0);
		this.add(aButton);

		// Set Arrival Button ActionCommand
		for (int i=1 ; i<=8 ; i++) {
			aButton.getStartButton()[i].setActionCommand("aBut"+i);	
			aButton.getStartButton()[i].addActionListener(this);
		}

		// Set Destination Button ActionCommand
		for (int i=1 ; i<=8 ; i++) {
			dButton.getDestinationButton()[i].setActionCommand("dBut"+i);	
			dButton.getDestinationButton()[i].addActionListener(this);
		}

		
		ses = new SES[5];
		for (int i=1 ; i<5 ; i++) {
			ses[i] = new SES(i);
		}

		for (int i=1 ; i<5 ; i++) {
			ses[i].setDrawBackground(drawWin);
		}

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	


	public static synchronized Background getdrawWin () { return drawWin; }

	public static  SES[] getses() { return ses; }

	private synchronized void inputFloor (int ID) {	
		if ( !SESControl.getses()[ID].getAllDestF().contains(tempStartFloor) ) {
			SESControl.getses()[ID].setDestF(tempStartFloor);
		}
		if ( !SESControl.getses()[ID].getAllDestF().contains(tempStopFloor) ) {
			SESControl.getses()[ID].setDestF(tempStopFloor);
		}
	}
	

	
	
	private synchronized int searchSES () {

		int tempID = 0;
		String tempState = null;

		int[] sesID = new int [5];
		int[] sescurrentFloor = new int [5];

		// This loop is for get current floor of all elsesators now.
		for ( int i=1 ; i<5 ; i++) {
			sesID[i] = i;
			sescurrentFloor[i] = ses[i].getcurF();
		}

		/*
		 *	This loop sort elsesator order by short distance from a person.
		 *	Algorithm is based on 'Insertion Sorting Algorithm'.
		 */
		for (int i=2 ; i<ses.length ; i++) {
			int rememberID = sesID[i];
			int remember = sescurrentFloor[i];

			int j = i;
			while (--j >= 1 && Math.abs(tempStartFloor - remember ) < Math.abs(tempStartFloor - sescurrentFloor[j] ) ) {
				sescurrentFloor[j+1] = sescurrentFloor[j];
				sesID[j+1] = sesID[j];
			}
			sesID[j+1] = rememberID;
			sescurrentFloor[j+1] = remember;
		}

		// If User(person) want to go up, tempState will be "UP".
		if (tempStopFloor - tempStartFloor > 0) { tempState = "UP"; }
		// If User(person) want to go up, tempState will be "DOWN".
		else if (tempStopFloor - tempStartFloor < 0) { tempState = "DOWN"; }

		for (int i=1 ; i<5 ; i++) {

				// If User state UP,
				if ( tempState.equals("UP")) {

					// If Elsesator state UP,
					if ( ses[sesID[i]].getSESState().equals("UP")) {

						// If Elsesator going up at the under floor of User's floor,
						if ( ses[sesID[i]].getcurF() < tempStartFloor ) {

							// If Stop floor of Elsesator is more high than User's start floor and stop floor,
							if ( ses[sesID[i]].getDestF() >= tempStartFloor ) {
								tempID = sesID[i];
								inputFloor(tempID);
								Collections.sort(SESControl.getses()[tempID].getAllDestF());
								break;
							}

						}
					}
					// If Elsesator state DOWN,
					else if ( ses[sesID[i]].getSESState().equals("DOWN") ) {

						// If Stop floor of Elsesator is last visit floor,
						if ( ses[sesID[i]].getAllDestF().size() == 1) {

							// If this visit floor equals to floor User's in or different + or - only 1 floor, 
							if ( ses[sesID[i]].getDestF() - tempStartFloor == 0 || Math.abs( ses[sesID[i]].getDestF() - tempStartFloor) == 1 ) {
								tempID = sesID[i];
								inputFloor(tempID);
								break;
							}
						}
					}
					// If Elsesator State DEFAULTUP,
					else if ( ses[sesID[i]].getSESState().equals("DEFAULTUP") ) {

						// If Elsesator going up at the under floor of User's floor,
						if ( ses[sesID[i]].getcurF() < tempStartFloor ) {
							tempID = sesID[i];
							inputFloor(tempID);
							Collections.sort(SESControl.getses()[tempID].getAllDestF());
							break;
						}
					}
					// If Elsesator State STOP,
					else if ( ses[sesID[i]].getSESState().equals("STOP") ) {
						tempID = sesID[i];
						inputFloor(tempID);
						Collections.sort(SESControl.getses()[tempID].getAllDestF());
						break;
					}
				}
				// If User state DOWN,
				else if ( tempState.equals("DOWN")) {

					// If Elsesator State DOWN,
					if ( ses[sesID[i]].getSESState().equals("DOWN") ) {

						// IF Elsesator going down at top of User's floor.
						if ( ses[sesID[i]].getcurF() > tempStartFloor ) {

							// If stop floor of Elsesator is more bottom fo User's start floor and stop floor,
							if ( ses[sesID[i]].getDestF() <= tempStartFloor ) {
								tempID = sesID[i];
								inputFloor(tempID);
								Collections.sort(SESControl.getses()[tempID].getAllDestF());
								Collections.reverse(SESControl.getses()[tempID].getAllDestF());
								break;
							}
						}
					}
					// If Elsesator state UP,
					else if ( ses[sesID[i]].getSESState().equals("UP") ) {

						// If Stop floor of Elsesator is last visit floor,
						if ( ses[sesID[i]].getAllDestF().size() == 1) {

							if ( ses[sesID[i]].getDestF() - tempStartFloor == 0 || Math.abs( ses[sesID[i]].getDestF() - tempStartFloor) == 1 ) {
								tempID = sesID[i];
								inputFloor(tempID);
								break;
							}
						}
					}
						// If Elsesator state DEFAULTDOWN,
					else if ( ses[sesID[i]].getSESState().equals("DEFAULTDOWN") ) {
						// If Elsesator is going down at the bottom of User's floor,
						if ( ses[sesID[i]].getcurF() > tempStartFloor ) {
							tempID = sesID[i];
							inputFloor(tempID);
							Collections.sort(SESControl.getses()[tempID].getAllDestF());
							Collections.reverse(SESControl.getses()[tempID].getAllDestF());
							break;

						}
					}
					// If Elsesator state STOP,
					else if ( ses[sesID[i]].getSESState().equals("STOP") ) {
						tempID = sesID[i];
						inputFloor(tempID);
						Collections.sort(SESControl.getses()[tempID].getAllDestF());
						Collections.reverse(SESControl.getses()[tempID].getAllDestF());
						break;
					}
				}
		}
		return tempID;
	}
	
	


	
	
	
	public void actionPerformed (ActionEvent e) {

		if (e.getActionCommand().substring(0,1).equals("a") ) {
			tempStartFloor=0;

			buttonOrder = true;	// If Start button is clicked before Stop button is clicked, buttonOrder = true.
			for (int i=1 ; i<=8 ; i++) {
				if (e.getActionCommand().equals("aBut"+i)) {
					tempStartFloor = i;
					break;
				}
			}
		}
		else if ( e.getActionCommand().substring(0,1).equals("d") ) {
			tempStopFloor=0;

			// If When stop button is clicked before start button is clicked, it cannot executed.
			if (buttonOrder) {
				for (int i=1 ; i<=8 ; i++) {	
					if (e.getActionCommand().equals("dBut"+i)) {
	
							tempStopFloor = i;
							break;	
	
					}
				}
				// If start floor and stop floor is same, it cannot be executed.
				if ( tempStartFloor - tempStopFloor != 0) {	

					int ID = searchSES ();

					// start elsesator thread when it didn't have choose before,
					if (ses[ID].getSESFlag() == false ) {
						if ( tempStartFloor - ses[ID].getcurF() > 0 ) { 
							ses[ID].setSESState("UP");
						}
						else if (tempStartFloor - ses[ID].getcurF() < 0) {
							ses[ID].setSESState("DOWN");
						}
						else {
							if ( tempStopFloor - ses[ID].getcurF() > 0 ) {
								ses[ID].setSESState("UP");
							}
							else if ( tempStopFloor - ses[ID].getcurF() < 0  ) {
								ses[ID].setSESState("DOWN");
							}
						}
						ses[ID].start();
					}

					buttonOrder = false;
				}
			}
		} 
	}
}
