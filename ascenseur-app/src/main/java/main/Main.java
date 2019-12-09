package main;

import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import floor.Floor;
import user.Demand;
import user.User;

public class Main {
 
	public static void main(String[] args) throws LastFloorException, FirstFloorException, UnreachableFloor,
			InterruptedException, NoSuchFloorException {
		
		SystemInit sys = new SystemInit();
		Utils.createRandomUsers(10);
		
		do {  
			ElevatorSequence.makeSequence();
			Thread.sleep(1000);		
			System.out.println("test");

		} while (!ElevatorSequence.SystemEmpty());

		
	}

}
