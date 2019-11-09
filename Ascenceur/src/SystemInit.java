import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import java_classes.elevator.Dispatcher;
import java_classes.elevator.Elevator;
import java_classes.elevator.GreenElevator;
import java_classes.elevator.RedElevator;
import java_classes.elevator.YellowElevator;
import java_classes.floor.Floor;
import java_classes.user.User;

public class SystemInit {
	
	public SystemInit() {
		// greenElevator
		
		//Creation of passengers Map
		int[] greenFloorsTab = {4,5,7,8,9};
		ArrayList<Floor> passengerG = createCircularFloorList(greenFloorsTab, "green");
		//Creation of GreenElevator
		for (int e = 0; e < 6; e++) {
			Elevator el = new GreenElevator(passengerG);
			//TODO add elevator to the green dispatcheur
		}

		// yellowElevator
		int[] yellowFloorsTab = {9,11,12,13,14,15,16};
		ArrayList<Floor> passengerY = createCircularFloorList(yellowFloorsTab, "yellow");

		for (int e = 0; e < 6; e++) {
			Elevator el = new YellowElevator(passengerY);
			//TODO add elevator to the yellow dispatcheur

		}

		// redElevator
		int[] redFloorsTab = {9,16,18,19,20,21,22};
		ArrayList<Floor> passengerR = createCircularFloorList(redFloorsTab, "green");
		//Creation of RedElevator
		for (int e = 0; e < 6; e++) {
			Elevator el = new RedElevator(passengerR);
			//TODO add elevator to the red dispatcheur

		}

		
	}

	public ArrayList<Floor> createCircularFloorList(int[] floorsTab, String color) {
		ArrayList<Floor> reachableFloors = new ArrayList<>();
		Floor fPrevious = new Floor(floorsTab[0], color);
		fPrevious.setPreviousFloor(null);
		reachableFloors.add(fPrevious);
		Floor fCourant = new Floor(floorsTab[1], color);

		for (int i = 2; i < floorsTab.length ; i++) {
			fCourant.setPreviousFloor(fPrevious);
			Floor fNext = new Floor(floorsTab[i], color);
			fCourant.setNextFloor(fNext);
			reachableFloors.add(fCourant);
			fPrevious = fCourant;
			fCourant = fNext;
		}

		fCourant.setPreviousFloor(fPrevious);
		fCourant.setNextFloor(null);
		reachableFloors.add(fPrevious);
		return(reachableFloors);
	}
}
