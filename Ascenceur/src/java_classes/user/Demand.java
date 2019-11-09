package java_classes.user;

import java_classes.floor.Floor;

public class Demand {

	private Floor floor;
	private String direction;
	
	public Demand(Floor f, String direction) {
		this.floor = f;
		this.direction = direction; 
	}
	
	public Floor getFloor() {
		return floor;
	}
	public String getDirection() {
		return direction;
	}

}
