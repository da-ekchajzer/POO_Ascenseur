package main;

import java.util.Random;

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
	

	
	/**
	 * @param nb
	 * @throws FirstFloorException
	 * @throws LastFloorException
	 * @throws NoSuchFloorException
	 * 
	 * Crée le nombre d'User passé en paramètre de façon pseudoAléatoire, la ajoute à la liste des Users et les fait appeler l'Elevator vie callElevator
	 */
	public static void createRandomUsers(int nb) throws FirstFloorException, LastFloorException, NoSuchFloorException {
		Random Randomizer = new Random();

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
			firstName = firstNames[Randomizer.nextInt(19)];
			lastName = lastNames[Randomizer.nextInt(12)];
			age = Randomizer.nextInt(100 - 16) + 16;
			weight = Randomizer.nextFloat() * (150 - 30) + 30;
			statut = statuts[Randomizer.nextInt(3)];
			pmr = Math.random() >= 1.0 - 0.058; // % d'handicape moteur dans la population française
			source = (Floor) floors[Randomizer.nextInt(22)];
			destination = (Floor) floors[Randomizer.nextInt(22)];

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
				System.out.println(d.getDirection() + ", " + d.getFloor().getFloorNumber() + ", " + d.getFloor().getColor());
			}	
		}
		System.out.println();
	}
	
	public static void displayUsersDetails() {
		for(User u : User.getUsers()) {
			System.out.println("Direction = " + u.getDirection());
			System.out.println("Destination = " + u.getDestination().getFloorNumber() + ", " + u.getDestination().getColor());
			if(u.getFinalDestination() != null) System.out.println("Final destination = " + u.getFinalDestination().getFloorNumber() + ", " + u.getFinalDestination().getColor());
			System.out.println("Source = " + u.getSource().getFloorNumber() + ", " + u.getSource().getColor());
			System.out.println("Is PMR : " + u.getPMR());
			System.out.println();
		}
		System.out.println();
	}
	
	public static void displayFloorsDetails() {
		for(Floor f : Floor.getFloors()) {
			System.out.print(f.getFloorNumber() + ", " + f.getColor());
			System.out.println();
			System.out.println("users up : " + f.getUsersUp().size());
			System.out.println("users down : " + f.getUsersDown().size());
			System.out.println();
		}	
	}

	public static void displayElevatorDetails() {
		for (String color : Dispatcher.getListElevator().keySet()) {
			for (Elevator el : Dispatcher.getListElevator().get(color)) {
				System.out.println(el.getColor() + " : " + el.getDirection() + " : "
						+ el.getPosition().getFloorNumber() + " : " + el.getPassengers() + " : " + el.getNbfloors());
			}
		}
		System.out.println("");
	}
	
	
	public static Elevator getElevator(String color, int num) {
		for (Elevator el : Dispatcher.getListElevator().get(color)) {
			if(el.getElevatorNumber() == num) return el;
		}
		return null;
	}
	
}
