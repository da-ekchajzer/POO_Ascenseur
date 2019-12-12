package test;

import static org.junit.Assert.assertTrue;

import java.util.PriorityQueue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import elevator.Dispatcher;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import floor.Floor;
import main.SystemInit;
import user.Demand;
import user.Teacher;
import user.User;

public class ExceptionsTest {

	static SystemInit syst;
	Demand demand;
	
	@BeforeClass
	public static void init() throws NoSuchFloorException {
		if(SystemInitTest.systToTest != null) syst = SystemInitTest.systToTest;
		else syst = new SystemInit();
	}
	
	@Test
	public void firstAndLastFloorExceptionsTest() throws LastFloorException, FirstFloorException, NoSuchFloorException {
		// Must throw FirstFloorException
		try {
			demand = new Demand(new Floor(0, "green"), "down");
			assertTrue(false);
		} catch (FirstFloorException e) {
			assertTrue(true);
		}
		
		// Must throw LastFloorException
		try {
			demand = new Demand(new Floor(22, "red"), "up");
			assertTrue(false);
		} catch (LastFloorException e) {
			assertTrue(true);
		}
		
		try {
			Floor.getFloor(30, "green");
			assertTrue(false);
		} catch (NoSuchFloorException e) {

			assertTrue(true);
		}
		 
		
		User t = new Teacher("test", "test", 45, 60, false, Floor.getFloor(0, "green"), Floor.getFloor(13, "yellow"));
		t.setDestination(Floor.getFloor(13, "yellow"));
		PriorityQueue<User> pq = new PriorityQueue<>();
		pq.add(t);
		try {
			Dispatcher.getListElevator().get("green").get(0).floorToElevator(pq);
			assertTrue(false);
		} catch (UnreachableFloor e) {
			assertTrue(true);
		}

	}
	
	
	@AfterClass
	public static void after() {
		syst.emptySystem();
	}

}
