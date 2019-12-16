package main;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import elevator.Dispatcher;
import elevator.Elevator;
import exceptions.NoSuchDirection;
import floor.Floor;

public class ElevatorSequence {

	private ElevatorSequence() {}
	
	/**
	 * Represente une iteration realisee par notre systeme.
	 * @throws FirstFloorException
	 * @throws LastFloorException
	 * @throws UnreachableFloor
	 * @throws NoSuchFloorException
	 * @throws NoSuchDirection
	 */
	public static void makeSequence() throws NoSuchFloorException {
		Dispatcher.dispatch(); 
		for (String color : Dispatcher.getListElevator().keySet()) {
			for (Elevator el : Dispatcher.getListElevator().get(color)) {
				if (el.getPassengers().containsValue(el.getPosition())) {
					el.exit();
				}
				if(el.getReachableFloors().get(el.getPosition()) == 1) {	
					el.enter();
					el.getReachableFloors().replace(el.getPosition(), 0);
				}
				elevatorStopper(el);
				if (el.getDirection()!=null && el.getDirection().equals("up")) {
					el.goUp();
					if(el.getNbfloors() != 0) {
						el.setNbfloors(el.getNbfloors()-1);
						if(el.getNbfloors() == 0) {
							el.setDirection("down");
						}
					}
				} else if (el.getDirection()!=null && el.getDirection().equals("down")) {
					el.goDown();
					if(el.getNbfloors() != 0) {
						el.setNbfloors(el.getNbfloors()-1);
						if(el.getNbfloors() == 0) {
							el.setDirection("up");
						}
					}
				} 
			}
		}
		SystemStats.addSequenceIteration();
	}

	/**
	 * Met un Elevator a l'arret s'il est vide et n'a plus de demande a traiter.
	 * @param el
	 */
	private static void elevatorStopper(Elevator el) {
		if(el.getPassengers().isEmpty() && !el.getReachableFloors().containsValue(1)) {
			el.setDirection(null);
			el.setNbfloors(0);
		} 
	}

	/**
	 * 
	 * @return true si le systeme est vide (plus de demande en attente, Elevators & Floors vide).
	 */
	public static boolean systemEmpty() {
		for (Floor f : Floor.getFloors()) {
			if (!f.getUsersDown().isEmpty() || !f.getUsersUp().isEmpty()) {
				return false;
			}
		}
		for (String color : Dispatcher.getListElevator().keySet()) {
			for (Elevator el : Dispatcher.getListElevator().get(color)) {
				if (!el.getPassengers().isEmpty()) {
					return false;
				} 
			}
		}
		return true;
	}

}
