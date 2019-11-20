package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;
import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
import exceptions.UnreachableFloor;
import main.SystemInit;
import main.Utils;
import user.User;

public class ElevatorTest {

	List<Elevator> greens, yellows, reds;
	SystemInit systToTest;
	Dispatcher dispatch;
	 
	@Before
	public void init() {
		systToTest = new SystemInit();
		dispatch = systToTest.dispatcheur;
		greens = dispatch.getListElevator().get("green");
		yellows = dispatch.getListElevator().get("yellow");
		reds = dispatch.getListElevator().get("red");
	}
	 
	 
	@Test
	public void ElevatorAttributesTest() throws LastFloorExeption, FirstFloorExeption {
		// Check 'elevatorNumber' attribute
		for(int i = 0 ; i < greens.size() ; i++) assertEquals(i+1, greens.get(i).getElevatorNumber());
		 
		Elevator green1 = greens.get(0);
		Elevator yellow1 = yellows.get(0);
		Elevator red1 = reds.get(0);
		
		assertEquals("green", green1.getColor());
		assertEquals(null, green1.getDirection());
		assertEquals(1000, green1.getMaxWeight());			//TODO : put the real weight
		assertEquals(0, green1.getPosition().getFloorNumber());
		green1.goUp();
		assertEquals(4, green1.getPosition().getFloorNumber());
		assertEquals("up", green1.getDirection());
		green1.goDown();
		assertEquals(0, green1.getPosition().getFloorNumber());
		assertEquals("down", green1.getDirection());
		
		assertEquals("yellow", yellow1.getColor());
		assertEquals(null, yellow1.getDirection());
		assertEquals(1000, yellow1.getMaxWeight());			//TODO : put the real weight
		
		assertEquals("red", red1.getColor());
		assertEquals(null, red1.getDirection());
		assertEquals(1000, red1.getMaxWeight());			//TODO : put the real weight
		
		
		
	}


	@Test
	public void floorToElevatorTest() {
		Elevator greenElevator = greens.get(0);

		PriorityQueue<User> pq = new PriorityQueue<>();
		try {
			Utils.createRandomUsers(10);
		} catch (FirstFloorExeption | LastFloorExeption e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		pq.addAll(User.getUsers());
		
		try {
			greenElevator.floorToElevator(pq);
		} catch (UnreachableFloor e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(!greenElevator.getPassengers().isEmpty());
		assertEquals(1, greenElevator.getPassengers().size());
	}
	
	@Test
	public void exitTest() {
		Elevator greenElevator = greens.get(0);
		int sizeBeforeExit = greenElevator.getPassengers().size();
		greenElevator.exit();
		int sizeAfterExit = greenElevator.getPassengers().size();
		assertTrue(sizeAfterExit<=sizeBeforeExit);
		
	}

}
