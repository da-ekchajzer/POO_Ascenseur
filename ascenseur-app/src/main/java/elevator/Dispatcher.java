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
	
	public static void dispatch() throws NoSuchDirection {
		Elevator choosen;
		Set<Demand> NotTreatedDemands = new LinkedHashSet<Demand>();
		for(Demand d : demands) {
			choosen = grabNullElevator(d.getFloor().getColor());
			if(choosen != null) {
				addDemandOnChoosen(choosen, d);
			} else {
				choosen = chooseNearestElevator(d);
				if(choosen == null) {
					NotTreatedDemands.add(d);
				} else {
					choosen.getReachableFloors().put(d.getFloor(), 1);
				}
			}
				
		}
		demands = NotTreatedDemands;
	} 


	private static Elevator chooseNearestElevator(Demand d) {
		int distBetweenChoosenAndD = 1000;
		int distBetweenElAndD;
		Elevator choosen = null;
		
		for(Elevator el : listElevator.get(d.getFloor().getColor())) {
			if(choosen != null) 
				distBetweenChoosenAndD = Math.abs(choosen.getPosition().getFloorNumber()-d.getFloor().getFloorNumber());
			
			distBetweenElAndD = Math.abs(el.getPosition().getFloorNumber()-d.getFloor().getFloorNumber());
			 
			if(el.getDirection().equals(d.getDirection())
					&&
					el.getNbfloors() == 0 &&
					(((el.getDirection().equals("up"))
							&& el.getPosition().getFloorNumber() <= d.getFloor().getFloorNumber()
							&& distBetweenElAndD<distBetweenChoosenAndD)
					||
					((el.getDirection().equals("down"))
							&& el.getPosition().getFloorNumber() >= d.getFloor().getFloorNumber()
							&& distBetweenElAndD<distBetweenChoosenAndD))) {
				choosen = el;
			}		
		}
		return choosen;
	}


	private static void addDemandOnChoosen(Elevator choosen, Demand d) {
		choosen.getReachableFloors().put(d.getFloor(), 1);

		if(d.getDirection() == "up") {
			if(choosen.getPosition().getFloorNumber() > d.getFloor().getFloorNumber()) {
				choosen.setDirection("down");
				choosen.setNbfloors(getNbFloorToReachDemand(choosen, d));
			}else if(choosen.getPosition().getFloorNumber() <= d.getFloor().getFloorNumber()){
				choosen.setDirection("up");
			}
		}
		
		else if(d.getDirection() == "down") {
			if(choosen.getPosition().getFloorNumber() < d.getFloor().getFloorNumber()) {
				choosen.setDirection("up");
				choosen.setNbfloors(getNbFloorToReachDemand(choosen, d));

			}else if(choosen.getPosition().getFloorNumber() >= d.getFloor().getFloorNumber()){
				choosen.setDirection("down");
			}
		}
	}
		

	private static int getNbFloorToReachDemand(Elevator choosen, Demand d) {
		int cmpt = 0;
		Floor f = choosen.getPosition();
		while(!f.equals(d.getFloor())){
			if(d.getFloor().getFloorNumber() < choosen.getPosition().getFloorNumber()) {
				f = f.getPreviousFloor();
			}else if(d.getFloor().getFloorNumber() > choosen.getPosition().getFloorNumber()) {
				f = f.getNextFloor();
			}
			cmpt++;
		}
		
		return(cmpt);
	}


	private static Elevator grabNullElevator(String elevatorColor) {
		for(Elevator el : listElevator.get(elevatorColor)) {
			if (el.getDirection() == null) {
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
