package main;

import java.util.Random;

import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import floor.Floor;
import user.Administrative;
import user.Demand;
import user.Student;
import user.Teacher;
import user.User;

public class Main {
 
	public static void main(String[] args) throws LastFloorException, FirstFloorException, UnreachableFloor,
			InterruptedException, NoSuchFloorException {
		SystemInit sys = new SystemInit();
		Utils.createRandomUsers(10);

		do {  
			System.out.println(Dispatcher.getDemands() );
			for (String color : Dispatcher.getListElevator().keySet()) {
				for (Elevator el : Dispatcher.getListElevator().get(color)) {
					System.out.println(el.getColor() + " : " + el.getDirection() + " : "
							+ el.getPosition().getFloorNumber() + " : " + el.getPassengers());
				}
			}
			ElevatorSequence.makeSequence();
			Thread.sleep(1000);
			System.out.println();
		} while (!ElevatorSequence.SystemEmpty());

		for (String color : Dispatcher.getListElevator().keySet()) {
			for (Elevator el : Dispatcher.getListElevator().get(color)) {
				System.out.println(el.getColor() + " : " + el.getDirection() + " : " + el.getPosition().getFloorNumber()
						+ " : " + el.getPassengers());
			}
		}
	}

}
