package elevator;

import java.util.LinkedHashMap;

import floor.Floor;


public class RedElevator extends Elevator {

	private static int RedElevatorNumber = 0;
	private static String elevatorColor = "red";
	private static int maxWeight = 1000;
	
 
	public RedElevator(LinkedHashMap<Floor, Integer> reachableFloors, Dispatcher d) {
		super(RedElevator.elevatorColor, RedElevator.maxWeight, ++RedElevatorNumber, reachableFloors);
	}

 
}
