package test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
import floor.Floor;
import main.SystemInit;

public class FloorTest {

	SystemInit syst;
	Dispatcher dispatch;
	LinkedHashMap<Floor, Integer> reachableFloorY;
	
	@Before
	public void init() throws FirstFloorExeption, LastFloorExeption {
		syst = new SystemInit();
		dispatch = syst.d;
	}
	
	@Test
	public void test() throws LastFloorExeption {
		Floor fgreen0 = Floor.getFloor(0, "green");
		Floor fgreen4 = Floor.getFloor(4, "green");
		Floor fgreen9 = Floor.getFloor(9, "green");

		Floor fyellow0 = Floor.getFloor(0, "yellow");
		Floor fyellow14 = Floor.getFloor(14, "yellow");
		Floor fyellow20 = Floor.getFloor(20, "yellow");
		Floor fyellow21 = Floor.getFloor(21, "yellow");
		
		Floor fred0 = Floor.getFloor(0, "red");
		Floor fred9 = Floor.getFloor(9, "red");
		Floor fred19 = Floor.getFloor(19, "red");
		Floor fred21 = Floor.getFloor(21, "red");


		assertEquals(0, fgreen0.getFloorNumber());
		assertEquals(fgreen4, fgreen0.getnextFloor());
	}

}
