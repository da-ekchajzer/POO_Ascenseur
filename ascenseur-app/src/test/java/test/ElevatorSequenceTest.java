package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchDirection;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import floor.Floor;
import main.ElevatorSequence;
import main.SystemInit;
import main.Utils;
import user.Administrative;
import user.Demand;
import user.Student;
import user.Teacher;
import user.User;

public class ElevatorSequenceTest {
	
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
		
	}
	
	public void createUsersAndCallElevators() throws FirstFloorException, LastFloorException, NoSuchFloorException {
			
		User u = new Administrative("test1", "test1", 10, 50, true, Floor.getFloor(9, "red"), Floor.getFloor(0, "red"));
		u.callElevator();
		User.addUsers(u);
		u = new Student("test2", "test2", 20, 100, false, Floor.getFloor(0, "green"), Floor.getFloor(9, "green"));
		u.callElevator();
		User.addUsers(u);
		u = new Teacher("test3", "test3", 30, 150, false, Floor.getFloor(9, "yellow"), Floor.getFloor(21, "red"));
		u.callElevator();
		User.addUsers(u);
		
	}
	
	
	@Test
	public void makeSequenceTest() throws FirstFloorException, LastFloorException, UnreachableFloor, NoSuchFloorException, NoSuchDirection {		
		createUsersAndCallElevators();
		Floor f = Floor.getFloor(0, "green");
		
		ElevatorSequence.makeSequence();
		
		assertTrue(f.getUsersUp().isEmpty());
		f = Floor.getFloor(9, "red");
		assertFalse(f.getUsersDown().isEmpty());
		assertTrue(Dispatcher.getDemands().isEmpty());
		assertFalse(ElevatorSequence.systemEmpty());

		ElevatorSequence.makeSequence();
		ElevatorSequence.makeSequence();
		ElevatorSequence.makeSequence();
		ElevatorSequence.makeSequence();
		ElevatorSequence.makeSequence();

		assertTrue(f.getUsersDown().isEmpty());
		f = Floor.getFloor(9, "yellow");
		assertTrue(f.getUsersUp().isEmpty());
		assertTrue(ElevatorSequence.systemEmpty());	
		
	}
	
	@AfterClass
	public static void after() {
		syst.emptySystem();
	}
	
}
