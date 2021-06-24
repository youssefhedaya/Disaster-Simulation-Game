package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simulation.Address;
import simulation.Simulatable;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Unit;

public class RescuePanel  extends JPanel{
	private JButton[][] world;
	
	public RescuePanel(JButton[][] world) 
	{
		super(new GridBagLayout());
		this.world = world;
		
		
		
	}
	
	

	
	
}

