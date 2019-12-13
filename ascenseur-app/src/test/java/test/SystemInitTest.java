package test;


import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.NoSuchFloorException;
import main.SystemInit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SystemInitTest {
	static List<Elevator> greens, yellows, reds;
	public static SystemInit systToTest;
	public static Dispatcher dispatch;
	
	@BeforeAll
	public static void init() throws NoSuchFloorException {
		systToTest = new SystemInit();
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
