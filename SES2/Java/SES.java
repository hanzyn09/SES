package ses;
/*
 * Draw SES, Run Threads
 */
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

public class SES extends Thread {


   private int id;              // SES ID
   private Image img;           // Images for SES Elevator
   private String SESState;     // RIGHT, LEFT, 1->RIGHT, 8->LEFT, STOP
   private int firstF;
   private int curF;

   private ArrayList<Integer> DestF;

   private int curX;
   private int curBeamY;
   private int curY;
   private int[][] XY = new int[2][11];   //[X,Y][planet8'x location]
   
   private Background drawBackground;
   private boolean SESFlag;           //represent SES[1~4] is moving(True) or not moving(False)

   public SES() {}

   public SES(int ID) {

      id = ID;
      SESState = "STOP";
      DestF = new ArrayList<Integer>(8);
      img= Toolkit.getDefaultToolkit().getImage("image//spaceship.png");
      
      /*************************************SES's default location is SES1,2-> 1 floor, SES3,4-> 8floor*********************************/
      if (ID == 1 || ID == 2) {
         firstF = 1;
         curF = 1;
      }
      else if (ID == 3 || ID == 4) {
         firstF = 8;
         curF = 8;
      }

      setXY ();

      curX = this.getF()[0][this.getcurF()];
      curY = this.getF()[1][this.getcurF()];

      SESFlag = false;

   }

   
   /*************************************************set default SES's location, planet1~8's location******************************/
   private synchronized void setXY () {
      int wid = SESControl.Width / 10;
      int hei = SESControl.Height / 5;

      int Y=0;

      switch (id) {
      case 1:
         Y = hei * 1 +20;
         break;
      case 2:
         Y = hei * 2;
         break;
      case 3:
         Y = hei * 3;
         break;
      case 4:
         Y = hei * 4-20;
         break;
      }
      for (int i = 8; i >= 1; i--) {
         
         XY[0][i] = wid * i -40;
         XY[1][i] = Y;
      }
   }
   
  /****************************************Update currentFloor***********************************************/
   private void updatecurF () {
      for (int i=1 ; i<=10 ; i++) {
         if (this.getcurX() == this.getF()[0][i]) {
            this.setcurF(i);
         }
      }
   }
   

   
   /*********************************wait a second when arrive planet, destination planet*******************/
   private void waitPlanet() {
      // Open the door.
      for (int i=0 ; i<5 ; i++) {
         setcurBeamY(this.getF()[1][6]);
         this.setSESState("Wait");
         drawBackground.repaint();
         try {
            Thread.sleep(100);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      // Close the door
      for (int i=4 ; i>=0 ; i--) {
          setcurBeamY(this.getF()[1][6]);
          this.setSESState("Wait");
        	  drawBackground.repaint();
        	  
         try {
            Thread.sleep(100);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }

   
   
   
   public void run () {
      int stopplanet=0;

      while (true) {   
    	  
    	  /************************************************Find Start Planet*****************************************************/
         while ( ! DestF.isEmpty() ) {
        	 
        	 /*****************************when SES is Down(go left)**********************/
            if (this.getcurF() - this.getDestF() > 0) {
               this.setSESState("LEFT");

               for ( int i = this.getcurX() ; i >= this.getF()[0][this.getDestF()] ; i--) {
                  this.setcurX(i);

                  drawBackground.repaint();
                  this.updatecurF();
                  try {
                     Thread.sleep(6);
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
            }
            
            /*****************************when SES is Up(go right)**********************/
            else if (this.getcurF() - this.getDestF() < 0) {

               this.setSESState("RIGHT");
               for (int i = this.getcurX() ; i <= this.getF()[0][this.getDestF()] ; i++) {
                  this.setcurX(i);
                  drawBackground.repaint();
                  this.updatecurF();
                  
                  try {
                     Thread.sleep(6);
                  } catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
            }
           
            /*****************************wait on start Planet************************/
            this.waitPlanet();
            this.setSESState("Stop");
            DestF.remove(0);
         }
         
         
         /*******************************************Back to Default Planet(1,2-> 1 floor,   3,4->8floor)**************************************/
         if ( this.getcurF() - this.getfirstF() != 0 ) {

        	 /**************************Down to 1 floor************************/
            if (this.getcurF() - this.getfirstF() > 0) {
               this.setSESState("DEFAULTLEFT");

               for ( stopplanet = this.getcurX() ; stopplanet >= this.getF()[0][this.getfirstF()] ; stopplanet--) {
                  if ( this.getAllDestF().isEmpty() ) {
                     this.setcurX(stopplanet);
                     drawBackground.repaint();
                     this.updatecurF();

                     try {
                        Thread.sleep(6);
                     } catch (InterruptedException e) {
                        e.printStackTrace();
                     }                     
                  }
                  else {
                     break;
                  }   
               }
                  this.setSESState("STOP");
               stopplanet=0;
            }
            
            /**********************************************Go UP*******************************************/
            else if (this.getcurF() - this.getfirstF() < 0) {

               this.setSESState("DEFAULTRIGHT");
               
               for (stopplanet = this.getcurX() ; stopplanet <= this.getF()[0][this.getfirstF()] ; stopplanet++) {

                  if ( this.getAllDestF().isEmpty() ) {
                     
                     this.setcurX(stopplanet);
                     drawBackground.repaint();
                     this.updatecurF();

                     
                     try {
                        Thread.sleep(6);
                     } catch (InterruptedException e) {
                        e.printStackTrace();
                     }
                  }
                  else {
                     break;
                  }
               }
                  this.setSESState("STOP");
             
               stopplanet=0;
            }
         }
      }
   }
   
   
/**********************************GET SET ********************************************/
   public synchronized void setID ( int inputId ) { id = inputId; }

   public synchronized void setSESState (String newState) { SESState = newState; }

	public synchronized void setDrawBackground (Background temp ) { drawBackground = temp; }

   public synchronized void setDestF (int inputDestF ) { DestF.add(inputDestF); }

   public void setfirstF (int inputfirstF) { firstF = inputfirstF; }

   public synchronized void setcurF (int inputcurF) { curF = inputcurF; }

   public synchronized void setcurX (int X) { curX = X; }

   public synchronized void setcurY (int Y) { curY = Y; }
      
   public synchronized void setcurBeamY (int BeamY) { curBeamY = BeamY; }

   public void setSESFlag (boolean flag) { SESFlag = flag; }
   
   
   

   public synchronized int getID () { return id; }

   public synchronized String getSESState () { return SESState; }

   public synchronized int getDestF () { return DestF.get(0); }
   
   public synchronized ArrayList<Integer> getAllDestF () { return DestF; }

   public synchronized int getfirstF () { return firstF; }
 
   public synchronized int getcurF () { return curF; }

   public int getcurX () { return curX; }

   public int getcurY () { return curY; }

   public int getcurBeamY () { return curBeamY; }

   public int[][] getF () { return XY; }

   public Image getImage () { return img; }

   public boolean getSESFlag () { return SESFlag; }

	
}
