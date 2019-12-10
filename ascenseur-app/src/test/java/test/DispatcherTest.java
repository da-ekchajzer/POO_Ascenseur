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
import exceptions.NoSuchFloorException;
import floor.Floor;
import main.SystemInit;
import user.Demand;

public class DispatcherTest {

	static SystemInit syst;
	static Demand demand;
	static List<Elevator> greens, yellows, reds;
	
	@BeforeClass
	public static void init() throws FirstFloorException, LastFloorException, NoSuchFloorException {
		if(SystemInitTest.systToTest != null) syst = SystemInitTest.systToTest;
		else syst = new SystemInit();
		
		greens = Dispatcher.getListElevator().get("green");
		yellows = Dispatcher.getListElevator().get("yellow");
		reds = Dispatcher.getListElevator().get("red");
		
		demand = new Demand(new Floor(0, "yellow"), "up");
		dispatchElevators();
	}
	
	public void fillDispatcherWithDemand() throws FirstFloorException, LastFloorException {
		demand = new Demand(new Floor(21, "yellow"), "down");
		Dispatcher.addDemand(demand);
		demand = new Demand(new Floor(0, "green"), "up");
		Dispatcher.addDemand(demand);
		demand = new Demand(new Floor(9, "green"), "down");
		Dispatcher.addDemand(demand);
		demand = new Demand(new Floor(9, "red"), "down");
		Dispatcher.addDemand(demand);
		demand = new Demand(new Floor(9, "yellow"), "down");
		Dispatcher.addDemand(demand);
		
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
		/*
		Elevator choosen = Dispatcher.chooseNearestElevator(demand);
		assertEquals("yellow", choosen.getColor());
		assertEquals(3, choosen.getElevatorNumber());
		assertEquals("down", choosen.getDirection());
		
		demand = new Demand(new Floor(21, "red"), "down");
		choosen = Dispatcher.chooseNearestElevator(demand);
		assertEquals("red", choosen.getColor());
		assertEquals(2, choosen.getElevatorNumber());
		assertEquals("down", choosen.getDirection());
		
		demand = new Demand(new Floor(9, "green"), "up");
		choosen = Dispatcher.chooseNearestElevator(demand);
		assertEquals("green", choosen.getColor());
		assertEquals(1, choosen.getElevatorNumber());
		assertEquals("up", choosen.getDirection());
		*/
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
		for (Entry<String, List<Elevator>> entry : Dispatcher.getListElevator().entrySet()) {
			for(Elevator e : entry.getValue()) {
			    System.out.println(entry.getKey() + " = " + e.getDirection() + " ; " + e.getPosition().getFloorNumber());
			}
			System.out.println();
		}
		
		assertTrue(Dispatcher.getDemands().isEmpty());
		fillDispatcherWithDemand();
		assertEquals(5, Dispatcher.getDemands().size());
		
		//Dispatcher.dispatch();
		
		assertEquals(1, Dispatcher.getDemands().size());
		
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
