package model.disasters;

import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Disaster implements Simulatable{
	private int startCycle;
	private Rescuable target;
	private boolean active;
	public Disaster(int startCycle, Rescuable target) {
		this.startCycle = startCycle;
		this.target = target;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getStartCycle() {
		return startCycle;
	}
	public Rescuable getTarget() {
		return target;
	}
	public void strike() throws CitizenAlreadyDeadException, BuildingAlreadyCollapsedException
	{
		if(getTarget()instanceof ResidentialBuilding && ((ResidentialBuilding) getTarget()).getStructuralIntegrity()<=0) 
			throw new BuildingAlreadyCollapsedException(this);
		if(getTarget()instanceof Citizen && ((Citizen) getTarget()).getState()==CitizenState.DECEASED)
			throw new CitizenAlreadyDeadException(this);
		else
		{
			target.struckBy(this);
			active=true;
		}
	}
	public String toString() {
		if(this instanceof Collapse)
			return "Collapse";
		if(this instanceof GasLeak)
			return "Gas Leak";
		if(this instanceof Fire)
			return "Fiaaar";
		if(this instanceof Infection)
			return "Infection";
		if(this instanceof Injury)
			return "Injury";
		else
			return "Estana Shway";

	}
}
