package controller;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.events.SOSResponder;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Unit;
import simulation.Rescuable;
import simulation.Simulator;
import view.MainWindow;

public class RescueGUI implements ActionListener {
	
	private MainWindow mainWindow;
	private ArrayList<JButton> buildingsB; 
	private ArrayList<JButton> citizensB;
	private ArrayList<JButton> unitsB;
	private ArrayList<ResidentialBuilding> buildings;
	private ArrayList<Citizen> citizens;
	private ArrayList<Unit> units;
	private JButton[][] world;
	
	public RescueGUI(ArrayList<ResidentialBuilding> buildings, ArrayList<Citizen> citizens, ArrayList<Unit> units) throws Exception
	{
		world = new JButton[10][10];
				
		this.buildings = buildings;
		this.citizens = citizens;
		this.units = units;
		mainWindow = new MainWindow(world);
		GridBagConstraints c = new GridBagConstraints();
		for(int i = 0;i<10;i++)
			for(int j = 0;j<10;j++) {
				this.world[i][j] = new JButton(i +","+ j);
				c.gridx = i;
				c.gridy = j;
				 mainWindow.getRescuePanel().add(world[i][j],c);
				 world[i][j].addActionListener(this);
			}
		for(Citizen citizen : citizens) {
			JButton b = world[citizen.getLocation().getX()][citizen.getLocation().getY()];
			b.setText(" Citizen ");
		}
		
		for(ResidentialBuilding building : buildings) {
			JButton b = world[building.getLocation().getX()][building.getLocation().getY()];
			b.setText("Building");
				
		}
		
		for (Unit unit: units){
			JButton b = world[unit.getLocation().getX()][unit.getLocation().getY()];
			if (b.getText()!=null)
				b.setText(b.getText()+" " +unit.helper() + " ");
			else 
				b.setText(unit.helper()+ " ");
		}
		
	}

	

	
	public void actionPerformed(ActionEvent e) {
		ResidentialBuilding r = buildings.get(0);
		//String f = "";
		//System.out.println("hi");
		JButton b = (JButton)e.getSource();	
		System.out.print(r.tooString());
		//JLabel info = new JLabel(r.toString());
		b.setText("hi 2");
		//this.mainWindow.getInfoPanel().add(info);
		b.setText("hi 3");
//String s= "new string";
//		
//		for (int i = 0;i<10;i++){
//			for(int j = 0;j<10;j++){
//				JButton b2 = world[i][j];
//				if(b.equals(b2))
//				{
//					
//					if (b.getText().equals("Building")){
//						b.setText("hiiiii");
//						JLabel info1 = new JLabel(s);
//						this.mainWindow.getInfoPanel().add(info1);
//						
//					}
//					//}
//			}
//			}
//		}	
	}
	
	
	
	public String findSimulatable(int x,int y){
		for (ResidentialBuilding building : buildings)			
			if (building.getLocation().getX()== x && building.getLocation().getY()== y)
				return building.toString() ;
		return null;
			
	}
	
	
//	public static void main(String[] args) throws Exception {
//		RescueGUI test = new RescueGUI();
//	}

}
