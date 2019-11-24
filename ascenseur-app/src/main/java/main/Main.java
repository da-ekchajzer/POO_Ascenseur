package main;

import java.util.Random;

import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.UnreachableFloor;
import floor.Floor;
import user.Administrative;
import user.Demand;
import user.Student;
import user.Teacher;
import user.User;

public class Main {
	public static SystemInit sys = new SystemInit();
	public static Dispatcher dispatcher = sys.dispatcheur;

	public static void main(String[] args) throws LastFloorException, FirstFloorException, UnreachableFloor {
		Utils.createRandomUsers(1);

			while(ElevatorSequence.makeSequence(dispatcher));
			for (String color : dispatcher.getListElevator().keySet()) {
				for (Elevator el : dispatcher.getListElevator().get(color)) {
					System.out.println(el.getDirection());
				}
			}

	}

	
}
