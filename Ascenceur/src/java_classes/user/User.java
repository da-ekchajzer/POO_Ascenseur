package java_classes.user;

import java_classes.floor.Floor;
import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
import java_classes.elevator.Dispatcher;
import java_classes.elevator.Elevator;

public abstract class User implements Comparable<User>{

	// OPIONNEL
	//1 PMR = 3 pers dans l'ascenseur
	//PMR escaliers n'existent pas, doivent forcément prendre l'ascenseur pour aller partout
	//Capacite max d'un ascenseur : 15 personnes (a voir)
	
	private String firstName;
	private String lastName;
	private int age;
	private float weight;
	private String status;
	private Boolean PMR;
	private int priority;
	private Floor source;
	private Floor destination;
	private Floor finalDestination;
	private String direction;
	

	public User(String firstName, String lastName, int age, float weight, String status, Boolean PMR, Floor source, Floor destination) throws FirstFloorExeption, LastFloorExeption {
		
		this.firstName = firstName;
		this.age = age;
		this.weight = weight;
		this.status = status;
		this.PMR = PMR;
		this.source = source;
		this.destination = destination;
		this.lastName = lastName;  
		this.setPriority();
		
		this.changementElevator();
		
		if(this.source.getFloorNumber() > this.destination.getFloorNumber()) {
			this.direction = "down";
			this.source.addUsersDown(this);
		}else if(this.source.getFloorNumber() == this.destination.getFloorNumber()){
			//destroy or reput in the system
		}else {
			this.direction = "up";
			this.source.addUsersUp(this);
		}
			
	}
	
	
	private void changementElevator() throws FirstFloorExeption, LastFloorExeption {
		if(this.destination.getColor() != this.source.getColor()) {
			this.finalDestination = this.destination;
			if(this.finalDestination.getFloorNumber() > 5 && this.finalDestination.getFloorNumber() < 9) {
				while(this.destination.getFloorNumber() != 9) {
					this.destination = this.destination.getnextFloor();
				}
			}else {
				
				while(this.destination.getFloorNumber() != 9 && this.destination.getFloorNumber() != 0) {
					this.destination = this.destination.getPreviousFloor();
				}
			}
		}
	}

	public void callElevator() {	
		Dispatcher.addDemand(new Demand(this.destination, this.direction));
	}

	
	public int getAge() {
		return age;
	}

	public float getWeight() {
		return weight;
	}

	public void setPriority() {
		this.priority = 0;
		
		if(this instanceof Teacher) {
			this.priority += 3;
		} else if(this instanceof Administrative) {
			this.priority += 2;
		} else if(this instanceof Student) {
			this.priority += 1;
		} else {
			//To add in the futur
		}

		if (this.PMR) {
			this.priority += 3;
		}
	}
	
	public int getPriority() {
		return this.priority;
	}

	public Floor getSource() {
		return source;
	}

	public Floor getDestination() {
		return destination;
	}
	
	public boolean getPMR() {
		return this.PMR;
	}
	

	@Override
	public int compareTo(User o) {
		int res;
		if(this.priority > o.priority) {
			res = 1;
		}else if(this.priority < o.priority) {
			res = -1;
		}else {
			if(this.age > o.age) {
				res = 1;
			}else if(this.age < o.age){
				res = -1;
			} else {
				res = 0;
			}
		}
		return res;
	}
	
	
	 @Override
	  public boolean equals(Object o) {
		 return false;
	 }


}
