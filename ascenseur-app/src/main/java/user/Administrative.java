package user;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import floor.Floor;

public class Administrative extends User {
	
	public Administrative(String firstName, String lastName, int age, float weight, Boolean PMR, Floor source,
			Floor destination) throws FirstFloorException, LastFloorException {
		super(firstName, lastName, age, weight, "administrative", PMR, source, destination);
	}

} 
