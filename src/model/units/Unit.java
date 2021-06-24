package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.Collapse;
import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.events.SOSResponder;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Unit implements Simulatable, SOSResponder {
	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	private WorldListener worldListener;

	public Unit(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		this.worldListener = worldListener;
	}

	public void setWorldListener(WorldListener listener) {
		this.worldListener = listener;
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getUnitID() {
		return unitID;
	}

	public Rescuable getTarget() {
		return target;
	}

	public int getStepsPerCycle() {
		return stepsPerCycle;
	}

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}

	
	@Override
	
	public void respond(Rescuable r) throws CannotTreatException, IncompatibleTargetException {
				if (!this.canTreat(r))
					throw new CannotTreatException(this, r ,"The building is safe");
				if (!this.isTreatable(r))
					throw new CannotTreatException(this, r ,"This unit cannot help this building's disaster");
				if (!(r instanceof ResidentialBuilding) )
					throw new IncompatibleTargetException(this, r, "You cannot help the citizen using this type of unit");

				else {
					if (target != null && state == UnitState.TREATING)
						reactivateDisaster();
				finishRespond(r);
		
		}
			
		
	}

	public void reactivateDisaster() {
		Disaster curr = target.getDisaster();
		curr.setActive(true);
	}

	public void finishRespond(Rescuable r)  {
		
		target = r;
		state = UnitState.RESPONDING;
		Address t = r.getLocation();
		distanceToTarget = Math.abs(t.getX() - location.getX())
				+ Math.abs(t.getY() - location.getY());

	}

	public abstract void treat();

	public void cycleStep() {
		if (state == UnitState.IDLE)
			return;
		if (distanceToTarget > 0) {
			distanceToTarget = distanceToTarget - stepsPerCycle;
			if (distanceToTarget <= 0) {
				distanceToTarget = 0;
				Address t = target.getLocation();
				worldListener.assignAddress(this, t.getX(), t.getY());
			}
		} else {
			state = UnitState.TREATING;
			
		}
	}

	public void jobsDone() {
		target = null;
		state = UnitState.IDLE;

	}
	
//	public boolean isStanding(Rescuable r){
//		if( ((ResidentialBuilding) r).getFireDamage()>0 || ((ResidentialBuilding) r).getFoundationDamage()>0 || ((ResidentialBuilding) r).getGasLevel()>0)
//			return true;
//		return false;
//	}
//	public boolean isAlive(Rescuable r){
//		if (((Citizen) r).getToxicity()>0 || ((Citizen) r).getBloodLoss()>0)
//			return true;
//		return false;
//	}
	
	public boolean canTreat(Rescuable r)
	{	 
		if (r instanceof ResidentialBuilding)
			if( ((ResidentialBuilding) r).getFireDamage()>0 || ((ResidentialBuilding) r).getFoundationDamage()>0 || ((ResidentialBuilding) r).getGasLevel()>0)
				return true;
		if (r instanceof Citizen)
			if (((Citizen) r).getToxicity()>0 || ((Citizen) r).getBloodLoss()>0)
				return true;
		return false;
	}
	
	public boolean isTreatable (Rescuable r){
		
		if (!(this instanceof Evacuator) && r.getDisaster() instanceof Collapse)
			return false;
		if (!(this instanceof FireTruck) && r.getDisaster() instanceof Fire)
			return false;
		if (!(this instanceof GasControlUnit) && r.getDisaster() instanceof GasLeak)
			return false;
			
		return true;
		
}
	public String toString() {
		if(!(this  instanceof Evacuator))
		return ("unit ID:" + unitID + "\n" +"Unit type:" + helper() +"\n" + "Unit's location:" + getLocation().toString()
				+ "\n" + "Unit's steps per cycle:" + stepsPerCycle + "\n" + "Units target:" + target +"\n" +
				"unit's state" +state );
		return ("unit ID:" + unitID + "\n" +"Unit type:" + helper() +"\n" + "Unit's location:" + getLocation().toString()
				+ "\n" + "Unit's steps per cycle:" + stepsPerCycle + "\n" + "Units target:" + target +"\n" +
				"unit's state" + state + "\n" + "number of passengers:" + ((Evacuator)this).getPassengers().size() 
				+ "\n" + getpassengersinfo());		
	}
	public String getpassengersinfo() {
		String s=" ";
		for(int i=0;i<((Evacuator)this).getPassengers().size();i++) {
			Citizen c=(  (((PoliceUnit) this).getPassengers().get(i)));
			s=s+c.toString();
		}
		return s;
			
	}
	
	public String helper() {
		if(this instanceof Ambulance)
			return "Ambulance";
		if(this instanceof DiseaseControlUnit)
			return "DiseaseControlUnit";
		if(this instanceof Evacuator)
			return "Evacuator";
		if(this instanceof FireTruck)
			return "FireTruck";
		return null;
		
	}
	
	
	
}
