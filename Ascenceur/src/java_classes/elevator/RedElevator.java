package java_classes.elevator;

import java.util.LinkedHashMap;

import java_classes.floor.Floor;


public class RedElevator extends Elevator {

	private static int elevatorNumber = 0;
	private static String elevatorColor = "red";
	private static int maxWeight = 1000;
	

	public RedElevator(LinkedHashMap<Floor, Integer> reachableFloors, Dispatcher d) {
		super(RedElevator.elevatorColor, RedElevator.maxWeight, ++RedElevator.elevatorNumber, reachableFloors);
		d.getListElevator().get("red").add(this);
	}

 
}
