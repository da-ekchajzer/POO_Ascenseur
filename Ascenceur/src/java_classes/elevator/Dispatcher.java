package java_classes.elevator;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import java_classes.floor.Floor;
import java_classes.user.Demand;

public abstract class Dispatcher {

	private static Map<String, Set<Elevator>> listElevator;
	// liste des demandes stocké, si une demande ne peut pas être traité on la
	// laisse pour la prochaine itération
	private static LinkedList<Demand> demands;

	public static void dispatch() {
		for (int index = 0; index < demands.size(); index++) {
			if(chooseElevator(demands.get(index))) {
				demands.remove(index);
			}
		}
	}

	public static boolean chooseElevator(Demand d) {
		for(Elevator el : listElevator.get(d.getFloor().getColor())) {
			if(el.getDirection() == "none" ||
					((el.getDirection() == d.getDirection() == "up") && el.getPosition().getFloorNumber() < d.getFloor().getFloorNumber()) ||
					((el.getDirection() == d.getDirection == "down") && el.getPosition().getFloorNumber() > d.getFloor().getFloorNumber())) {
				el.getReachableFloors().put(d.getFloor(), 1);	
			}
		}
	}

	public static Map<String, Set<Elevator>> getListElevator() {
		return listElevator;
	}

	public static void addDemand(Demand d) {
		demands.add(d);
	}
}
