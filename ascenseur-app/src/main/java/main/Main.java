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
		/*
		System.out.println("******************************************************************************");
		for(User u : User.getUsers()) {
			System.out.println("Direction = " + u.getDirection());
			System.out.println("Destination = " + u.getDestination().getFloorNumber() + ", " + u.getDestination().getColor());
			if(u.getFinalDestination() != null) System.out.println("Final destination = " + u.getFinalDestination().getFloorNumber() + ", " + u.getFinalDestination().getColor());
			System.out.println("Source = " + u.getSource().getFloorNumber() + ", " + u.getSource().getColor());
			System.out.println(); System.out.println();
		}
		System.out.println("******************************************************************************");
		System.out.println();
		*/
		do {  
//			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//			for(Floor f : Floor.getFloors()) {
//				System.out.print(f.getFloorNumber() + ", " + f.getColor());
//				System.out.println();
//				System.out.println("users up : " + f.getUsersUp().size());
//				System.out.println("users down : " + f.getUsersDown().size());
//				System.out.println();
//			}
//			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//			
//			System.out.println("******************************************************************************************************");
//			System.out.println("direction, color and number or each DEMAND : ");
//			System.out.println(Dispatcher.getDemands() );
//			Utils.displayDemandsDetails();
//			System.out.println("******************************************************************************************************");
//
			for (String color : Dispatcher.getListElevator().keySet()) {
				for (Elevator el : Dispatcher.getListElevator().get(color)) {
					System.out.println(el.getColor() + " : " + el.getDirection() + " : "
							+ el.getPosition().getFloorNumber() + " : " + el.getPassengers());
				}
			}
		
			
			ElevatorSequence.makeSequence();
			Thread.sleep(1000);		
			System.out.println("test");
//			System.out.println("******************************************************************************");
//			for(User u : User.getUsers()) {
//				System.out.println("Direction = " + u.getDirection());
//				System.out.println("Destination = " + u.getDestination().getFloorNumber() + ", " + u.getDestination().getColor());
//				if(u.getFinalDestination() != null) System.out.println("Final destination = " + u.getFinalDestination().getFloorNumber() + ", " + u.getFinalDestination().getColor());
//				System.out.println("Source = " + u.getSource().getFloorNumber() + ", " + u.getSource().getColor());
//				System.out.println(); System.out.println();
//			}
//			System.out.println("******************************************************************************");
//			System.out.println();
			
		} while (!ElevatorSequence.SystemEmpty());
		
		
		for (String color : Dispatcher.getListElevator().keySet()) {
			for (Elevator el : Dispatcher.getListElevator().get(color)) {
				System.out.println("************************************************************************************************************");
				System.out.println("color, position, direction and passengers of each elevator : ");
				System.out.println(el.getColor() + " : " + el.getDirection() + " : " + el.getPosition().getFloorNumber()
						+ " : " + el.getPassengers());
				System.out.println("************************************************************************************************************");

			}
		}
		
	}

}
