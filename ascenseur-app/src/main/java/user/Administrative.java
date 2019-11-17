package user;

import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
import floor.Floor;

public class Administrative extends User {
	
	public Administrative(String firstName, String lastName, int age, float weight, Boolean PMR, Floor source,
			Floor destination) throws FirstFloorExeption, LastFloorExeption {
		super(firstName, lastName, age, weight, "administrative", PMR, source, destination);
	}

} 
