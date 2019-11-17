package floor;

 
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
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
 

	public static Floor getFloor(int number, String color){
		for(Floor f : floors) {
			if(f.floorNumber == number && f.color.equals(color)) {
				 return f;
			}
		}
		return null;
	}
	
	public int getFloorNumber() {
		return floorNumber;
	}
	
	public Floor getNextFloor() throws LastFloorExeption{
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Floor other = (Floor) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (floorNumber != other.floorNumber)
			return false;
		if (nextFloor == null) {
			if (other.nextFloor != null)
				return false;
		} else if (!nextFloor.equals(other.nextFloor))
			return false;
		if (previousFloor == null) {
			if (other.previousFloor != null)
				return false;
		} else if (!previousFloor.equals(other.previousFloor))
			return false;
		if (usersDown == null) {
			if (other.usersDown != null)
				return false;
		} else if (!usersDown.equals(other.usersDown))
			return false;
		if (usersUp == null) {
			if (other.usersUp != null)
				return false;
		} else if (!usersUp.equals(other.usersUp))
			return false;
		return true;
	}



	
}
