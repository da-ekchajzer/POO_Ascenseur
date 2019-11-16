package elevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import user.Demand;

public class Dispatcher {

	private Map<String, List<Elevator>> listElevator;
	private LinkedList<Demand> demands;

	public Dispatcher() {
		this.listElevator =  new HashMap<>();
		this.listElevator.put("green", new ArrayList<Elevator>());
		this.listElevator.put("yellow", new ArrayList<Elevator>());
		this.listElevator.put("red", new ArrayList<Elevator>());
	}
	
	
	public void dispatch() {
		for (int index = 0; index < this.demands.size(); index++) {
			if(chooseElevator(this.demands.get(index))) {
				this.demands.remove(index);
			}
		}
	}
  
	public boolean chooseElevator(Demand d) {
		Elevator choosen = null;
		for(Elevator el : this.listElevator.get(d.getFloor().getColor())) {
			if(el.getDirection().equals("none") || el.getDirection().equals(d.getDirection())
					&&
					((el.getDirection().equals("up")
							&& el.getPosition().getFloorNumber() < d.getFloor().getFloorNumber()
							&& (choosen == null || choosen.getPosition().getFloorNumber() < el.getPosition().getFloorNumber()))
							||
							(el.getDirection().equals("down")
									&& el.getPosition().getFloorNumber() > d.getFloor().getFloorNumber()
									&& (choosen == null || choosen.getPosition().getFloorNumber() > el.getPosition().getFloorNumber())))) {
				choosen = el;
			}
		}
		if(choosen != null) {
			choosen.getReachableFloors().put(d.getFloor(), 1);
			return true;
		}
		return false;
	}


	public Map<String, List<Elevator>> getListElevator() {
		return this.listElevator;
	}

	public void addDemand(Demand d) {
		this.demands.add(d);
	}
	
	public List<Demand> getDemands() {
		return this.demands;
	}
}
