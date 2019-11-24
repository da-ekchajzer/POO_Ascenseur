package test;
import floor.Floor;
import user.*;
import main.*;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
	static SystemInit syst;
	
	@BeforeClass
	public static void init() {
		syst = new SystemInit();
	}
	
	@Test
	public void userCreation() throws FirstFloorException, LastFloorException {	
		User uAdmin1 = new Administrative("Julien", "Dupont", 45, 84, false, Floor.getFloor(0, "yellow"), Floor.getFloor(14, "yellow"));
		assertEquals(uAdmin1.getAge(),45);
		assertEquals(uAdmin1.getPriority(), 2);
		assertFalse(uAdmin1.getPMR());
		assertTrue(uAdmin1.getWeight() == 84);
		assertEquals(uAdmin1.getFinalDestination(), null);
		assertTrue(uAdmin1.getSource().equals(Floor.getFloor(0, "yellow")));
		assertTrue(uAdmin1.getDestination().equals(Floor.getFloor(14, "yellow")));
		assertTrue(uAdmin1 instanceof Administrative);
		
		User uStudent1 = new Student("Juliette", "Martin", 19, 54, false, Floor.getFloor(14, "yellow"), Floor.getFloor(0, "yellow"));
		assertEquals(uStudent1.getAge(),19);
		assertEquals(uStudent1.getPriority(), 1);
		assertFalse(uStudent1.getPMR());
		assertTrue(uStudent1.getWeight() == 54);
		assertEquals(uStudent1.getFinalDestination(), null);
		assertTrue(uStudent1.getSource().equals(Floor.getFloor(14, "yellow")));
		assertTrue(uStudent1.getDestination().equals(Floor.getFloor(0, "yellow")));
		assertTrue(uStudent1 instanceof Student);
		
		User uTeacher1 = new Teacher("Marin", "Loups", 35, 44, true, Floor.getFloor(5, "green"), Floor.getFloor(14, "yellow"));
		assertEquals(uTeacher1.getAge(),35);
		assertEquals(uTeacher1.getPriority(), 6); 
		assertTrue(uTeacher1.getPMR());
		assertTrue(uTeacher1.getWeight() == 44);
		assertTrue(uTeacher1.getFinalDestination().equals(Floor.getFloor(14, "yellow")));
		assertTrue(uTeacher1.getSource().equals(Floor.getFloor(5, "green")));
		assertTrue(uTeacher1.getDestination().equals(Floor.getFloor(9, "green")));
		assertTrue(uTeacher1 instanceof Teacher);
		
	}
	
	@Test
	public void setCorrespondanceTest() throws FirstFloorException, LastFloorException {
		User u = new Student("Prenom", "Nom", 20, 85, false, Floor.getFloor(14,  "yellow"), Floor.getFloor(8, "green"));
		assertEquals(Floor.getFloor(9, "yellow"), u.getDestination());
		assertEquals(8, u.getFinalDestination().getFloorNumber());
	}
	
	/**
	 * Bonus !
	@Test
	public void PriseEscalierTest() throws FirstFloorExeption, LastFloorExeption {
		User u = new Student("Prenom", "Nom", 20, 85, false, Floor.getFloor(14,  "yellow"), Floor.getFloor(8, "green"));
		
		assertEquals(9, u.getSource().getFloorNumber());
		assertEquals("green",  u.getSource().getColor());
	}
	*/
}
