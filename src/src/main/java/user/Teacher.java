package user;

import exceptions.NoSuchFloorException;
import floor.Floor;
import main.SystemStats;

/**
 * Herite de User, represente un utilisateur ayant le statut "Teacher".
 * @author david_Ekchajzer, Mathieu_Ridet
 * 
 */
public class Teacher extends User {

	public Teacher(String firstName, String lastName, int age, float weight, Boolean pmr, Floor source,
			Floor destination) throws NoSuchFloorException {
		super(firstName, lastName, age, weight, "teacher", pmr, source, destination);
		SystemStats.addTeacher();
	}

}
