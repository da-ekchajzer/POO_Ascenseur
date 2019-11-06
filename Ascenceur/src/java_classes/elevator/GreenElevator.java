package java_classes.elevator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java_classes.floor.Floor;
import java_classes.user.User;

public class GreenElevator extends Elevator {
	
	private static int elevatorNumber = 0;
	public List<Integer> floorNumbers = new ArrayList<>(Arrays.asList(0, 4, 5, 7, 8, 9));
	
	public GreenElevator(String color, int maxWeight, int elevatorNumber) {
		super("green", 1000, ++elevatorNumber);
		
		for(Integer i : this.floorNumbers) {
			this.passengers.put(new Floor(i, this.getColor()), new ArrayDeque<User>());
		}

	}
	
	

}
