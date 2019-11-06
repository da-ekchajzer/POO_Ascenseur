package elevator;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;

import floor.Floor;
import user.User;
	
public class Elevator {
	
	String color;
	Set<Floor> reachableFloor;
	int maxWeight;
	ArrayList<PriorityQueue<User>> passengers;
	String direction;
	Floor position;
	int elevatorNumber;

	public Elevator(String color, Set<Floor> reachableFloor, int maxWeight, int elevatorNumber) {
		this.color = color;
		this.reachableFloor = reachableFloor;
		this.maxWeight = maxWeight;
		//this.elevatorNumber = elevatorNumber; soit le numéro est géré au niveaux de cette classe ou des classes filles
	}
	
	private boolean weightCheck() {
		return false;
	}
	
	public void exit() {
		
	}
	
	public void enter() {
		
	}
	
	private void goUp() {
		
	}
	
	private void goDown() {
	
	public Set<Floor> getReachableFloor() {
		return reachableFloor;
	}

	public void setReachableFloor(Set<Floor> reachableFloor) {
		this.reachableFloor = reachableFloor;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Floor getPosition() {
		return position;
	}

	public void setPosition(Floor position) {
		this.position = position;
	}

	public String getColor() {
		return color;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public ArrayList<PriorityQueue<User>> getPassengers() {
		return passengers;
	}

	public int getElevatorNumber() {
		return elevatorNumber;
	}

}
