package elevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import user.Demand;

public class Dispatcher {

	// 15 Nov : Set remplaced by List (to access the elements in the tests)
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
  
	// 15 Nov : revue
	public boolean chooseElevator(Demand d) {
		// Useful to get, each time, the best elevator
		Elevator choosen = null;
		for(Elevator el : this.listElevator.get(d.getFloor().getColor())) {
			/*
			System.out.println("el.getElevatorNumber() : " + el.getElevatorNumber());
			System.out.println("el.getColor() : " + el.getColor());
			System.out.println(el.getDirection() == null ? "" : "el.getDirection() : " + el.getDirection());
			System.out.println("el.position.getFloorNumber() : " + el.position.getFloorNumber());
			System.out.println();
			System.out.println();
			*/
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
				el.getReachableFloors().put(d.getFloor(), 1);
			}
		}
		if(choosen != null) {
			/*
			System.out.println("choosen.getElevatorNumber() : " + choosen.getElevatorNumber());
			System.out.println("choosen.getColor() : " + choosen.getColor());
			*/
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
