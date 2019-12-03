package elevator;

import java.util.LinkedHashMap;

import exceptions.NoSuchFloorException;
import floor.Floor;

/**
 * @author david_Ekchajzer, Mathieu_Ridet
 * 
 */

public class YellowElevator extends Elevator {

	private static int YellowElevatorNumber = 0;
	private static String elevatorColor = "yellow";
	private static int maxWeight = 1000;
	
 
	public YellowElevator(LinkedHashMap<Floor, Integer> reachableFloors) throws NoSuchFloorException {
		super(YellowElevator.elevatorColor, YellowElevator.maxWeight, ++YellowElevatorNumber, reachableFloors);
	}

}
