package user;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import floor.Floor;
import main.SystemStats;
/**
 * Herite de User, represente un utilisateur ayant le statut "Student".
 * @author david_Ekchajzer, Mathieu_Ridet
 * 
 */
public class Student extends User {
	
	public Student(String firstName,
			String lastName, int age, float weight, Boolean pmr, Floor source, Floor destination) throws FirstFloorException, LastFloorException, NoSuchFloorException {
		super(firstName, lastName, age, weight, "student", pmr, source, destination);
		SystemStats.addStudent();
	}

}
