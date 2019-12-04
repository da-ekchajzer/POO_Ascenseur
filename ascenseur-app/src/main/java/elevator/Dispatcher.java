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

	private static Map<String, List<Elevator>> listElevator = new HashMap<>();
	private static Set<Demand> demands = new LinkedHashSet<Demand>();
	
	public static boolean elevatorComing(Demand d) {
		//TODO: Regarde si un elevator arrive pour une demande précise
		return true;
	}
	
	
	public static Elevator chooseElevator(Demand d) {
		return null;
		//TODO: Prends un elevator null
	}

	
	public static void dispatch() {
		for(Demand d : demands) {
			if(!elevatorComing(d)) {
				Elevator el = chooseElevator(d);
				chooseElevator(d).setDirection("up");
				if(d.getDirection() == "down") {
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
}
