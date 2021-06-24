package controller;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import model.events.SOSListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Unit;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulator;
import view.InfoPanel;
import view.MainWindow;
import view.RescuePanel;

public class CommandCenter implements SOSListener, ActionListener {

	private Simulator engine;
	private ArrayList<ResidentialBuilding> visibleBuildings;
	private ArrayList<Citizen> visibleCitizens;
	private RescueGUI view;
	
//	private MainWindow mainwindow;
//	private RescuePanel rescuePanel;
//	private InfoPanel infoPanel;
//	private JButton[][] world; 

	
	@SuppressWarnings("unused")
	private ArrayList<Unit> emergencyUnits;

	public CommandCenter() throws Exception {
		engine = new Simulator(this);
		visibleBuildings = new ArrayList<ResidentialBuilding>();
		visibleCitizens = new ArrayList<Citizen>();
		emergencyUnits = engine.getEmergencyUnits();
		
		view = new RescueGUI(engine.getBuildings(), engine.getCitizens(), engine.getEmergencyUnits());
		
		
//		GridBagConstraints c = new GridBagConstraints();
//		mainwindow  = new MainWindow();
//		rescuePanel = new RescuePanel();
//		mainwindow.getContentPane().add(rescuePanel,BorderLayout.NORTH);
//		world = new JButton[10][10];
		//JButton currentCycle = new JButton("CurrentCycle: " + engine.currentCycle);
		//currentCycle.setActionCommand("nextCycle");
//		c.gridx = 11;
//		c.gridy = 10;
		//rescuePanel.add(currentCycle,c);
//		ResidentialBuilding be = new ResidentialBuilding(new Address(2,2));
//		visibleBuildings.add(be);
//		Citizen ci = new Citizen(new Address(2,2),"43","323",32,null);
//		visibleCitizens.add(ci);
//		infoPanel = new InfoPanel();
//		for(int i = 0;i<10;i++)
//			for(int j = 0;j<10;j++) {
//				world[i][j] = new JButton(i +","+ j);
//				c.gridx = i;
//				c.gridy = j;
//				rescuePanel.add(world[i][j],c);
//			}
//		for(ResidentialBuilding building : visibleBuildings) {
//			JButton b = world[building.getLocation().getX()][building.getLocation().getY()];
//			b.setText("Building");
//			
//		}
//		for(Citizen citizen : visibleCitizens) {
//			JButton b = world[citizen.getLocation().getX()][citizen.getLocation().getY()];
//			b.setText("Citizen");
//
//		}
	}

	public Simulator getEngine() {
		return engine;
	}

	@Override
	public void receiveSOSCall(Rescuable r) {
		if (r instanceof ResidentialBuilding) {	
			if (!visibleBuildings.contains(r))
				visibleBuildings.add((ResidentialBuilding) r);
		} else {
			if (!visibleCitizens.contains(r))
				visibleCitizens.add((Citizen) r);
		}

	}	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println();
		JButton action = (JButton) e.getSource();
		if(action.getActionCommand().equals("nextCyle")) {
		engine.nextCycle();
		//action.setText("current cycle: " + engine.currentCycle);
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		CommandCenter trial = new CommandCenter();
	}
	
	

}
