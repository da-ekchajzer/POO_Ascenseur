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
	
	
	public static void dispatch() {
		List<Demand> demandsToDelete = new LinkedList<Demand>();
		for (Demand d : demands) {
			if(chooseElevator(d) != null) {
				demandsToDelete.add(d);
			}
		}
		for(Demand d : demandsToDelete) {
			demands.remove(d);
		}
	} 
	
	public static Elevator chooseElevator(Demand d) {
		Elevator choosen = null;
		int distBetweenChoosenAndD = 1000, distBetweenElAndD = 1000;
		
		for(Elevator el : listElevator.get(d.getFloor().getColor())) {
			if(choosen != null) distBetweenChoosenAndD = Math.abs(choosen.getPosition().getFloorNumber()-d.getFloor().getFloorNumber());
			distBetweenElAndD = Math.abs(el.getPosition().getFloorNumber()-d.getFloor().getFloorNumber());
			
			if(el.getDirection() == null) {
				
				if(choosen == null) choosen = el;
				if(choosen != null && choosen.getDirection() == null) {
					if(distBetweenElAndD<distBetweenChoosenAndD) {
						choosen = el;
					}
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
		
		// On déplace l'ascenseur à l'étage de la demande
		/*
		while(choosen.getPosition().getFloorNumber() != d.getFloor().getFloorNumber()) {
			if(d.getFloor().getFloorNumber() > choosen.getPosition().getFloorNumber()) {
				choosen.goUp();
			} else if(d.getFloor().getFloorNumber() < choosen.getPosition().getFloorNumber()) {
				choosen.goDown();
			}
		}*/
		choosen.setPosition(d.getFloor());
		// On remet la direction de l'ascenseur choisi à la direction de la demande
		choosen.setDirection(d.getDirection());
		
		if(choosen != null) {
			for (Entry<String, List<Elevator>> entry : listElevator.entrySet()) {
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
			System.out.println("############# " + choosen.getDirection() + ", " + choosen.getColor() + ", " + 
			choosen.getPosition().getFloorNumber() + ", " + choosen.elevatorNumber);
			return choosen;
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
}
