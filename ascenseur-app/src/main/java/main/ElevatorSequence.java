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

	public static void makeSequence() throws FirstFloorException, LastFloorException, UnreachableFloor, NoSuchFloorException, NoSuchDirection {
		
		Dispatcher.dispatch(); 
				
			// pour chaque ascenceur dans chaque couleur d'ascenceur
			for (String color : Dispatcher.getListElevator().keySet()) {
				for (Elevator el : Dispatcher.getListElevator().get(color)) {					

					// si un passager veux descendre � l'�tage actuel
					if (el.getPassengers().containsValue(el.getPosition())) {
						el.exit();
					}
					
					//enter
					if(el.getReachableFloors().get(el.getPosition()) == 1) {	
						el.enter();
						el.getReachableFloors().replace(el.getPosition(), 0);
					}
					
					elevatorStopper(el);
					
					// fait les mouvements
					if (el.getDirection() == "up") {
						el.goUp();
						if(el.getNbfloors() != 0) {
							el.setNbfloors(el.getNbfloors()-1);
							if(el.getNbfloors() == 0) {
								el.setDirection("down");
							}
						}
					} else if (el.getDirection() == "down") {
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

	private static void elevatorStopper(Elevator el) {
		if(el.getPassengers().isEmpty() && !el.getReachableFloors().containsValue(1)) {
			el.setDirection(null);
			el.setNbfloors(0);
		} 
	}

	public static boolean SystemEmpty() {
		for (Floor f : Floor.getFloors()) {
			if (!f.getUsersDown().isEmpty() || !f.getUsersUp().isEmpty()) {
				return false;
			}
		}
		//si un ascenceur n'est pas vide
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
