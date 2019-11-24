package elevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import exceptions.FirstFloorException;
import user.Demand;
 
public class Dispatcher {

	private Map<String, List<Elevator>> listElevator;
	private Set<Demand> demands = new LinkedHashSet<Demand>();

	public Dispatcher() {
		this.listElevator =  new HashMap<>();
		this.listElevator.put("green", new ArrayList<Elevator>());
		this.listElevator.put("yellow", new ArrayList<Elevator>());
		this.listElevator.put("red", new ArrayList<Elevator>());
	}
	
	
	public void dispatch() {
		List<Demand> demandsToDelete = new LinkedList<Demand>();
		for (Demand d : this.demands) {
			if(chooseElevator(d) != null) {
				demandsToDelete.add(d);
			}
		}
		for(Demand d : demandsToDelete) {
			this.demands.remove(d);
		}
	} 
  
	
	public Elevator chooseElevator(Demand d) {
		Elevator choosen = null;
		int distBetweenChoosenAndD = 1000, distBetweenElAndD = 1000;
		
		for(Elevator el : this.listElevator.get(d.getFloor().getColor())) {
			if(choosen != null) distBetweenChoosenAndD = Math.abs(choosen.getPosition().getFloorNumber()-d.getFloor().getFloorNumber());
			distBetweenElAndD = Math.abs(el.getPosition().getFloorNumber()-d.getFloor().getFloorNumber());
			
			if(el.getDirection() == null) {
				if(d.getDirection().equals("down") 
						&& el.getPosition().getFloorNumber() >= d.getFloor().getFloorNumber()
						&& (choosen == null 
							|| (distBetweenElAndD<distBetweenChoosenAndD))) {
					choosen = el;
				} else if(d.getDirection().equals("up") 
						&& el.getPosition().getFloorNumber() <= d.getFloor().getFloorNumber()
						&& (choosen == null 
							|| (distBetweenElAndD<distBetweenChoosenAndD))){
					choosen = el;
				}
			}
			else if(el.getDirection().equals(d.getDirection())
					&&
					(((el.getDirection().equals("up"))
							&& el.getPosition().getFloorNumber() <= d.getFloor().getFloorNumber()
							&& (choosen == null 
								|| (distBetweenElAndD<distBetweenChoosenAndD))
					||
					((el.getDirection().equals("down"))
							&& el.getPosition().getFloorNumber() >= d.getFloor().getFloorNumber()
							&& (choosen == null 
								|| (distBetweenElAndD<distBetweenChoosenAndD)))))) {
				choosen = el;
			}
		}
		if(choosen != null) {
			for (Entry<String, List<Elevator>> entry : this.listElevator.entrySet()) {
				if(entry.getKey() == choosen.getColor()) {
					for(Elevator e : entry.getValue()) {
						if(e.elevatorNumber == choosen.elevatorNumber) {
							if(e.getDirection() == null) {
								if(d.getDirection().equals("up")) {
									e.setDirection("up");
								}else if(d.getDirection().equals("down")) {
									e.setDirection("down");
								}
							}
							e.getReachableFloors().put(d.getFloor(), 1);
						}
					}
				}
			}
			return choosen;
		}
		return null;
	}
	

	public Map<String, List<Elevator>> getListElevator() {
		return this.listElevator;
	}

	public void addDemand(Demand d) {
		this.demands.add(d);
	}
	
	public Set<Demand> getDemands() {
		return this.demands;
	}
}
