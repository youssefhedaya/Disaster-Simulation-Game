package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Unit;
import simulation.Simulator;

public class MainWindow  extends JFrame{
	private static InfoPanel infoPanel;
	private static RescuePanel rescuePanel;
	private UnitsPanel unitsPanel;
	private static ActionPanel actionPanel;
	//private JButton[][] world; 
	
	
	
	
	public MainWindow(JButton[][] world){
		// sets title
		setTitle("Rescue Simulation");
		
		// set to fill screen
		
		// set exit to close widow
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	
		rescuePanel= new RescuePanel(world);
		actionPanel = new ActionPanel();
		infoPanel = new InfoPanel();
		getContentPane().add(rescuePanel,BorderLayout.NORTH);
		getContentPane().add(actionPanel,BorderLayout.SOUTH);
		getContentPane().add(infoPanel,BorderLayout.WEST);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setVisible(true);

		
	}




	public static RescuePanel getRescuePanel() {
		return rescuePanel;
	}




	public InfoPanel getInfoPanel() {
		return infoPanel;
	}
}
