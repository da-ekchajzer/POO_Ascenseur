package main;

import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchDirection;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import floor.Floor;
import user.Demand;
import user.Teacher;
import user.User;

public class Main {
 
	public static void main(String[] args) throws LastFloorException, FirstFloorException, UnreachableFloor,
			InterruptedException, NoSuchFloorException, NoSuchDirection {
		
		SystemInit sys = new SystemInit();
//		Utils.createRandomUsers(5);
		User t1 = new Teacher("Béné", "Legrand", 40, 40, false, Floor.getFloor(4, "green"), Floor.getFloor(0, "yellow"));
		User t2 = new Teacher("Béné", "Legrand", 40, 40, false, Floor.getFloor(0, "green"), Floor.getFloor(14, "yellow"));
//		User t3 = new Teacher("Béné", "Legrand", 40, 40, false, Floor.getFloor(4, "green"), Floor.getFloor(0, "yellow"));
		t2.callElevator();
		t1.callElevator();
		Utils.displayDemandsDetails();
		Utils.displayUsersDetails();
		Thread.sleep(2000);	
		
		do {  
			ElevatorSequence.makeSequence();
			Utils.displayElevatorDetails();
//			Utils.displayFloorsDetails();
			Thread.sleep(1000);		

		} while (!ElevatorSequence.SystemEmpty());

		
	}

}
