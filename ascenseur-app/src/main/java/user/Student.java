package user;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import floor.Floor;

public class Student extends User {
	
	public Student(String firstName,
			String lastName, int age, float weight, Boolean PMR, Floor source, Floor destination) throws FirstFloorException, LastFloorException {
		super(firstName, lastName, age, weight, "student", PMR, source, destination);
	}

}
