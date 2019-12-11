package elevator;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.NoSuchDirection;
import floor.Floor;
import user.Demand;
/**
 *Classe contenant tous les Elevator et toutes les Demand. Sont rôle est de dispatcher les demands dans les Elevator de manière optimisée
 * @author david_Ekchajzer, Mathieu_Ridet 
 */
public class Dispatcher {

	private static Map<String, List<Elevator>> listElevator = new HashMap<>();
	private static Set<Demand> demands = new LinkedHashSet<Demand>();
	
	/**
	 * Parcours la liste des demandes non traitées et les affectes une à une à un elevator en suivant l'ordre de priorité suivant : 
	 * </br>1 - un elevator qui n'a pas de demandes en cours
	 * </br>2 - l'elevator le plus proche allant dans la direction de la demande
	 * </br> Si aucun elevator ne conviens remet la demande dans les demandes à traité à la prochaine itération
	 *@throws NoSuchDirection
	 *
	 **/
	public static void dispatch() throws NoSuchDirection {
		Elevator choosen;
		Set<Demand> NotTreatedDemands = new LinkedHashSet<Demand>();
		for(Demand d : demands) {
			choosen = grabNullElevator(d.getFloor().getColor());
			if(choosen != null) {
				addDemandOnChoosen(choosen, d);
			} else {
				choosen = chooseNearestElevator(d);
				if(choosen == null) {
					NotTreatedDemands.add(d);
				} else {
					choosen.getReachableFloors().put(d.getFloor(), 1);
				}
			}
				
		}
		demands = NotTreatedDemands;
	} 

	/**
	 * 
	 * @param d la Demand à traiter
	 * @return l'Elevator le plus proche de la demande ayant la même direction que la demande
	 */
	private static Elevator chooseNearestElevator(Demand d) {
		int distBetweenChoosenAndD = 1000;
		int distBetweenElAndD;
		Elevator choosen = null;
		
		for(Elevator el : listElevator.get(d.getFloor().getColor())) {
			if(choosen != null) 
				distBetweenChoosenAndD = Math.abs(choosen.getPosition().getFloorNumber()-d.getFloor().getFloorNumber());
			
			distBetweenElAndD = Math.abs(el.getPosition().getFloorNumber()-d.getFloor().getFloorNumber());
			 
			if(el.getDirection().equals(d.getDirection())
					&&
					el.getNbfloors() == 0 &&
					(((el.getDirection().equals("up"))
							&& el.getPosition().getFloorNumber() <= d.getFloor().getFloorNumber()
							&& distBetweenElAndD<distBetweenChoosenAndD)
					||
					((el.getDirection().equals("down"))
							&& el.getPosition().getFloorNumber() >= d.getFloor().getFloorNumber()
							&& distBetweenElAndD<distBetweenChoosenAndD))) {
				choosen = el;
			}		
		}
		return choosen;
	}

	/**
	 * Ajoute la Demand passé en paramètre à la liste des demande à traité par l'Elevator en paramètre
	 * @param choosen Elevator qui à été choisi
	 * @param d la Demand qu'on souhaite lui affécter
	 */
	private static void addDemandOnChoosen(Elevator choosen, Demand d) {
		choosen.addNbDemandsTreated();
		choosen.getReachableFloors().put(d.getFloor(), 1);

		if(d.getDirection() == "up") {
			if(choosen.getPosition().getFloorNumber() > d.getFloor().getFloorNumber()) {
				choosen.setDirection("down");
				choosen.setNbfloors(getNbFloorToReachDemand(choosen, d));
			}else if(choosen.getPosition().getFloorNumber() <= d.getFloor().getFloorNumber()){
				choosen.setDirection("up");
			}
		}
		
		else if(d.getDirection() == "down") {
			if(choosen.getPosition().getFloorNumber() < d.getFloor().getFloorNumber()) {
				choosen.setDirection("up");
				choosen.setNbfloors(getNbFloorToReachDemand(choosen, d));

			}else if(choosen.getPosition().getFloorNumber() >= d.getFloor().getFloorNumber()){
				choosen.setDirection("down");
			}
		}
	}
		
	/**
	 * Appelé dans le cas ou l'Elevator doit atteindre une demande à un etage puis change sa direction quand la demande est atteinte
	 * @param choosen
	 * @param d
	 * @return Le nombre d'itération avant d'atteindre une demande
	 */
	private static int getNbFloorToReachDemand(Elevator choosen, Demand d) {
		int cmpt = 0;
		Floor f = choosen.getPosition();
		while(!f.equals(d.getFloor())){
			if(d.getFloor().getFloorNumber() < choosen.getPosition().getFloorNumber()) {
				f = f.getPreviousFloor();
			}else if(d.getFloor().getFloorNumber() > choosen.getPosition().getFloorNumber()) {
				f = f.getNextFloor();
			}
			cmpt++;
		}
		
		return(cmpt);
	}

	/**
	 * 
	 * @param elevatorColor
	 * @return le premier Elevator d'une couleur à l'arret trouvé
	 */
	private static Elevator grabNullElevator(String elevatorColor) {
		for(Elevator el : listElevator.get(elevatorColor)) {
			if (el.getDirection() == null) {
				return el;
			}

		}
		return null;
	}

	public static Map<String, List<Elevator>> getListElevator() {
		return listElevator;
	}

	public static void addDemand(Demand d) {
		demands.add(d);
	}
	
	public static Set<Demand> getDemands() {
		return demands;
	}

}
