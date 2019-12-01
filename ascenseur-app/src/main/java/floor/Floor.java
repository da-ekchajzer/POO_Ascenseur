package floor;

 
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import user.User;

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
 

	public static Floor getFloor(int number, String color) throws NoSuchFloorException{
		for(Floor f : floors) {
			if(f.floorNumber == number && f.color.equals(color)) {
				return f;
			}
		}
		throw new NoSuchFloorException("...");
	}
	 
	public int getFloorNumber() {
		return floorNumber;
	}
	
	public Floor getNextFloor() throws LastFloorException{
		/*
		if(this.nextFloor == null) {
			throw new LastFloorException("...");
		}
		*/
		return this.nextFloor;
	}
	
	public Floor getPreviousFloor() throws FirstFloorException{
		/*
		if(this.previousFloor == null) {
			throw new FirstFloorException("...");
		}
		*/
		return this.previousFloor;
	}
	
	
	public String getColor() {
		return color;
	}
	
	public void setPreviousFloor(Floor previousFloor) {
		this.previousFloor = previousFloor;
	}

	public void setNextFloor(Floor nextFloor) {
		this.nextFloor = nextFloor;
	}

	public static Set<Floor> getFloors() {
		return floors;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + floorNumber;
		return result; 
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Floor) {
			Floor other = (Floor) obj;
			if (this.color.equals(other.color) && this.floorNumber == other.floorNumber) 
				return true;
		}
		return false;
	}

	
}
