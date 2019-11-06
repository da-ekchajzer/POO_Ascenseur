package java_classes.floor;

import java.util.PriorityQueue;
import java_classes.user.User;

public class Floor {
	
	private int floorNumber;
	private String color;
	
	private PriorityQueue<User> usersUp = new PriorityQueue<>();
	private PriorityQueue<User> usersDown = new PriorityQueue<>();

	public Floor(int floorNumber, String color) {
		this.floorNumber = floorNumber;
		this.color = color;
	}

	public int getFloorNumber() {
		return floorNumber;
	}
	
	public String getColor() {
		return color;
	}
	
	public void addUsersUp(User u) {
		this.usersUp.add(u);
	}
	
	public void addUsersDown(User u) {
		this.usersDown.add(u);
	}
	
	public PriorityQueue<User> getUsersUp() {
		return usersUp;
	}

	public PriorityQueue<User> getUsersDown() {
		return usersDown;
	}
}
