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
		Utils.createRandomUsers(50);
//		User t = new Teacher("Béné", "Legrand", 40, 40, false, Floor.getFloor(8, "green"), Floor.getFloor(4, "green"));
//		t.callElevator();
		Utils.displayDemandsDetails();
		Utils.displayUsersDetails();
		
		do {  
			ElevatorSequence.makeSequence();
			Utils.displayElevatorDetails();
			Thread.sleep(1000);		

		} while (!ElevatorSequence.SystemEmpty());

		
	}

}
