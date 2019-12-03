package elevator;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import floor.Floor;
import user.Demand;
import user.User;
/**
 * @author david_Ekchajzer, Mathieu_Ridet
 * 
 */
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
  
	/**
	 * @param u
	 * @return true si le poids de l'User qui souhaite rentrer ne fais pas passer le poids de l'Elevator au dessus de son poids maximum, false sinon
	 */
	private boolean weightCheck(User u) {
		if (this.currentWeight + u.getWeight() <= this.maxWeight) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @throws UnreachableFloor
	 * Determine si l'Elevator dois prendre les Users qui montent ou ceux qui descendent puis lance floorToElevator si l'elevator n'a pas fait rentrer tous les user relance une demande
	 */
	public void enter() throws UnreachableFloor {
		if(this.direction == null) {
		}
		else if (this.direction.equals("up")) {
			this.floorToElevator(this.position.getUsersUp());
			if(!this.position.getUsersUp().isEmpty()) {
				Dispatcher.addDemand(new Demand(this.position, "up"));
			}
		} else {
			this.floorToElevator(this.position.getUsersDown());
			if(!this.position.getUsersDown().isEmpty()) {
				Dispatcher.addDemand(new Demand(this.position, "down"));
			}
		}
	} 

	/**
	 * @param pq
	 * @throws UnreachableFloor
	 * Regarde si l'ascenceur à la place pour un nouvel User si oui l'ajoute sinon si c'est une PMR lance exitWhenPMR  
	 */
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

	
	/**
	 * @param pq
	 * Fait rentrer le première user d'une queue d'un étage dans l'Elevator
	 */
	private void addPassengersToElevator(PriorityQueue<User> pq) {
		User u = pq.poll();
		this.passengers.put(u, u.getDestination());
		this.currentWeight += u.getWeight();

	}
	
	
	/**
	 * @param pq
	 * Fait sortir les passagers jusqu'à ce que le PMR premier dans la queue d'un étage puisse rentrer
	 */
	private void exitWhenPMR(PriorityQueue<User> pq) {
			User firstP = (User) this.passengers.keySet().toArray()[0];
			if(firstP.getDestination().getFloorNumber() < this.position.getFloorNumber()) {
				this.position.addUsersDown(firstP);
			}else{
				this.position.addUsersUp(firstP);
			}
			this.passengers.remove(firstP);
	}
	
	
	/**
	 * @throws NoSuchFloorException
	 * @throws FirstFloorException
	 * @throws LastFloorException
	 * 
	 * Itére sur les passagers de l'Elevator et sort les Users qui veulent déscendre à la position actuelle de l'Elevator
	 * Si un User doit faire un changement appel makeChangement puis callElevator
	 */
	public void exit() throws NoSuchFloorException, FirstFloorException, LastFloorException {
		for(Iterator<User> userIterator = this.passengers.keySet().iterator(); userIterator.hasNext();) {
			User u = userIterator.next();
			if(u.getDestination() == this.position) {
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

	/**
	 * @throws LastFloorException
	 * Monte l'Elevator de un étage
	 */
	public void goUp() throws LastFloorException {
//		if(this.position.getNextFloor() == null) {
//			this.direction = null;
//		} else {
			this.position = this.position.getNextFloor();
			this.direction = "up";
//		}
		
	}

	/**
	 * @throws FirstFloorException
	 * Descend l'Elevator de un étage
	 */
	public void goDown() throws FirstFloorException {
//		if(this.position.getPreviousFloor() == null) {
//			this.direction = null;
//		} else {
			this.position = this.position.getPreviousFloor();
			this.direction = "down";
//		}
		
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
