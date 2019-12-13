package test;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchDirection;
import exceptions.NoSuchFloorException;
import floor.Floor;
import main.ElevatorSequence;
import main.SystemInit;
import user.Demand;

public class DispatcherTest {

	static SystemInit syst;
	static Demand demand;
	static List<Elevator> greens, yellows, reds;
	
	@BeforeAll
	public static void init() throws FirstFloorException, LastFloorException, NoSuchFloorException {
		if(test.SystemInitTest.systToTest != null) syst = test.SystemInitTest.systToTest;
		else syst = new SystemInit();
		
		greens = Dispatcher.getListElevator().get("green");
		yellows = Dispatcher.getListElevator().get("yellow");
		reds = Dispatcher.getListElevator().get("red");
		
		demand = new Demand(Floor.getFloor(0, "yellow"), "up");
	}
	
	public void fillDispatcherWithDemand() throws FirstFloorException, LastFloorException, NoSuchFloorException {
		demand = new Demand(Floor.getFloor(16, "yellow"), "down");
		Dispatcher.addDemand(demand);
		demand = new Demand(Floor.getFloor(0, "green"), "up");
		Dispatcher.addDemand(demand);
		demand = new Demand(Floor.getFloor(9, "green"), "down");
		Dispatcher.addDemand(demand);
		demand = new Demand(Floor.getFloor(9, "red"), "down");
		Dispatcher.addDemand(demand);
		demand = new Demand(Floor.getFloor(9, "yellow"), "down");
		Dispatcher.addDemand(demand);
		
		Dispatcher.addDemand(new Demand(Floor.getFloor(15, "yellow"), "up"));
		Dispatcher.addDemand(new Demand(Floor.getFloor(15, "yellow"), "down"));
		Dispatcher.addDemand(new Demand(Floor.getFloor(14, "yellow"), "down"));
		Dispatcher.addDemand(new Demand(Floor.getFloor(13, "yellow"), "down"));
		Dispatcher.addDemand(new Demand(Floor.getFloor(9, "yellow"), "up"));
		Dispatcher.addDemand(new Demand(Floor.getFloor(11, "yellow"), "down"));

	}
	
	
	@Test
	public void dispatchTest() throws FirstFloorException, LastFloorException, NoSuchDirection, NoSuchFloorException {
		for (Entry<String, List<Elevator>> entry : Dispatcher.getListElevator().entrySet()) {
			for(Elevator e : entry.getValue()) {
			    System.out.println(entry.getKey() + " = " + e.getDirection() + " ; " + e.getPosition().getFloorNumber());
			}
			System.out.println();
		}
		
		//assertTrue(Dispatcher.getDemands().isEmpty());
		fillDispatcherWithDemand();
		//assertEquals(5, Dispatcher.getDemands().size());
		assertEquals(11, Dispatcher.getDemands().size());
		
		Dispatcher.dispatch();
		
		for (Entry<String, List<Elevator>> entry : Dispatcher.getListElevator().entrySet()) {
			for(Elevator e : entry.getValue()) {
			    System.out.println(entry.getKey() + " = " + e.getDirection() + " ; " + e.getPosition().getFloorNumber());
			}
			System.out.println();
		}
		
		assertEquals(1, Dispatcher.getDemands().size());
		
		ElevatorSequence.makeSequence();
		ElevatorSequence.makeSequence();

		Dispatcher.dispatch();

		for (Entry<String, List<Elevator>> entry : Dispatcher.getListElevator().entrySet()) {
			for(Elevator e : entry.getValue()) {
			    System.out.println(entry.getKey() + " = " + e.getDirection() + " ; " + e.getPosition().getFloorNumber());
			}
			System.out.println();
		}
		
		assertEquals(0, Dispatcher.getDemands().size());

		
	}
	
	
	@AfterAll
	public static void after() {
		syst.emptySystem();
	}

}
