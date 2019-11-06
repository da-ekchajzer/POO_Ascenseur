package java_classes.user;

import java_classes.floor.Floor;

public class Administrative extends User {

	public Administrative(String firstName, int age, int weight, Boolean PMR, Floor source,
			Floor destination, String lastName) {
		super(firstName, age, weight, "administrative", PMR, source, destination, lastName);
	}

}
