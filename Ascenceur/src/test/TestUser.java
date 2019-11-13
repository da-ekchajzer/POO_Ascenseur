package test;
import java_classes.floor.Floor;
import java_classes.user.*;
import Main.*;
import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class TestUser {
	
	@Test
	void userCreation() throws FirstFloorExeption, LastFloorExeption {	
		SystemInit s1 = new SystemInit();
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
	
}
