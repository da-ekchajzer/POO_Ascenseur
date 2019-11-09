package java_classes.elevator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import java_classes.floor.Floor;
import java_classes.user.User;

public class GreenElevator extends Elevator {

	private static int elevatorNumber = 0;
	private static String elevatorColor = "green";
	private static int maxWeight = 1000;
	

	public GreenElevator(ArrayList<Floor> reachableFloors) {
		super(GreenElevator.elevatorColor, GreenElevator.maxWeight, ++GreenElevator.elevatorNumber, reachableFloors);
		GreenDispatcher.getListElevator().add(this);
	}
	
	

}
