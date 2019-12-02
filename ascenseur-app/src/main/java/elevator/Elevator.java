package elevator;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import floor.Floor;
import user.User;

public abstract class Elevator {
  
	private String color;
	private int maxWeight;
	private int currentWeight = 0;
	protected LinkedHashMap<User, Floor> passengers;
	protected LinkedHashMap<Floor, Integer> reachableFloors;
	private String direction;
	private Floor position;
	protected int elevatorNumber;
 
	public Elevator(String color, int maxWeight, int elevatorNumber, LinkedHashMap<Floor, Integer> reachableFloors) throws NoSuchFloorException {
		this.color = color;
		this.maxWeight = maxWeight;
		this.elevatorNumber = elevatorNumber;
		this.passengers = new LinkedHashMap<User, Floor>();
		this.reachableFloors = reachableFloors;
		this.direction = null;
		this.position = Floor.getFloor(0, color);
	}
  
	private boolean weightCheck(User u) {
		if (this.currentWeight + u.getWeight() <= this.maxWeight) {
			this.currentWeight += u.getWeight();
			return true;
		} else {
			return false;
		}
	}

	public void enter() throws UnreachableFloor {
		if(this.direction == null) {
		}
		else if (this.direction.equals("up")) {
			this.floorToElevator(this.position.getUsersUp());
		} else {
			this.floorToElevator(this.position.getUsersDown());
		}
	} 

	public void floorToElevator(PriorityQueue<User> pq) throws UnreachableFloor {
		while (!pq.isEmpty()) {
			User u = pq.peek();
			if (!this.reachableFloors.containsKey(u.getDestination())) {
				pq.poll();
				//Destroy or reput in the system
				throw new UnreachableFloor("...");
			}
			if (!this.weightCheck(u)) {
				while(pq.peek().getPMR()) {
					this.exitWhenPMR(pq);
					if(!this.weightCheck(u)) {
						continue;
					} else {
						this.addPassengersToElevator(pq);
					}
				}
				break;
			}

			this.addPassengersToElevator(pq);
		}
	}

	
	private void addPassengersToElevator(PriorityQueue<User> pq) {
		User u = pq.poll();
		this.passengers.put(u, u.getDestination());
	}
	
	
	private void exitWhenPMR(PriorityQueue<User> pq) {
			User firstP = (User) this.passengers.keySet().toArray()[0];
			if(firstP.getDestination().getFloorNumber() < this.position.getFloorNumber()) {
				this.position.addUsersDown(firstP);
			}else{
				this.position.addUsersUp(firstP);
			}
			this.passengers.remove(firstP);
	}
	
	
	// ConcurrentModificationException ici car on parcourt une collection et on suppr des éléments en mm tmps.
	// Solution : Iterator
	public void exit() throws NoSuchFloorException, FirstFloorException, LastFloorException {
		//for(User u : this.passengers.keySet()) {
		for(Iterator<User> userIterator = this.passengers.keySet().iterator(); userIterator.hasNext();) {
			User u = userIterator.next();
			if(u.getDestination() == this.position) {
				//this.passengers.remove(u);
				userIterator.remove();
				if(!u.isFinalDestination()) {
					if(u.getFinalDestination().getFloorNumber() != u.getDestination().getFloorNumber()) {
						u.makeChangement();
						u.callElevator();
					}
					
				}
			}
		}
	}

	public void goUp() throws LastFloorException {
		if(this.position.getNextFloor() == null) {
			this.direction = null;
		} else {
			this.position = this.position.getNextFloor();
			this.direction = "up";
		}
		
	}

	public void goDown() throws FirstFloorException {
		if(this.position.getPreviousFloor() == null) {
			this.direction = null;
		} else {
			this.position = this.position.getPreviousFloor();
			this.direction = "down";
		}
		
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

	public LinkedHashMap<User, Floor> getPassengers() {
		return passengers;
	}

	public int getElevatorNumber() {
		return elevatorNumber;
	}
	
	public LinkedHashMap<Floor, Integer> getReachableFloors() {
		return reachableFloors;
	}


	

}
