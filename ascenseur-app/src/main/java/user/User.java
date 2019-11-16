package user;

import floor.Floor;
import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
import elevator.Dispatcher;

public abstract class User implements Comparable<User> {

	// OPTIONNEL
	// 1 PMR = 3 pers dans l'ascenseur
	// PMR escaliers n'existent pas, doivent forcement prendre l'ascenseur pour
	// aller partout
	// Capacite max d'un ascenseur : 15 personnes (a voir)

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

	public User(String firstName, String lastName, int age, float weight, String status, Boolean PMR, Floor source,
			Floor destination) throws FirstFloorExeption, LastFloorExeption {

		this.firstName = firstName;
		this.age = age;
		this.weight = weight;
		this.status = status;
		this.PMR = PMR;
		this.source = source;
		this.destination = destination;
		this.lastName = lastName;
		this.finalDestination = null;
		this.setPriority();
		this.setDirection();
		if (this.destination.getColor() != this.source.getColor()) {
			this.setCorrespondanceElevator();
		}
	}
 
	public void callElevator(Dispatcher d) {
		d.addDemand(new Demand(this.destination, this.direction));
	}

	private void setCorrespondanceElevator() throws FirstFloorExeption, LastFloorExeption {

		this.finalDestination = this.destination;
		if (this.finalDestination.getFloorNumber() > 5 && this.finalDestination.getFloorNumber() < 9) {
			while (this.destination.getFloorNumber() != 9) {
				this.destination = this.destination.getnextFloor();
			} 
		} else {
			while (this.destination.getFloorNumber() != 9 && this.destination.getFloorNumber() != 0) {
				this.destination = this.destination.getPreviousFloor();
			}
		}
		this.destination = Floor.getFloor(this.destination.getFloorNumber(), this.source.getColor());
	}

	public Boolean isFinalDestination() {
		return this.finalDestination == null;
	}

	public void makeChangement() {
		this.source = Floor.getFloor(this.destination.getFloorNumber(), this.finalDestination.getColor());
		this.destination = this.finalDestination;
		this.finalDestination = null;
		this.setDirection();
	}


	
	public void setDirection() {
		if (this.source.getFloorNumber() > this.destination.getFloorNumber()) {
			this.direction = "down";
			this.source.addUsersDown(this);
		} else if (this.source.getFloorNumber() == this.destination.getFloorNumber()) {
			// destroy or reput in the system
		} else {
			this.direction = "up";
			this.source.addUsersUp(this);
		}
	}
  
	public void setPriority() {
		this.priority = 0;

		if (this instanceof Teacher) {
			this.priority += 3;
		} else if (this instanceof Administrative) {
			this.priority += 2;
		} else if (this instanceof Student) {
			this.priority += 1;
		} else {
			// To add in the futur
		}

		if (this.PMR) {
			this.priority += 3;
		}
	}

	public void setDestination(Floor destination) {
		this.destination = destination;
	}

	public void setSource(Floor source) {
		this.source = source;
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

	public int getAge() {
		return age;
	}

	public float getWeight() {
		return weight;
	}
	
	public Floor getFinalDestination() {
		return this.finalDestination;
	}
	
	@Override
	public int compareTo(User o) {
		int res;
		if (this.priority > o.priority) {
			res = 1;
		} else if (this.priority < o.priority) {
			res = -1;
		} else {
			if (this.age > o.age) {
				res = 1;
			} else if (this.age < o.age) {
				res = -1;
			} else {
				res = 0;
			}
		}
		return res;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PMR == null) ? 0 : PMR.hashCode());
		result = prime * result + age;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((finalDestination == null) ? 0 : finalDestination.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + priority;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + Float.floatToIntBits(weight);
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
		User other = (User) obj;
		if (PMR == null) {
			if (other.PMR != null)
				return false;
		} else if (!PMR.equals(other.PMR))
			return false;
		if (age != other.age)
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (finalDestination == null) {
			if (other.finalDestination != null)
				return false;
		} else if (!finalDestination.equals(other.finalDestination))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (priority != other.priority)
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
			return false;
		return true;
	}

	

}