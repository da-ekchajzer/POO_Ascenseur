package java_classes.elevator;

import java.util.Map;
import java.util.Set;

import java_classes.floor.Floor;
import java_classes.user.Demand;

public abstract class Dispatcher{
	
	private static Map<String, Set<Elevator>> listElevator;
	//liste des demandes stocké, si une demande ne peut pas être traité on la laisse pour la prochaine itération
	private static Set<Demand> demands;

	
	public static void dispatch() {
		//TODO : for each demand in demands chooseElevator if no elevator avaible wait for next sequence
		//tu peux utilisé direction = none quand l'asceur à pas d'instruction
	}
	
	public static Elevator chooseElevator(String direction, Floor source) {
		Elevator choosen = null;
		for(Elevator el : listElevator.get(source.getColor())) {
			if(el.getDirection() == direction && el.getPosition().getFloorNumber() < source.getFloorNumber()) {
				choosen = el;
			}
		}
		return choosen;
	}
	

	public static Map<String, Set<Elevator>> getListElevator() {
		return listElevator;
	}
	
	public static void addDemand(Demand d) {
		demands.add(d);
	}
}
