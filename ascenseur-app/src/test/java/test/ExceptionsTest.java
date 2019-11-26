package test;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import floor.Floor;
import main.SystemInit;
import user.Demand;

public class ExceptionsTest {

	static SystemInit syst;
	Demand demand;
	
	@BeforeClass
	public static void init() throws NoSuchFloorException {
		if(SystemInitTest.systToTest != null) syst = SystemInitTest.systToTest;
		else syst = new SystemInit();
	}
	
	@Test
	public void firstAndLastFloorExceptionsTest() {
		// Must throw FirstFloorException
		try {
			demand = new Demand(new Floor(0, "green"), "down");
		} catch (FirstFloorException | LastFloorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Must throw LastFloorException
		try {
			demand = new Demand(new Floor(22, "red"), "up");
		} catch (FirstFloorException | LastFloorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
