package java_classes.user;

import java_classes.floor.Floor;

public class Student extends User {
	
	public Student(String firstName, int age, int weight, Boolean PMR, Floor source, Floor destination,
			String lastName) {
		super(firstName, age, weight, "student", PMR, source, destination, lastName);
	}

}
