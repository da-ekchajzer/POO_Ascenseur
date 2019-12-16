package main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import elevator.Dispatcher;
import elevator.Elevator;
import elevator.GreenElevator;
import elevator.RedElevator;
import elevator.YellowElevator;
import exceptions.NoSuchFloorException;
import floor.Floor;

/**
 * @author david_Ekchajzer, Mathieu_Ridet
 */
public class SystemInit { 

	private static final String YELLOW_COLOR = "yellow";
	private static final String GREEN_COLOR = "green";
	private static final String RED_COLOR = "red";

	/**
	 * @throws NoSuchFloorException
	 * Initialise le système (Elevators, Floors, Dispatcher)
	 */
	public SystemInit() throws NoSuchFloorException{
		
		Dispatcher.getListElevator().put(GREEN_COLOR, new ArrayList<Elevator>());
		Dispatcher.getListElevator().put(YELLOW_COLOR, new ArrayList<Elevator>());
		Dispatcher.getListElevator().put(RED_COLOR, new ArrayList<Elevator>());
		
		int[] greenFloorsTab = {0, 4, 5, 7, 8, 9 };

		for (int e = 0; e < 6; e++) {
			Elevator el = new GreenElevator(createCircularFloorList(greenFloorsTab, GREEN_COLOR));
			Dispatcher.getListElevator().get(GREEN_COLOR).add(el);
		}
 
		int[] yellowFloorsTab = {0, 9, 11, 12, 13, 14, 15, 16};

		for (int e = 0; e < 6; e++) {
			Elevator el = new YellowElevator(createCircularFloorList(yellowFloorsTab, YELLOW_COLOR));
			Dispatcher.getListElevator().get(YELLOW_COLOR).add(el);
		}

		int[] redFloorsTab = {0, 9, 16, 18, 19, 20, 21, 22 };

		for (int e = 0; e < 6; e++) {
			Elevator el = new RedElevator(createCircularFloorList(redFloorsTab, RED_COLOR));
			Dispatcher.getListElevator().get(RED_COLOR).add(el);
		}

	}


	/**
	 * @param floorsTab
	 * @param color
	 * @return une liste circulaire d'etage qui se suivent
	 */
	public Map<Floor, Integer> createCircularFloorList(int[] floorsTab, String color) {
		LinkedHashMap<Floor, Integer>  reachableFloors = new LinkedHashMap<>();
		Floor fPrevious = new Floor(floorsTab[0], color);
		fPrevious.setPreviousFloor(null);
		reachableFloors.put(fPrevious, 0);  
		Floor fCourant = new Floor(floorsTab[1], color);
		fPrevious.setNextFloor(fCourant);
		
		for (int i = 2; i < floorsTab.length; i++) {
			fCourant.setPreviousFloor(fPrevious);
			Floor fNext = new Floor(floorsTab[i], color);
			fCourant.setNextFloor(fNext);
			reachableFloors.put(fCourant, 0);
			fPrevious = fCourant;
			fCourant = fNext;
		}

		fCourant.setPreviousFloor(fPrevious);
		fCourant.setNextFloor(null);
		reachableFloors.put(fCourant, 0);
		return (reachableFloors);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Floor f : Floor.getFloors()) {
			s.append(f.toString() + " \n");
		}

		for (String color : Dispatcher.getListElevator().keySet()) {
			for (Elevator el : Dispatcher.getListElevator().get(color)) {
				s.append(el.toString() + " \n");
			}
		}
		return(s.toString());
	}

	/**
	 * Vide le systeme (utile dans les classes de tests).
	 */
	public void emptySystem() {
		for(List<Elevator> list : Dispatcher.getListElevator().values()) {
			for(Elevator el : list) {
				el.emptyElevator();
			}
		}
		for(Floor f : Floor.getFloors()) {
			f.emptyUsersDown();
			f.emptyUsersUp();
		}
		Dispatcher.getDemands().clear();
	}
	
	

}
