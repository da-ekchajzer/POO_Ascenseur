package elevator;

import java.util.LinkedHashMap;

import exceptions.NoSuchFloorException;
import floor.Floor;
/**
 * @author david_Ekchajzer, Mathieu_Ridet
 * 
 */

public class RedElevator extends Elevator {

	private static int RedElevatorNumber = 0;
	private static String elevatorColor = "red";
	private static int maxWeight = 1000;
	
 
	public RedElevator(LinkedHashMap<Floor, Integer> reachableFloors) throws NoSuchFloorException {
		super(RedElevator.elevatorColor, RedElevator.maxWeight, ++RedElevatorNumber, reachableFloors);
	}

 
}
