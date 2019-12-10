package main;

import elevator.Dispatcher;
import elevator.Elevator;
import elevator.NoSuchDirection;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
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
		//Utils.createRandomUsers(100);

//		User t1 = new Teacher("Béné", "Legrand", 40, 40, false, Floor.getFloor(4, "green"), Floor.getFloor(0, "yellow"));
//		t1.callElevator();
		
		for(int i = 0 ; i < 100 ; i++) new Teacher("test", "test", 40, 40, false, Floor.getFloor(4, "green"), Floor.getFloor(0, "yellow")).callElevator();
		
		//Utils.displayUsersDetails();
		Thread.sleep(2000);	
		
		do {  
//			Utils.displayDemandsDetails();
			ElevatorSequence.makeSequence();
			Utils.displayElevatorDetails();
//			System.out.println("*******************");
//			Utils.displayFloorsDetails();
//			System.out.println("*******************");
			Thread.sleep(1000);

		} while (!ElevatorSequence.SystemEmpty());

		
	}

}
