package ses;

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

		int[] EVID = new int [5];
		int[] EVcurrentFloor = new int [5];

		// This loop is for get current floor of all elevators now.
		for ( int i=1 ; i<5 ; i++) {
			EVID[i] = i;
			EVcurrentFloor[i] = ses[i].getcurF();
		}

		/*
		 *	This loop sort elevator order by short distance from a person.
		 *	Algorithm is based on 'Insertion Sorting Algorithm'.
		 */
		for (int i=2 ; i<ses.length ; i++) {
			int rememberID = EVID[i];
			int remember = EVcurrentFloor[i];

			int j = i;
			while (--j >= 1 && Math.abs(tempStartFloor - remember ) < Math.abs(tempStartFloor - EVcurrentFloor[j] ) ) {
				EVcurrentFloor[j+1] = EVcurrentFloor[j];
				EVID[j+1] = EVID[j];
			}
			EVID[j+1] = rememberID;
			EVcurrentFloor[j+1] = remember;
		}

		// If User(person) want to go up, tempState will be "UP".
		if (tempStopFloor - tempStartFloor > 0) { tempState = "UP"; }
		// If User(person) want to go up, tempState will be "DOWN".
		else if (tempStopFloor - tempStartFloor < 0) { tempState = "DOWN"; }

		for (int i=1 ; i<EVID.length ; i++) {

				// If User state UP,
				if ( tempState.equals("UP")) {

					// 留뚯빟 �뿕�젅踰좎씠�꽣�쓽 �긽�깭媛� UP�씤 寃쎌슦
					// If Elevator state UP,
					if ( ses[EVID[i]].getSESState().equals("UP")) {

						// �뿕�젅踰좎씠�꽣媛� �궡 諛묒뿉�꽌 �삱�씪�삤怨� �엳�뒗 寃쎌슦
						// If Elevator going up at the under floor of User's floor,
						if ( ses[EVID[i]].getcurF() < tempStartFloor ) {

							// �뿕�젅踰좎씠�꽣�쓽 �룄李⑹링�씠 �궡 異쒕컻痢�, �룄李⑹링蹂대떎 �넂��寃쎌슦
							// If Stop floor of Elevator is more high than User's start floor and stop floor,
							if ( ses[EVID[i]].getDestF() >= tempStartFloor ) {
								tempID = EVID[i];
								inputFloor(tempID);
								Collections.sort(SESControl.getses()[tempID].getAllDestF());
								break;
							}

						}
					}
					else if ( ses[EVID[i]].getSESState().equals("DOWN") ) {

						if ( ses[EVID[i]].getAllDestF().size() == 1) {
							// If this visit floor equals to floor User's in or different + or - only 1 floor, 
							if ( ses[EVID[i]].getDestF() - tempStartFloor == 0 || Math.abs( ses[EVID[i]].getDestF() - tempStartFloor) == 1 ) {
								tempID = EVID[i];
								inputFloor(tempID);
								break;
							}
						}
					}
					// If Elevator State DEFAULTUP,
					else if ( ses[EVID[i]].getSESState().equals("DEFAULTUP") ) {
						// If Elevator going up at the under floor of User's floor,
						if ( ses[EVID[i]].getcurF() < tempStartFloor ) {
							tempID = EVID[i];
							inputFloor(tempID);
							Collections.sort(SESControl.getses()[tempID].getAllDestF());
							break;
						}
					}
					// If Elevator State STOP,
					else if ( ses[EVID[i]].getSESState().equals("STOP") ) {
						tempID = EVID[i];
						inputFloor(tempID);
						Collections.sort(SESControl.getses()[tempID].getAllDestF());
						break;
					}
				}
				// If User state DOWN,
				else if ( tempState.equals("DOWN")) {
					// If Elevator State DOWN,
					if ( ses[EVID[i]].getSESState().equals("DOWN") ) {
						// IF Elevator going down at top of User's floor.
						if ( ses[EVID[i]].getcurF() > tempStartFloor ) {
							// If stop floor of Elevator is more bottom fo User's start floor and stop floor,
							if ( ses[EVID[i]].getDestF() <= tempStartFloor ) {
								tempID = EVID[i];
								inputFloor(tempID);
								Collections.sort(SESControl.getses()[tempID].getAllDestF());
								Collections.reverse(SESControl.getses()[tempID].getAllDestF());
								break;
							}
						}
					}
					// If Elevator state UP,
					else if ( ses[EVID[i]].getSESState().equals("UP") ) {

						// If Stop floor of Elevator is last visit floor,
						if ( ses[EVID[i]].getAllDestF().size() == 1) {

							if ( ses[EVID[i]].getDestF() - tempStartFloor == 0 || Math.abs( ses[EVID[i]].getDestF() - tempStartFloor) == 1 ) {
								tempID = EVID[i];
								inputFloor(tempID);
								break;
							}
						}
					}
					// If Elevator state DEFAULTDOWN,
					else if ( ses[EVID[i]].getSESState().equals("DEFAULTDOWN") ) {

						// If Elevator is going down at the bottom of User's floor,
						if ( ses[EVID[i]].getcurF() > tempStartFloor ) {
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
						ses[ID].setSESFlag(true);
						ses[ID].start();
					}

					buttonOrder = false;
				}
			}
		} 
	}
}
