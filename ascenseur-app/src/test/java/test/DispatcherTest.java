package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import floor.Floor;
import main.SystemInit;
import user.Demand;

public class DispatcherTest {

	static SystemInit syst;
	static Dispatcher dispatcherToTest;
	static Demand demand;
	static List<Elevator> greens, yellows, reds;
	
	@BeforeClass
	public static void init() throws FirstFloorException, LastFloorException {
		if(SystemInitTest.systToTest != null) syst = SystemInitTest.systToTest;
		else syst = new SystemInit();
		dispatcherToTest = syst.dispatcheur;
		
		greens = dispatcherToTest.getListElevator().get("green");
		yellows = dispatcherToTest.getListElevator().get("yellow");
		reds = dispatcherToTest.getListElevator().get("red");
		
		demand = new Demand(new Floor(0, "yellow"), "up");
		dispatchElevators();
	}
	
	public void fillDispatcherWithDemand(Dispatcher dispatcher) throws FirstFloorException, LastFloorException {
		demand = new Demand(new Floor(21, "yellow"), "down");
		dispatcher.addDemand(demand);
		demand = new Demand(new Floor(0, "green"), "up");
		dispatcher.addDemand(demand);
		demand = new Demand(new Floor(9, "green"), "down");
		dispatcher.addDemand(demand);
		demand = new Demand(new Floor(9, "red"), "down");
		dispatcher.addDemand(demand);
		demand = new Demand(new Floor(9, "yellow"), "down");
		dispatcher.addDemand(demand);
		
		dispatchElevators();
	}
	
	public static void dispatchElevators() {
		greens.get(0).setDirection("up");
		greens.get(0).setPosition(new Floor(9, "green"));
		greens.get(1).setDirection("down");
		greens.get(1).setPosition(new Floor(4, "green"));
		greens.get(2).setDirection(null);
		greens.get(2).setPosition(new Floor(9, "green"));
		
		yellows.get(0).setDirection("up");
		yellows.get(0).setPosition(new Floor(14, "yellow"));
		yellows.get(1).setDirection("down");
		yellows.get(1).setPosition(new Floor(14, "yellow"));
		yellows.get(2).setDirection(null);
		yellows.get(2).setPosition(new Floor(13, "yellow"));
		
		reds.get(0).setDirection("up");
		reds.get(0).setPosition(new Floor(9, "red"));
		reds.get(1).setDirection("down");
		reds.get(1).setPosition(new Floor(21, "red"));
		reds.get(2).setDirection(null);
		reds.get(2).setPosition(new Floor(9, "red"));
	}
	
	@Test
	public void chooseElevatorTest() throws FirstFloorException, LastFloorException {
		/* Voir l'état des ascenseurs ! */
		/*
		for (Entry<String, List<Elevator>> entry : dispatcherToTest.getListElevator().entrySet()) {
			for(Elevator e : entry.getValue()) {
			    System.out.println(entry.getKey() + " = " + e.getDirection() + " ; " + e.getPosition().getFloorNumber());
			}
			System.out.println();
		}
		*/
		
		Elevator choosen = dispatcherToTest.chooseElevator(demand);
		assertEquals("yellow", choosen.getColor());
		assertEquals(3, choosen.getElevatorNumber());
		assertEquals("down", choosen.getDirection());
		
		demand = new Demand(new Floor(21, "red"), "down");
		choosen = dispatcherToTest.chooseElevator(demand);
		assertEquals("red", choosen.getColor());
		assertEquals(2, choosen.getElevatorNumber());
		assertEquals("down", choosen.getDirection());
		
		demand = new Demand(new Floor(9, "green"), "up");
		choosen = dispatcherToTest.chooseElevator(demand);
		assertEquals("green", choosen.getColor());
		assertEquals(1, choosen.getElevatorNumber());
		assertEquals("up", choosen.getDirection());
		
		/*
		for (Entry<String, List<Elevator>> entry : dispatcherToTest.getListElevator().entrySet()) {
			for(Elevator e : entry.getValue()) {
			    System.out.println(entry.getKey() + " = " + e.getDirection() + " ; " + e.getPosition().getFloorNumber());
			}
			System.out.println();
		}
		*/
	}
	
	@Test
	public void dispatchTest() throws FirstFloorException, LastFloorException {
		for (Entry<String, List<Elevator>> entry : dispatcherToTest.getListElevator().entrySet()) {
			for(Elevator e : entry.getValue()) {
			    System.out.println(entry.getKey() + " = " + e.getDirection() + " ; " + e.getPosition().getFloorNumber());
			}
			System.out.println();
		}
		
		assertTrue(dispatcherToTest.getDemands().isEmpty());
		fillDispatcherWithDemand(dispatcherToTest);
		assertEquals(5, dispatcherToTest.getDemands().size());
		
		dispatcherToTest.dispatch();
		
		assertEquals(1, dispatcherToTest.getDemands().size());
		
		/*
		for (Entry<String, List<Elevator>> entry : dispatcherToTest.getListElevator().entrySet()) {
			for(Elevator e : entry.getValue()) {
			    System.out.println(entry.getKey() + " = " + e.getDirection() + " ; " + e.getPosition().getFloorNumber());
			}
			System.out.println();
		}
		*/
	}

}
