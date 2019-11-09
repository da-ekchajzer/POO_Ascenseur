package java_classes.floor;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
import java_classes.user.User;

public class Floor {
	
	private int floorNumber;
	private String color;
	private Floor previousFloor;
	private Floor nextFloor;
	private PriorityQueue<User> usersUp = new PriorityQueue<>();
	private PriorityQueue<User> usersDown = new PriorityQueue<>();
	private static Set<Floor> floors = new HashSet<>();

	public Floor(int floorNumber, String color) {
		this.floorNumber = floorNumber;
		this.color = color;
		floors.add(this);
	}

	public int getFloorNumber() {
		return floorNumber;
	}
	
	public Floor getnextFloor() throws LastFloorExeption{
		if(this.nextFloor == null) {
			throw new LastFloorExeption("...");
		}
		return this.nextFloor;
	}
	
	public Floor getPreviousFloor() throws FirstFloorExeption{
		if(this.previousFloor == null) {
			throw new FirstFloorExeption("...");
		}
		return this.previousFloor;
	}
	
	
	public String getColor() {
		return color;
	}
	
	public static Set<Floor> getFloors() {
		return floors;
	}
	
	public void setPreviousFloor(Floor previousFloor) {
		this.previousFloor = previousFloor;
	}

	public void setNextFloor(Floor nextFloor) {
		this.nextFloor = nextFloor;
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
