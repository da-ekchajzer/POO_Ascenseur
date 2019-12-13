package user;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import floor.Floor;
import main.SystemStats;

/**
 * Herite de User, represente un utilisateur ayant le statut "Administrative".
 * @author david_Ekchajzer, Mathieu_Ridet
 * 
 */
public class Administrative extends User {
	
	public Administrative(String firstName, String lastName, int age, float weight, Boolean PMR, Floor source,
			Floor destination) throws FirstFloorException, LastFloorException, NoSuchFloorException {
		super(firstName, lastName, age, weight, "administrative", PMR, source, destination);
		SystemStats.addAmdin();
	}

} 
