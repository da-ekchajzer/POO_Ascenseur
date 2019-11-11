package java_classes.elevator;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import java_classes.floor.Floor;


public class YellowElevator extends Elevator {

	private static int elevatorNumber = 0;
	private static String elevatorColor = "green";
	private static int maxWeight = 1000;
	

	public YellowElevator(LinkedHashMap<Floor, Integer> reachableFloors, Dispatcher d) {
		super(YellowElevator.elevatorColor, YellowElevator.maxWeight, ++YellowElevator.elevatorNumber, reachableFloors);
		d.getListElevator().get("yellow").add(this);
	}

}
