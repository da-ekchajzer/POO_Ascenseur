package Main;

import java.util.LinkedHashMap;

import exceptions.FirstFloorExeption;
import java_classes.elevator.Dispatcher;
import java_classes.elevator.GreenElevator;
import java_classes.elevator.RedElevator;
import java_classes.elevator.YellowElevator;
import java_classes.floor.Floor;

public class SystemInit { 
	public Dispatcher d = new Dispatcher();
	 
	public SystemInit() throws FirstFloorExeption {

		int[] greenFloorsTab = {0, 4, 5, 7, 8, 9 };
		LinkedHashMap<Floor, Integer> reachableFloorG = createCircularFloorList(greenFloorsTab, "green");

		for (int e = 0; e < 6; e++) {
			new GreenElevator(reachableFloorG, d);
		}
 
		int[] yellowFloorsTab = {0, 9, 11, 12, 13, 14, 15, 16 };
		LinkedHashMap<Floor, Integer> reachableFloorY = createCircularFloorList(yellowFloorsTab, "yellow");

		for (int e = 0; e < 6; e++) {
			new YellowElevator(reachableFloorY, d);

		}

		int[] redFloorsTab = {0, 9, 16, 18, 19, 20, 21, 22 };
		LinkedHashMap<Floor, Integer> reachableFloorR = createCircularFloorList(redFloorsTab, "red");

		for (int e = 0; e < 6; e++) {
			new RedElevator(reachableFloorR, d);
		}

	}

	private LinkedHashMap<Floor, Integer> createCircularFloorList(int[] floorsTab, String color)
			throws FirstFloorExeption {
		LinkedHashMap<Floor, Integer> reachableFloors = new LinkedHashMap<>();
		Floor fPrevious = new Floor(floorsTab[0], color);
		fPrevious.setPreviousFloor(null);
		reachableFloors.put(fPrevious, 0);
		Floor fCourant = new Floor(floorsTab[1], color);

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

}
