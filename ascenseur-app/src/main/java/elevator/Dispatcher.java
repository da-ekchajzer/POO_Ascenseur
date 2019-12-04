package elevator;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import floor.Floor;
import user.Demand;
 
public class Dispatcher {

	private static Map<String, List<Elevator>> listElevator = new HashMap<>();
	private static Set<Demand> demands = new LinkedHashSet<Demand>();
	
	public static boolean elevatorComing(Demand d) {
		for(Elevator el : listElevator.get(d.getFloor().getColor())) {
			if(el.getDirection() != null && el.getDirection().equals("up")) {
				if(el.getTarget() != null && el.getTarget().equals("down")) {
					if(d.getDirection().equals("down")) {
						return true;
					}
				} else if(el.getTarget() != null && el.getTarget().equals("up")) {
					if(d.getDirection().equals("up") 
							&& el.getPosition().getFloorNumber() <= d.getFloor().getFloorNumber()) {
						return true;
					}
				}
			} else if(el.getDirection() != null && el.getDirection().equals("down") 
					&& d.getDirection().equals("down")
					&& el.getPosition().getFloorNumber() >= d.getFloor().getFloorNumber()) {
				return true;
			}
		}
		return false;
	}
	
	
	public static Elevator chooseElevator(Demand d) {
		for(Elevator el : listElevator.get(d.getFloor().getColor())) {
			if(el.getDirection() == null) {
				return el;
			}
		}
		return null;
	}

	
	public static void dispatch() {
		for(Demand d : demands) {
			if(!elevatorComing(d)) {
				Elevator el = chooseElevator(d);
				if(el == null) {
					continue;
				}
				el.setDirection("up");
				if(d.getDirection().equals("down")) {
					el.setTarget("down");
				}
			}
			
		}
	}
	
	public static Map<String, List<Elevator>> getListElevator() {
		return listElevator;
	}

	public static void addDemand(Demand d) {
		demands.add(d);
	}
	
	public static Set<Demand> getDemands() {
		return demands;
	}


	public static boolean isDemanded(Elevator el) {
		for(Demand d : demands) {
			if(el.getPosition().equals(d.getFloor())) {
				if(el.getDirection() != null && el.getDirection().equals(d.getDirection())) {
					return true;
				}
			}
		}
		return false;
	}


	public static boolean isDemandUpper(Elevator el) {
		for(Demand d : demands) {
			if(d.getDirection().equals("down") && d.getFloor().getFloorNumber() >= el.getPosition().getFloorNumber()) {
				return true;
			}
		}
		return false;
	}
}
