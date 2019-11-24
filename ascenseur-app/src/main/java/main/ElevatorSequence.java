package main;
import exceptions.FirstFloorException;
import exceptions.LastFloorException;
import exceptions.UnreachableFloor;
import elevator.Dispatcher;
import elevator.Elevator;
import floor.Floor;

public class ElevatorSequence {

	public static boolean makeSequence(Dispatcher d) throws FirstFloorException, LastFloorException, UnreachableFloor {
  
		d.dispatch(); 
		
		if (!SystemEmpty(d)) {
			// pour chaque ascenceur dans chaque couleur d'ascenceur
			for (String color : d.getListElevator().keySet()) {
				for (Elevator el : d.getListElevator().get(color)) {
					// fait les mouvements
					if (el.getDirection() == "up") {
						el.goUp();
					} else if (el.getDirection() == "down") {
						el.goDown();
					} 
					
					// si un passager veux descendre � l'�tage actuel
					if (el.getPassengers().containsValue(el.getPosition())) {
						el.exit();
						el.enter();
						el.getReachableFloors().replace(el.getPosition(), 0);
					}
					//si l'ascenceur � �t� choisi par le dispatcheur pour l'�tage
					else if (el.getReachableFloors().get(el.getPosition()) == 1) {
						el.enter();
						el.getReachableFloors().replace(el.getPosition(), 0);
					}

					elevatorStopper(el);

				}
			}
			return true;
		}
		return false;
	}

	private static void elevatorStopper(Elevator el) {
		//Si personne n'est dans l'ascenceur et que le dispatcheur n'a donn� aucun �tage � deservir
		if (!el.getReachableFloors().containsValue(1) && el.getPassengers().isEmpty()) {
			el.setDirection(null);
		}
	}

	private static boolean SystemEmpty(Dispatcher d) {
		//Si au moin une personne attends � un �tage
		for (Floor f : Floor.getFloors()) {
			if (!f.getUsersDown().isEmpty() || !f.getUsersUp().isEmpty()) {
				return false;
			}
		}
		//si un ascenceur n'est pas vide
		for (String color : d.getListElevator().keySet()) {
			for (Elevator el : d.getListElevator().get(color)) {
				if (!el.getPassengers().isEmpty()) {
					return false;
				} 

			}
		}
		return true;
	}

}
