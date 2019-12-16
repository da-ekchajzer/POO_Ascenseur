package main;

import java.util.Scanner;
import java.util.logging.Logger;
import exceptions.NoSuchFloorException;

/**
 * Est la classe principale de notre application : fait tourner notre systeme tant qu'il n'est pas vide.
 * @author david_Ekchajzer, Mathieu_Ridet 
 */
public class Main {
 
	private static final Logger LOGGER = Logger.getLogger("main.Main");	  
	
	public static void main(String[] args) throws NoSuchFloorException {
		SystemStats.setTimeStart();
		new SystemInit();
		
		Scanner sc = new Scanner(System.in);
		LOGGER.info("Veuillez entrer le nombre d'utilisateurs que vous voulez dans le système : ");
		Utils.createRandomUsers(sc.nextInt());
		
		do {  
			ElevatorSequence.makeSequence();
			//Utils.displayDemandsDetails();
			//Utils.displayElevatorDetails();
			//Utils.displayFloorsDetails();
			//Thread.sleep(1000);
		} while (!ElevatorSequence.systemEmpty());
		
		sc.close();
		SystemStats.setTimeEnd();
		
		LOGGER.info(SystemStats.getStats());
	}

}
