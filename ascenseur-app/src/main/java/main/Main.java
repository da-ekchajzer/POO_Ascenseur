package main;

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

public class Main {
 
	public static void main(String[] args) throws LastFloorException, FirstFloorException, UnreachableFloor,
			InterruptedException, NoSuchFloorException, NoSuchDirection {
		SystemStats.setTimeStart();
		SystemInit sys = new SystemInit();
		Utils.createRandomUsers(500);
		
		do {  
//			Thread.sleep(1000);
			ElevatorSequence.makeSequence();
//			Utils.displayDemandsDetails();
//			Utils.displayElevatorDetails();
//			Utils.displayFloorsDetails();
		} while (!ElevatorSequence.SystemEmpty());
		
		SystemStats.setTimeEnd();
		
		System.out.println(SystemStats.getStats());
	}

}
