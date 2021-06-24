package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GeneralPanel extends JPanel {
	
	
	public GeneralPanel(int currentCycle, int numberOfCasualties){
	super();
	JLabel general = new JLabel("General information");
	this.add(general);

//	JLabel NumberOfCasualties = new JLabel("Number of casualties: " + getNumberOfCasualties());
//	this.add(NumberOfCasualties);
//	
//	JLabel CurrentCycle = new JLabel("Current cycle: ") + getCurrentCyce();
//	this.add(CurrentCycle);
	}
}
