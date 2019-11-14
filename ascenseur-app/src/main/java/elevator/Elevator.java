package elevator;


import java.util.LinkedHashMap;
import java.util.PriorityQueue;

import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
import exceptions.UnreachableFloor;
import floor.Floor;
import user.User;

public abstract class Elevator {
 
	private String color;
	private int maxWeight;
	private int currentWeight = 0;
	protected LinkedHashMap<User, Floor> passengers;
	//Chaque Floor reachable peut avoir une valeur 1 si le dispatcheur lui � demand� de deservir cette �tage
	protected LinkedHashMap<Floor, Integer> reachableFloors;
	private String direction;
	Floor position;
	protected int elevatorNumber;

	public Elevator(String color, int maxWeight, int elevatorNumber, LinkedHashMap<Floor, Integer> reachableFloors) {
		this.color = color;
		this.maxWeight = maxWeight;
		this.elevatorNumber = elevatorNumber;
		this.passengers = new LinkedHashMap<User, Floor>();
		this.reachableFloors = reachableFloors;
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
		if (this.direction.equals("up")) {
			this.floorToElevator(this.position.getUsersUp());
		} else {
			this.floorToElevator(this.position.getUsersDown());
		}
	} 

	public void floorToElevator(PriorityQueue<User> pq) throws UnreachableFloor {
		while (!pq.isEmpty()) {
			User u = pq.peek();
			if (!this.reachableFloors.containsKey(u.getDestination()) && u.getFinalDestination() == null) {
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
	
	
	public void exit() {
		for(User u : this.passengers.keySet()) {
			if(u.getDestination() == this.position) {
				this.passengers.remove(u);
				if(!u.isFinalDestination()) {
					u.makeChangement();
				}
			}
		}
	}

	public void goUp() throws LastFloorExeption {
		this.position = this.position.getnextFloor();
	}

	public void goDown() throws FirstFloorExeption {
		this.position = this.position.getPreviousFloor();
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
