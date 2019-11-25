package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

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

public class FloorTest {
 
	static SystemInit syst;
	static Dispatcher dispatch;
	static LinkedHashMap<Floor, Integer> reachableFloorY;
	
	@BeforeClass
	public static void init() throws FirstFloorException, LastFloorException, NoSuchFloorException {
		if(SystemInitTest.systToTest != null) syst = SystemInitTest.systToTest;
		else syst = new SystemInit();
	}
	
	@Test
	public void test() throws LastFloorException, FirstFloorException, NoSuchFloorException {
		Floor fgreen0 = Floor.getFloor(0, "green");
		Floor fgreen4 = Floor.getFloor(4, "green");
		Floor fgreen9 = Floor.getFloor(9, "green");

		Floor fyellow0 = Floor.getFloor(0, "yellow");
		Floor fyellow9 = Floor.getFloor(9, "yellow");
		Floor fyellow11 = Floor.getFloor(11, "yellow");
		Floor fyellow12 = Floor.getFloor(12, "yellow");
		
		Floor fred0 = Floor.getFloor(0, "red");
		Floor fred9 = Floor.getFloor(9, "red");
		Floor fred19 = Floor.getFloor(19, "red");
		Floor fred21 = Floor.getFloor(21, "red");
		
		assertEquals(0, fgreen0.getFloorNumber());
		assertEquals(fgreen4, fgreen0.getNextFloor());
		assertEquals(fyellow9, fyellow11.getPreviousFloor());
		assertEquals(fgreen4.getColor(), "green");
		
		try{
			Floor fred23 = Floor.getFloor(23, "red");
			assertTrue(false);
		}catch(NoSuchFloorException e){
			assertTrue(true);
		}

		
		
		try {
			assertNull(fgreen0.getPreviousFloor());
			assertTrue(false);
		}catch(FirstFloorException e) {
			assertTrue(true);
		}
		 
		try {
			assertNull(fgreen9.getNextFloor());
			assertTrue(false);
		}catch(LastFloorException e) {
			assertTrue(true);
		}
		
	} 

}
