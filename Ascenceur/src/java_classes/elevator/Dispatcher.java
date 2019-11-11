package java_classes.elevator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import java_classes.user.Demand;

public class Dispatcher {

	private Map<String, Set<Elevator>> listElevator;
	private LinkedList<Demand> demands;

	public Dispatcher() {
		this.listElevator =  new HashMap<>();
		this.listElevator.put("green", new HashSet<Elevator>());
		this.listElevator.put("yellow", new HashSet<Elevator>());
		this.listElevator.put("red", new HashSet<Elevator>());
	}
	
//
//	public static void dispatch() {
//		for (int index = 0; index < demands.size(); index++) {
//			if(chooseElevator(demands.get(index))) {
//				demands.remove(index);
//			}
//		}
//	}
//  
//	public static boolean chooseElevator(Demand d) {
//		for(Elevator el : listElevator.get(d.getFloor().getColor())) {
//			if(el.getDirection() == "none" ||
//					((el.getDirection() == d.getDirection() == "up") && el.getPosition().getFloorNumber() < d.getFloor().getFloorNumber()) ||
//					((el.getDirection() == d.getDirection == "down") && el.getPosition().getFloorNumber() > d.getFloor().getFloorNumber())) {
//				el.getReachableFloors().put(d.getFloor(), 1);	
//			}
//		}
//	}


	public Map<String, Set<Elevator>> getListElevator() {
		return listElevator;
	}

	public void addDemand(Demand d) {
		demands.add(d);
	}
	
	public LinkedList<Demand> getDemands() {
		return this.demands;
	}
}
