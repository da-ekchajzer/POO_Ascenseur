package main;

import java.util.LinkedHashMap;

import elevator.Dispatcher;
import elevator.Elevator;
import elevator.GreenElevator;
import elevator.RedElevator;
import elevator.YellowElevator;
import floor.Floor;

public class SystemInit { 
	public Dispatcher d = new Dispatcher();
	  
	public SystemInit(){
		
		int[] greenFloorsTab = {0, 4, 5, 7, 8, 9 };
		LinkedHashMap<Floor, Integer> reachableFloorG = createCircularFloorList(greenFloorsTab, "green");

		for (int e = 0; e < 6; e++) {
			Elevator el = new GreenElevator(reachableFloorG, d);
			d.getListElevator().get("green").add(el);
		}
 
		int[] yellowFloorsTab = {0, 9, 11, 12, 13, 14, 15, 16 };
		LinkedHashMap<Floor, Integer> reachableFloorY = createCircularFloorList(yellowFloorsTab, "yellow");

		for (int e = 0; e < 6; e++) {
			Elevator el = new YellowElevator(reachableFloorY, d);
			d.getListElevator().get("yellow").add(el);
		}

		int[] redFloorsTab = {0, 9, 16, 18, 19, 20, 21, 22 };
		LinkedHashMap<Floor, Integer> reachableFloorR = createCircularFloorList(redFloorsTab, "red");

		for (int e = 0; e < 6; e++) {
			Elevator el = new RedElevator(reachableFloorR, d);
			d.getListElevator().get("red").add(el);
		}

	}

	// 15 Nov : has to be public for the tests
	public LinkedHashMap<Floor, Integer> createCircularFloorList(int[] floorsTab, String color) {
		LinkedHashMap<Floor, Integer> reachableFloors = new LinkedHashMap<>();
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
//		for(Floor f : reachableFloors.keySet()) {
//			System.out.println(f.getnextFloor().getFloorNumber());
//		}
		return (reachableFloors);
	}

}
