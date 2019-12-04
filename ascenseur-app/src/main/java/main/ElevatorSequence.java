package main;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.NoSuchFloorException;
import exceptions.UnreachableFloor;
import elevator.Dispatcher;
import elevator.Elevator;
import floor.Floor;

public class ElevatorSequence {

	public static void makeSequence() throws FirstFloorException, LastFloorException, UnreachableFloor, NoSuchFloorException {
  
		Dispatcher.dispatch(); 
		
			// pour chaque ascenceur dans chaque couleur d'ascenceur
			for (String color : Dispatcher.getListElevator().keySet()) {
				for (Elevator el : Dispatcher.getListElevator().get(color)) {
					
					elevatorStopper(el);
					
					if(el.getTarget() == "up" && el.getDirection() == "up" && Dispatcher.isDemanded(el)){
						el.enter();
					} else if (el.getDirection() == "up" && el.getTarget() == "down" && !Dispatcher.isDemandUpper(el) &&  Dispatcher.isDemanded(el)) {
						el.setDirection("down");
						el.enter();
					} else if (el.getDirection() == "down" && el.getTarget() == "down" &&  Dispatcher.isDemanded(el)) {
						el.enter();
					}
					
					// fait les mouvements
					if (el.getDirection() == "up") {
						el.goUp();
					} else if (el.getDirection() == "down") {
						el.goDown();
					} 
					
					// si un passager veux descendre � l'�tage actuel
					if (el.getPassengers().containsValue(el.getPosition())) {
						el.exit();
					}
					
					

				}
			}

	}

	private static void elevatorStopper(Elevator el) {
		if(el.getPosition().getFloorNumber() == 0 && el.getDirection() == "down") {
			el.setDirection(null);
			el.setTarget(null);
		}
		else if(el.getPassengers().isEmpty() && !Dispatcher.isDemanded(el) && el.getPosition().getFloorNumber() != 0) {
			el.setDirection("down");
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
