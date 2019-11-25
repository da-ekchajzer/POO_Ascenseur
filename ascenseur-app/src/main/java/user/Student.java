package user;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import floor.Floor;

public class Student extends User {
	
	public Student(String firstName,
			String lastName, int age, float weight, Boolean PMR, Floor source, Floor destination) throws FirstFloorException, LastFloorException, NoSuchFloorException {
		super(firstName, lastName, age, weight, "student", PMR, source, destination);
	}

}
