package elevator;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import exceptions.NoSuchFloorException;
import floor.Floor;
/**
 * Herite d'Elevator, represente les ascenceur vert définis son propre poid et sa propre surface maximum, numérote les ascenceurs verts
 * @author david_Ekchajzer, Mathieu_Ridet
 */

public class RedElevator extends Elevator {

	private static int RedElevatorNumber = 0;
	private static String elevatorColor = "red";
	private static int maxWeight = 1000;
	
 
	public RedElevator(LinkedHashMap<Floor, Integer> reachableFloors) throws NoSuchFloorException {
		super(RedElevator.elevatorColor, RedElevator.maxWeight, ++RedElevatorNumber, reachableFloors);
	}

 
}
