package java_classes.elevator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import exceptions.UnreachableFloor;
import java_classes.floor.Floor;
import java_classes.user.User;
	
public class Elevator {
	
	private String color;
	private int maxWeight;
	private int currentWeight = 0;
	protected LinkedHashMap<Floor, ArrayDeque<User>> passengers;
	private String direction;
	Floor position;
	int elevatorNumber;
	List<Integer> floorNumbers = null;

	public Elevator(String color, int maxWeight, int elevatorNumber) {
		this.color = color;
		this.maxWeight = maxWeight;
		//this.elevatorNumber = elevatorNumber; soit le numéro est géré au niveaux de cette classe ou des classes filles
	}

	
	private boolean weightCheck(User u) {
		if(this.currentWeight+u.getWeight() <= this.maxWeight) {
			this.currentWeight += u.getWeight();
			return true;
		} else {
			return false;
		}
	}
		
	public void enter() throws UnreachableFloor {
		if(this.direction == "up") {
			this.floorToElevator(this.position.getUsersUp());
		} else {
			this.floorToElevator(this.position.getUsersDown());
		}
	}
	
	public void floorToElevator(PriorityQueue<User> pq) throws UnreachableFloor {
		while(!pq.isEmpty()) {
			User u = pq.peek();
			if(!this.floorNumbers.contains(u.getDestination())){
				pq.poll();
				throw new UnreachableFloor("...");
			}
			if(!this.weightCheck(u)) break; 
			
			u = pq.poll();
			ArrayDeque<User> arr = this.passengers.get(u.getDestination());
			arr.add(u);
			this.passengers.put(u.getDestination(), arr);
		}
	}
	
	


	public void exit() {
		
	}
	
	
	
	private void goUp() {
		
	}
	
	private void goDown() {
		
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

	public LinkedHashMap<Floor, ArrayDeque<User>> getPassengers() {
		return passengers;
	}

	public int getElevatorNumber() {
		return elevatorNumber;
	}

}
