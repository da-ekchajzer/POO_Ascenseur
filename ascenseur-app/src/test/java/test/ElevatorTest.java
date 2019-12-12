package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import floor.Floor;
import main.SystemInit;
import user.Student;
import user.User;

public class ElevatorTest {

	static List<Elevator> greens, yellows, reds;
	static SystemInit syst;

	 
	@BeforeClass
	public static void init() throws NoSuchFloorException {		
		if(SystemInitTest.systToTest != null) syst = SystemInitTest.systToTest;
		else syst = new SystemInit();
		
		greens = Dispatcher.getListElevator().get("green");
		yellows = Dispatcher.getListElevator().get("yellow");
		reds = Dispatcher.getListElevator().get("red");
	}
	 
	 
	@Test
	public void ElevatorAttributesTest() throws LastFloorException, FirstFloorException {
		// Check 'elevatorNumber' attribute
		for(int i = 1 ; i < greens.size()+1 ; i++) {
			assertEquals(i,  greens.get(i-1).getElevatorNumber());
		}
			 
		Elevator green1 = greens.get(1);
		Elevator yellow1 = yellows.get(1);
		Elevator red1 = reds.get(1);
		
		assertEquals("green", green1.getColor());
		assertEquals(null, green1.getDirection());
		assertEquals(1000, green1.getMaxWeight());			//TODO : put the real weight
		assertEquals(0, green1.getPosition().getFloorNumber());
		
		assertEquals("yellow", yellow1.getColor());
		assertEquals(null, yellow1.getDirection());
		assertEquals(1000, yellow1.getMaxWeight());			//TODO : put the real weight
		
		assertEquals("red", red1.getColor());
		assertEquals(null, red1.getDirection());
		assertEquals(1000, red1.getMaxWeight());			//TODO : put the real weight
		
		
		
	}

	
	
	@Test
	public void floorToElevatorAndExitTest() throws FirstFloorException, LastFloorException, NoSuchFloorException {
		Elevator greenElevator = greens.get(5);

		PriorityQueue<User> pq = new PriorityQueue<>();
		
		User u1 = new Student("David", "Ekchajzer", 22, 375, false, Floor.getFloor(0, "green"), Floor.getFloor(4, "green")) ;
		User u2 = new Student("Mathieu", "Ridet", 22, 375, false, Floor.getFloor(0, "green"), Floor.getFloor(4, "green")) ;
		User u3 = new Student("test", "test", 21, 375, false, Floor.getFloor(0, "green"), Floor.getFloor(4, "green")) ;
		//User u4 = new Student("Mathieu", "Ridet", 21, 75, false, Floor.getFloor(0, "green"), Floor.getFloor(4, "green")) ;

		pq.add(u1);
		pq.add(u2);
		pq.add(u3);

		try {
			greenElevator.floorToElevator(pq);
		} catch (UnreachableFloor e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(!greenElevator.getPassengers().isEmpty());
		assertEquals(2, greenElevator.getPassengers().size());
		assertEquals(1, pq.size());
		assertEquals("test", pq.peek().getFirstName());
		
		// On va à l'étage suivant : le 4 (destination finale de nos 2 users)
		greenElevator.goUp();
		greenElevator.exit();
		
		assertEquals(4, greenElevator.getPosition().getFloorNumber());
		
		
		assertEquals(0, greenElevator.getPassengers().size());
	}
	
	/*
	@Test
	public void weightCheckTest() throws FirstFloorException, LastFloorException, NoSuchFloorException {
		Elevator greenElevator = greens.get(3);

		User u1 = new Student("David", "Ekchajzer", 22, 900, false, Floor.getFloor(0, "green"), Floor.getFloor(4, "green")) ;
		assertFalse(greenElevator.weightCheck(u1));
	}
	*/
	
	@AfterClass
	public static void after() {
		syst.emptySystem();
	}
	
}
