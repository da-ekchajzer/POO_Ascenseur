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
	
	public static void dispatch() {
		Elevator choosen;
		for(Demand d : demands) {
			choosen = grabNullElevator(d.getFloor().getColor());
			if(choosen != null) {
				addDemandOnChoosen(choosen, d);
			}
		}
	} 


	private static void addDemandOnChoosen(Elevator choosen, Demand d) {
		choosen.getReachableFloors().put(d.getFloor(), 1);
		
	}


	private static Elevator grabNullElevator(String elevatorColor) {
		for(Elevator el : listElevator.get(elevatorColor)) {
			if (el.getPosition() == null) {
				return el;
			}

		}
		return null;
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
