import exceptions.FirstFloorExeption;
import exceptions.LastFloorExeption;
import exceptions.UnreachableFloor;
import java_classes.elevator.Dispatcher;
import java_classes.elevator.Elevator;
import java_classes.floor.Floor;

public class ElevatorSequence {

	public static void makeSequence() throws FirstFloorExeption, LastFloorExeption, UnreachableFloor {

		Dispatcher.dispatch();
		
		if (!SystemEmpty()) {
			// pour chaque ascenceur dans chaque couleur d'ascenceur
			for (String color : Dispatcher.getListElevator().keySet()) {
				for (Elevator el : Dispatcher.getListElevator().get(color)) {
					// fait les mouvements
					if (el.getDirection() == "up") {
						el.goUp();
					} else if (el.getDirection() == "down") {
						el.goDown();
					}

					// si un passager veux descendre à l'étage actuel
					if (el.getPassengers().containsValue(el.getPosition())) {
						el.exit();
						el.enter();
					}
					//si l'ascenceur à été choisi par le dispatcheur pour l'étage
					else if (el.getReachableFloors().get(el.getPosition()) == 1) {
						el.enter();
					}

					elevatorStopper(el);

				}
			}

		}

	}

	private static void elevatorStopper(Elevator el) {
		//Si personne n'est dans l'ascenceur et que le dispatcheur n'a donné aucun étage à deservir
		if (!el.getReachableFloors().containsValue(1) && el.getPassengers().isEmpty()) {
			el.setDirection("none");
		}
	}

	private static boolean SystemEmpty() {
		//Si au moin une personne attends à un étage
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
