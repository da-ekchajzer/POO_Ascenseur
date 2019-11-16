package test;

import org.junit.Test;

import elevator.Dispatcher;
import exceptions.FirstFloorExeption;
import floor.Floor;
import main.SystemInit;
import user.Demand;

public class DispatcherTest {
	// TODO : after SystemInitTest !
	@Test
	public void test() throws FirstFloorExeption {
		SystemInit syst = new SystemInit();
		Dispatcher dispatcherToTest = syst.d;
		
		Demand demand = new Demand(new Floor(14, "yellow"), "up");
		//dispatcherToTest.addDemand(demand);
		dispatcherToTest.chooseElevator(demand);
	} 

}
