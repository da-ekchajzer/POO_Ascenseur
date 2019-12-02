package main;

import java.util.Random;

import elevator.Dispatcher;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import floor.Floor;
import user.Administrative;
import user.Demand;
import user.Student;
import user.Teacher;
import user.User;

public class Utils {
	

	
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

}
