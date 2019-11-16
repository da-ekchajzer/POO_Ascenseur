package main;
import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
import exceptions.UnreachableFloor;
import floor.Floor;
import user.User;

public class Main {
	public static void main (String[] args) throws LastFloorExeption{
		SystemInit s = new SystemInit();
		System.out.println(Floor.getFloor(0, "green").getnextFloor());
		}
}
