package java_classes.elevator;

import java.util.Set;

import java_classes.floor.Floor;

public abstract class Dispatcher {
	
	protected static Set<Elevator> listElevator;

	public static Elevator chooseElevator(String direction, Floor source) {
		Elevator choosen = null;
		for(Elevator el : listElevator) {
			if(el.getDirection() == direction && el.getPosition().getFloorNumber() < source.getFloorNumber()) {
				choosen = el;
			}
		}
		return choosen;
	}
	
	public static Set<Elevator> getListElevator() {
		return listElevator;
	}
}
