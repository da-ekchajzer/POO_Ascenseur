package user;

import exceptions.NoSuchFloorException;
import floor.Floor;
import main.SystemStats;

/**
 * Herite de User, represente un utilisateur ayant le statut "Administrative".
 * @author david_Ekchajzer, Mathieu_Ridet
 * 
 */
public class Administrative extends User {
	
	public Administrative(String firstName, String lastName, int age, float weight, Boolean pmr, Floor source, Floor destination) throws NoSuchFloorException {
		super(firstName, lastName, age, weight, "administrative", pmr, source, destination);
		SystemStats.addAmdin();
	}

} 
