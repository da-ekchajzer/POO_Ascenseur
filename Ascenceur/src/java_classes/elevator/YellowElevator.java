package java_classes.elevator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import java_classes.floor.Floor;
import java_classes.user.User;

public class YellowElevator extends Elevator {

	private static int elevatorNumber = 0;
	private static String elevatorColor = "green";
	private static int maxWeight = 1000;
	

	public YellowElevator(ArrayList<Floor> reachableFloors) {
		super(YellowElevator.elevatorColor, YellowElevator.maxWeight, ++YellowElevator.elevatorNumber, reachableFloors);
		YellowDispatcher.getListElevator().add(this);
	}

}
