package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.tools.DocumentationTool.Location;

public class ActionPanel extends JPanel {
	JButton respond;
	JButton nextCycle;
	JButton reset;
	
	public ActionPanel(){
		super();
		JLabel title = new JLabel("Actions: ");
		respond = new JButton("Respnd");
		nextCycle = new JButton("Next Cycle");
		reset = new JButton("Reset");
		add(title);
		add(respond);
		add(nextCycle);
		add(reset);
		
		
	}

}
