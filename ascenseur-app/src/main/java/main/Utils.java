package main;

import java.util.Random;
import java.util.logging.Logger;
import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import floor.Floor;
import user.Administrative;
import user.Demand;
import user.Student;
import user.Teacher;
import user.User;

/**
 * @author david_Ekchajzer, Mathieu_Ridet
 * 
 */
public class Utils {

	private static final Logger LOGGER = Logger.getLogger("main.Utils");	  
	private Utils() {}
	
	/**
	 * @param nb
	 * @throws FirstFloorException
	 * @throws LastFloorException
	 * @throws NoSuchFloorException
	 * 
	 * Cree le nombre d'Users passe en parametre de façon "pseudo aleatoire", les ajoute a la liste des Users et les fait appeler un Elevator (via callElevator).
	 */
	public static void createRandomUsers(int nb) throws NoSuchFloorException {
		Random randomizer = new Random();

		String[] firstNames = { "Gabriel", "Louis", "Raphaël", "Jules", "Adam", "Lucas", "Léo", "Hugo", "Arthur",
				"Nathan", "Emma", "Louise", "Jade", "Alice", "Chloé", "Lina", "Mila", "Léa", "Manon", "Rose" };
		String[] lastNames = { "Stokes", "Stark", "Hatfield", "Wilkerson", "Day", "Mcconnell", "Calderon", "Haas",
				"Burke", "Carney", "Stewart", "Pace" };
		String[] statuts = { "Administrative", "Student", "Teacher" };
		Object[] floors = Floor.getFloors().toArray();

		String firstName;
		String lastName;
		int age;
		float weight;
		String statut;
		Boolean pmr;
		Floor source;
		Floor destination;
		User u;
		for (int i = 0; i < nb; i++) {
			firstName = firstNames[randomizer.nextInt(19)];
			lastName = lastNames[randomizer.nextInt(12)];
			age = randomizer.nextInt(100 - 16) + 16;
			weight = randomizer.nextFloat() * (150 - 30) + 30;
			statut = statuts[randomizer.nextInt(3)];
			pmr = Math.random() >= 1.0 - 0.058; // % d'handicape moteur dans la population française
			source = (Floor) floors[randomizer.nextInt(22)];
			destination = (Floor) floors[randomizer.nextInt(22)];

			if (statut.equals("Administrative")) {
				u = new Administrative(firstName, lastName, age, weight, pmr, source, destination);
			} else if (statut.equals("Student")) {
				u = new Student(firstName, lastName, age, weight, pmr, source, destination);
			} else {
				u = new Teacher(firstName, lastName, age, weight, pmr, source, destination);

			}
			
			if(u.getDirection() != null) {
				u.callElevator();				
				User.addUsers(u);
			}
		}

	}
	
	public static void displayDemandsDetails() {
		Object[] demands = Dispatcher.getDemands().toArray();
		for(Object o : demands) {
			if(o instanceof Demand) {
				Demand d = (Demand) o;
				LOGGER.info(d.getDirection() + ", " + d.getFloor().getFloorNumber() + ", " + d.getFloor().getColor());
			}	
		}
		LOGGER.info("\n");
	}
	
	public static void displayUsersDetails() {
		for(User u : User.getUsers()) {
			LOGGER.info("Direction = " + u.getDirection());
			LOGGER.info("Destination = " + u.getDestination().getFloorNumber() + ", " + u.getDestination().getColor());
			if(u.getFinalDestination() != null) LOGGER.info("Final destination = " + u.getFinalDestination().getFloorNumber() + ", " + u.getFinalDestination().getColor());
			LOGGER.info("Source = " + u.getSource().getFloorNumber() + ", " + u.getSource().getColor());
			LOGGER.info("Is PMR : " + u.getPMR() + "\n");
		}
		LOGGER.info("\n");
	}
	
	public static void displayFloorsDetails() {
		for(Floor f : Floor.getFloors()) {
			LOGGER.info(f.getFloorNumber() + ", " + f.getColor());
			LOGGER.info("\nusers up : " + f.getUsersUp().size() + "\n");
			LOGGER.info("users down : " + f.getUsersDown().size() + "\n");
		}	
	}

	public static void displayElevatorDetails() {
		for (String color : Dispatcher.getListElevator().keySet()) {
			for (Elevator el : Dispatcher.getListElevator().get(color)) {
				LOGGER.info(el.getColor() + " : " + el.getDirection() + " : "
						+ el.getPosition().getFloorNumber() + " : " + el.getPassengers() + " : " + el.getNbfloors());
			}
		}
		System.out.println();
	}
	
	
	public static Elevator getElevator(String color, int num) {
		for (Elevator el : Dispatcher.getListElevator().get(color)) {
			if(el.getElevatorNumber() == num) return el;
		}
		return null;
	}
	
}
