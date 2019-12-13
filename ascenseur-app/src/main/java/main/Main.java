package main;

import java.util.Scanner;

import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.NoSuchDirection;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import floor.Floor;
import user.Demand;
import user.Teacher;
import user.User;

/**
 * Est la classe principale de notre application : fait tourner notre systeme tant qu'il n'est pas vide.
 * @author david_Ekchajzer, Mathieu_Ridet 
 */
public class Main {
 
	public static void main(String[] args) throws LastFloorException, FirstFloorException, UnreachableFloor,
			InterruptedException, NoSuchFloorException, NoSuchDirection {
		SystemStats.setTimeStart();
		SystemInit sys = new SystemInit();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrer le nombre d'utilisateurs que vous voulez dans le système : ");
		Utils.createRandomUsers(sc.nextInt());
		
		do {  
			ElevatorSequence.makeSequence();
			//Utils.displayDemandsDetails();
			//Utils.displayElevatorDetails();
			//Utils.displayFloorsDetails();
			//Thread.sleep(1000);
		} while (!ElevatorSequence.SystemEmpty());
		
		SystemStats.setTimeEnd();
		
		System.out.println(SystemStats.getStats());
	}

}
