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
 *Classe contenant tous les Elevators et toutes les Demandes. Son role est de dispatcher les demandes dans les Elevators de maniere optimisee.
 * @author david_Ekchajzer, Mathieu_Ridet 
 */
public class Dispatcher {

	private static Map<String, List<Elevator>> listElevator = new HashMap<>();
	private static Set<Demand> demands = new LinkedHashSet<Demand>();
	
	/**
	 * Parcourt la liste des demandes non traitees et les affecte une a une a un elevator en suivant l'ordre de priorite suivant : 
	 * </br>1 - un elevator qui n'a pas de demandes en cours
	 * </br>2 - l'elevator le plus proche allant dans la meme direction que la demande
	 * </br> Si aucun elevator ne convient, remet la demande dans les demandes a traiter à la prochaine iteration.
	 *@throws NoSuchDirection
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
	 * @param d la Demande a traiter
	 * @return l'Elevator le plus proche de la demande ayant la meme direction que la demande
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
	 * Ajoute la Demande passee en parametre a la liste des demands a traiter par l'Elevator en parametre
	 * @param choosen l'Elevator qui a ete choisi
	 * @param d la Demande qu'on souhaite lui affecter
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
	 * Appelee dans le cas ou l'Elevator doit atteindre une demande a un etage puis change sa direction quand l'etage est atteint
	 * @param choosen
	 * @param d
	 * @return Le nombre d'iteration (d'etages a parcourir) avant d'atteindre une demande
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
	 * @param elevatorColor
	 * @return le premier Elevator a l'arret (d'une couleur donnee) trouve
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
