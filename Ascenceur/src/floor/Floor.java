package floor;

import java.util.PriorityQueue;
import user.User;

public class Floor {
	
	int floorNumber;
	public PriorityQueue<User> usersUp = new PriorityQueue<>();
	public PriorityQueue<User> usersDown = new PriorityQueue<>();

	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
	}

}
