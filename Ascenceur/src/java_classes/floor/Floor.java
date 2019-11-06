package java_classes.floor;

import java.util.PriorityQueue;
import java_classes.user.User;

public class Floor {
	
	int floorNumber;
	int color;
	
	public PriorityQueue<User> usersUp = new PriorityQueue<>();
	public PriorityQueue<User> usersDown = new PriorityQueue<>();

	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
	}

}
