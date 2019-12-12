package elevator;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import floor.Floor;
import main.SystemStats;
import main.Utils;
import user.Demand;
import user.User;
/**
 * Classe representant un Elevator. Celui-ci contient :</br>
 * Une Map(passager) associant User passagers et Floor </br> 
 * Une Map(reachableFloors) contenant l'ensemble des Floor que peux deservir l'Elevator si leur valeur est à 1 le Floor doit être desservis par l'Elevator.</br> 
 * @author david_Ekchajzer, Mathieu_Ridet
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
	private int nbFloors;
	private int nbDemandsTreated;
 
	public Elevator(String color, int maxWeight, int elevatorNumber, LinkedHashMap<Floor, Integer> reachableFloors) throws NoSuchFloorException {
		this.color = color;
		this.maxWeight = maxWeight;
		this.elevatorNumber = elevatorNumber;
		this.passengers = new LinkedHashMap<User, Floor>();
		this.reachableFloors = reachableFloors;
		this.direction = null;
		this.position = Floor.getFloor(0, color);
		this.nbFloors = 0;
	}
  
	/**
	 * @param u
	 * @return true si le poids de l'User qui souhaite rentrer ne fais pas passer le poids de l'Elevator au dessus de son poids maximum, false sinon
	 */
	private boolean weightCheck(User u) {
		if(this.currentWeight + u.getWeight() <= this.maxWeight) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determine si l'Elevator dois prendre les Users qui montent ou ceux qui descendent puis lance {@link #floorToElevator(PriorityQueue) floorToElevator} </br>
	 * Si l'elevator n'a pas pu faire rentrer tous les user relance une demande
	 * @throws UnreachableFloor
	 */
	public void enter() throws UnreachableFloor {
		
		if(this.direction == null) {

		}
		else if (this.direction.equals("up") && this.getNbfloors() == 0) {	
			this.floorToElevator(this.position.getUsersUp());
			if(!this.position.getUsersUp().isEmpty()) {
				Dispatcher.addDemand(new Demand(this.position, "up"));
			}

		} else if (this.direction.equals("down") && this.getNbfloors() == 0){
			this.floorToElevator(this.position.getUsersDown());
			if(!this.position.getUsersDown().isEmpty()) {
				Dispatcher.addDemand(new Demand(this.position, "down"));
			}
		}
	} 

	/**
	 * Permet de mettre les utilisateur d'une queue d'un étage dans l'elevator.
	 * Regarde si l'ascenceur à la place pour un nouvel User si oui l'ajoute sinon si c'est une PMR lance {@link elevator.Elevator#exitWhenPMR() exitWhenPMR}  
	 * @param pq La queue à vider dans l'Elevator
	 * @throws UnreachableFloor
	 */
	public void floorToElevator(PriorityQueue<User> pq) throws UnreachableFloor {
		while (!pq.isEmpty()) {
			
			User u = pq.peek();
			if (!this.reachableFloors.keySet().contains(u.getDestination())) {
				pq.poll();
				throw new UnreachableFloor("...");
			}
			if (!this.weightCheck(u)) {
				while(pq.peek().getPMR()) {
					if(this.itContainsOnlyPMR()) break;
					this.exitWhenPMR();
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

	
	private boolean itContainsOnlyPMR() {
		for(User u : this.getPassengers().keySet()) {
			if(!u.getPMR()) return false;
		}
		return true;
	}

	/**
	 * Fait rentrer le première user d'une queue d'un étage dans l'Elevator
	 * @param pq
	 */
	private void addPassengersToElevator(PriorityQueue<User> pq) {
		User u = pq.poll();
		this.passengers.put(u, u.getDestination());
		this.currentWeight += u.getWeight();

	}
	
	
	/**
	 * @param pq
	 * Fait sortir les passagers jusqu'à ce que le premier PMR de la queue d'un étage puisse rentrer
	 */
	private void exitWhenPMR() {
			User lessImportantUser = null;
						
			for(User u : this.passengers.keySet()) {
				if(lessImportantUser == null || lessImportantUser.compareTo(u) > 0) 
					lessImportantUser = u;
			}
			
			if(lessImportantUser.getDestination().getFloorNumber() < this.position.getFloorNumber()) {
				this.position.addUsersDown(lessImportantUser);
			}else{
				this.position.addUsersUp(lessImportantUser);
			}
			this.passengers.remove(lessImportantUser);
			this.removeWeight(lessImportantUser.getWeight());
	}

	/**
	 * Itére sur les passagers de l'Elevator et sort les Users qui veulent déscendre à la position actuelle de l'Elevator
	 * </br>Si un User doit faire un changement appel {@link user.User#makeChangement() makeChangement} puis {@link user.User#callElevator() callElevator}
	 * @throws NoSuchFloorException
	 * @throws FirstFloorException
	 * @throws LastFloorException
	 * 
	 * 
	 */
	public void exit() throws NoSuchFloorException, FirstFloorException, LastFloorException {
		for(Iterator<User> userIterator = this.passengers.keySet().iterator(); userIterator.hasNext();) {
			User u = userIterator.next();
			if(u.getDestination() == this.position) {
				userIterator.remove();
				this.removeWeight(u.getWeight());
				if(!u.isFinalDestination()) {
					if(u.getFinalDestination().getFloorNumber() != u.getDestination().getFloorNumber()) {
						u.makeChangement();
						u.callElevator();
					}else{
						SystemStats.addUserReachDestination();
					}
					
				}else if(u.getDestination().equals(this.position)){
					SystemStats.addUserReachDestination();
				}
			}
		}
	}

	/**
	 * Monte l'Elevator de un étage.
	 * @throws LastFloorException si c'est le dernier Floor 
	 * 
	 */
	public void goUp() throws LastFloorException {
		if(this.position.getNextFloor() == null) {
			throw new LastFloorException();
		} else {
			this.position = this.position.getNextFloor();
		}
		
	}

	/**
	 * Descend l'Elevator de un étage
	 * @throws FirstFloorException si c'est le premier Floor
	 * 
	 */
	public void goDown() throws FirstFloorException {
		if(this.position.getPreviousFloor() == null) {
			throw new FirstFloorException();
		} else {
			this.position = this.position.getPreviousFloor();
		}
		
	}
	
	public String getDirection() {
		return this.direction;
	} 

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Floor getPosition() {
		return this.position;
	}

	public void setPosition(Floor position) {
		this.position = position;
	}

	public String getColor() {
		return this.color;
	}

	public int getMaxWeight() {
		return this.maxWeight;
	}

	public LinkedHashMap<User, Floor> getPassengers() {
		return this.passengers;
	}
	
	public void emptyElevator() {
		this.passengers.clear();
	}

	public int getElevatorNumber() {
		return this.elevatorNumber;
	}
	
	public LinkedHashMap<Floor, Integer> getReachableFloors() {
		return this.reachableFloors;
	}

	public int getNbfloors() {
		return this.nbFloors;
		
	}
	
	public void setNbfloors(int nb) {
		this.nbFloors = nb;
	}
	
	private void removeWeight(float w) {
		this.currentWeight -= w;
	}


	public int getNbDemandsTreated() {
		return this.nbDemandsTreated;
	}
	
	public void addNbDemandsTreated() {
		this.nbDemandsTreated++;
	}
	
	

}
