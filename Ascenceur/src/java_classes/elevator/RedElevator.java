package java_classes.elevator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import java_classes.floor.Floor;
import java_classes.user.User;

public class RedElevator extends Elevator {

	private static int elevatorNumber = 0;
	private static String elevatorColor = "red";
	private static int maxWeight = 1000;
	

	public RedElevator(ArrayList<Floor> reachableFloors) {
		super(RedElevator.elevatorColor, RedElevator.maxWeight, ++RedElevator.elevatorNumber, reachableFloors);
		RedDispatcher.getListElevator().add(this);
	}


}
