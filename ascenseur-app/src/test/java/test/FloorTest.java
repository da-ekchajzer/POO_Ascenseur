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
	public void init() throws FirstFloorExeption {
		try {
			syst = new SystemInit();
		} catch (FirstFloorExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispatch = syst.d;
		int[] yellowFloorsTab = {0, 9, 11, 12, 13, 14, 15, 16 };
		reachableFloorY = syst.createCircularFloorList(yellowFloorsTab, "yellow");
	}
	
	@Test
	public void test() throws LastFloorExeption {
		
		Floor fgreen0 = new Floor(0, "green");
		Floor fgreen1 = new Floor(1, "green");
		Floor fgreen9 = new Floor(9, "green");
		Floor fgreen14 = new Floor(14, "green");

		Floor fyellow0 = new Floor(0, "yellow");
		Floor fyellow14 = new Floor(0, "yellow");
		Floor fyellow20 = new Floor(0, "yellow");
		Floor fyellow21 = new Floor(0, "yellow");
		
		Floor fred0 = new Floor(0, "red");
		Floor fred1 = new Floor(1, "red");
		Floor fred2 = new Floor(2, "red");
		Floor fred3 = new Floor(3, "red");


		assertEquals(0, fgreen0.getFloorNumber());
		//assertEquals(fgreen1, fgreen0.getnextFloor());		TODO : SystemInit.createCircularFloorList

	}

}
