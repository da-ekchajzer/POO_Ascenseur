package java_classes.user;

import java_classes.floor.Floor;
import java_classes.elevator.Dispatcher;
import java_classes.elevator.Elevator;

public class User implements Comparable<User>{

	// OPIONNEL
	//1 PMR = 3 pers dans l'ascenseur
	//PMR escaliers n'existent pas, doivent forcément prendre l'ascenseur pour aller partout
	//Capacite max d'un ascenseur : 15 personnes (a voir)
	
	private String firstName;
	private String lastName;
	private int age;
	private int weight;
	private String status;
	private Boolean PMR;
	private int priority;
	private Floor source;
	private Floor destination;
	private String direction;
	

	public User(String firstName, int age, int weight, String status, Boolean PMR, Floor source, Floor destination,
			String lastName) {
		
		this.firstName = firstName;
		this.age = age;
		this.weight = weight;
		this.status = status;
		this.PMR = PMR;
		this.source = source;
		this.destination = destination;
		this.lastName = lastName;
		this.setPriority();
		
		if(this.source.getFloorNumber() > this.destination.getFloorNumber()) {
			this.direction = "down";
			this.source.addUsersDown(this);
		}else if(this.source.getFloorNumber() == this.destination.getFloorNumber()){
			//destruction
		}else {
			this.direction = "up";
			this.source.addUsersUp(this);
		}
			
	}

	private void callElevator() {	
		Dispatcher.chooseElevator(this.direction, this.source);
	}

	
	public int getAge() {
		return age;
	}

	public int getWeight() {
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
