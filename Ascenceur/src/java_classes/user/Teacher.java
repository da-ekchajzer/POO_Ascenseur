package java_classes.user;

import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
import java_classes.floor.Floor;

public class Teacher extends User {

	public Teacher(String firstName, String lastName, int age, float weight, Boolean PMR, Floor source,
			Floor destination) throws FirstFloorExeption, LastFloorExeption {
		super(firstName, lastName, age, weight, "teacher", PMR, source, destination);
	}

}
