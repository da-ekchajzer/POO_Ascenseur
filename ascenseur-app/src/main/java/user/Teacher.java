package user;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import floor.Floor;
/**
 * @author david_Ekchajzer, Mathieu_Ridet
 * 
 */
public class Teacher extends User {

	public Teacher(String firstName, String lastName, int age, float weight, Boolean PMR, Floor source,
			Floor destination) throws FirstFloorException, LastFloorException, NoSuchFloorException {
		super(firstName, lastName, age, weight, "teacher", PMR, source, destination);
	}

}
