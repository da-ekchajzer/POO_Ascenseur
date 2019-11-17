package user;

import floor.Floor;

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
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((floor == null) ? 0 : floor.hashCode());
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
		Demand other = (Demand) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (floor == null) {
			if (other.floor != null)
				return false;
		} else if (!floor.equals(other.floor))
			return false;
		return true;
	}

}
