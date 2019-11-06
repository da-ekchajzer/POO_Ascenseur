package java_classes.user;

import java_classes.floor.Floor;

public class Teacher extends User {

	public Teacher(String firstName, int age, int weight, Boolean PMR, Floor source, Floor destination,
			String lastName) {
		super(firstName, age, weight, "teacher", PMR, source, destination, lastName);
	}

}
