package main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import elevator.Dispatcher;
import elevator.Elevator;
import elevator.GreenElevator;
import elevator.RedElevator;
import elevator.YellowElevator;
import exceptions.NoSuchFloorException;
import floor.Floor;
/**
 * @author david_Ekchajzer, Mathieu_Ridet
 * 
 */
public class SystemInit { 
	   
	/**
	 * @throws NoSuchFloorException
	 * Initialise le système (Elevators, Floors, Dispatcher)
	 */
	public SystemInit() throws NoSuchFloorException{
		
		Dispatcher.getListElevator().put("green", new ArrayList<Elevator>());
		Dispatcher.getListElevator().put("yellow", new ArrayList<Elevator>());
		Dispatcher.getListElevator().put("red", new ArrayList<Elevator>());
		
		int[] greenFloorsTab = {0, 4, 5, 7, 8, 9 };
		LinkedList<Floor> reachableFloorG = createCircularFloorList(greenFloorsTab, "green");

		for (int e = 0; e < 6; e++) {
			Elevator el = new GreenElevator(reachableFloorG);
			Dispatcher.getListElevator().get("green").add(el);
		}
 
		int[] yellowFloorsTab = {0, 9, 11, 12, 13, 14, 15, 16 };
		LinkedList<Floor> reachableFloorY = createCircularFloorList(yellowFloorsTab, "yellow");

		for (int e = 0; e < 6; e++) {
			Elevator el = new YellowElevator(reachableFloorY);
			Dispatcher.getListElevator().get("yellow").add(el);
		}

		int[] redFloorsTab = {0, 9, 16, 18, 19, 20, 21, 22 };
		LinkedList<Floor> reachableFloorR = createCircularFloorList(redFloorsTab, "red");

		for (int e = 0; e < 6; e++) {
			Elevator el = new RedElevator(reachableFloorR);
			Dispatcher.getListElevator().get("red").add(el);
		}

	}


	/**
	 * @param floorsTab
	 * @param color
	 * @return Crée une liste circulaire d'étage qui se suivent
	 */
	public LinkedList<Floor> createCircularFloorList(int[] floorsTab, String color) {
		LinkedList<Floor> reachableFloors = new LinkedList<>();
		Floor fPrevious = new Floor(floorsTab[0], color);
		fPrevious.setPreviousFloor(null);
		reachableFloors.add(fPrevious);  
		Floor fCourant = new Floor(floorsTab[1], color);
		fPrevious.setNextFloor(fCourant);
		
		for (int i = 2; i < floorsTab.length; i++) {
			fCourant.setPreviousFloor(fPrevious);
			Floor fNext = new Floor(floorsTab[i], color);
			fCourant.setNextFloor(fNext);
			reachableFloors.add(fCourant);
			fPrevious = fCourant;
			fCourant = fNext;
		}

		fCourant.setPreviousFloor(fPrevious);
		fCourant.setNextFloor(null);
		reachableFloors.add(fCourant);
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
	
	

}
