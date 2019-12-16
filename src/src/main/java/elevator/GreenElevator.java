package elevator;

import java.util.Map;
import exceptions.NoSuchFloorException;
import floor.Floor;

/**
 * Herite d'Elevator, represente les ascenceurs vert. </br>
 * Definie son propre poids et sa propre surface maximum, numerote les ascenceurs verts
 * @author david_Ekchajzer, Mathieu_Ridet
 */
public class GreenElevator extends Elevator {

	private static int greenElevatorNumber = 0;
	private static String elevatorColor = "green";
	private static int maxWeight = 1000;
	private static int maxSurface = 10;

	public GreenElevator(Map<Floor, Integer> reachableFloors) throws NoSuchFloorException {
		super(GreenElevator.elevatorColor, GreenElevator.maxWeight, GreenElevator.maxSurface, ++greenElevatorNumber, reachableFloors);
	}
}
	 

