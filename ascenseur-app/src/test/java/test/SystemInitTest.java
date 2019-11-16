package test;

import org.junit.Before;
import org.junit.Test;

import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
import main.SystemInit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class SystemInitTest {
	List<Elevator> greens, yellows, reds;
	SystemInit systToTest;
	Dispatcher dispatch;
	
	@Before
	public void init() {
		systToTest = new SystemInit();
		dispatch = systToTest.d;
		greens = dispatch.getListElevator().get("green");
		yellows = dispatch.getListElevator().get("yellow");
		reds = dispatch.getListElevator().get("red");
	}
	 

	@Test
	public void ElevatorsCreation() {
		// We've Green, Yellow & Red !
		assertEquals(3, dispatch.getListElevator().size());

		// Verify we've 6 green, 6 yellow & 6 red !
		assertEquals(6, greens.size());
		assertEquals(6, yellows.size());
		assertEquals(6, reds.size());
	}

}
