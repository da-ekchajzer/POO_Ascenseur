package java_classes.elevator;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import java_classes.floor.Floor;


public class GreenElevator extends Elevator {

	private static int elevatorNumber = 0;
	private static String elevatorColor = "green";
	private static int maxWeight = 1000;
	

	public GreenElevator(LinkedHashMap<Floor, Integer> reachableFloors) {
		super(GreenElevator.elevatorColor, GreenElevator.maxWeight, ++GreenElevator.elevatorNumber, reachableFloors);
		Dispatcher.getListElevator().get("green").add(this);
	}
	
	

}
