package test;

import org.junit.BeforeClass;
import org.junit.Test;
import elevator.Dispatcher;
import elevator.Elevator;
import main.SystemInit;
import static org.junit.Assert.assertEquals;

import java.util.List;

public class SystemInitTest {
	static List<Elevator> greens, yellows, reds;
	public static SystemInit systToTest;
	public static Dispatcher dispatch;
	
	@BeforeClass
	public static void init() {
		systToTest = new SystemInit();
		dispatch = systToTest.dispatcheur;
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
