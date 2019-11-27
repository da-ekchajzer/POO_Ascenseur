package user;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import floor.Floor;

public class Demand {

	private Floor floor;
	private String direction;
	
	public Demand(Floor f, String dir) throws FirstFloorException, LastFloorException {
		this.floor = f;
		this.direction = dir; 
		if(f.getFloorNumber() == 0 && this.direction.equals("down"))
			throw new FirstFloorException();
		else if(f.getFloorNumber() == 22 && this.direction.equals("up")) 
			throw new LastFloorException();
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
