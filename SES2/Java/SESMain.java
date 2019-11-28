package ses;

/*
 * Start SES Program
 */
public class SESMain {

	public static SESControl SES = new SESControl();

	public static void main(String[] args) {
		SES.setVisible(true);
	}

	public SESControl getMainWindow () { return SES; }	
}
