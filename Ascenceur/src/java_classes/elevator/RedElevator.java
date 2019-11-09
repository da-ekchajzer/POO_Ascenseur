package java_classes.elevator;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import java_classes.floor.Floor;


public class RedElevator extends Elevator {

	private static int elevatorNumber = 0;
	private static String elevatorColor = "red";
	private static int maxWeight = 1000;
	

	public RedElevator(LinkedHashMap<Floor, Integer> reachableFloors) {
		super(RedElevator.elevatorColor, RedElevator.maxWeight, ++RedElevator.elevatorNumber, reachableFloors);
		Dispatcher.getListElevator().get("red").add(this);
	}


}
