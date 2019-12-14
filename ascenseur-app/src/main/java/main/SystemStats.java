package main;

import elevator.Dispatcher;
import elevator.Elevator;

/**
 * Realise les statisques quant aux performances de notre application.
 * @author david_Ekchajzer, Mathieu_Ridet
 */
public class SystemStats {
	
	private SystemStats() {}

	private static int nbSequenceIterations;
	private static int totalUserReachDestination;
	private static long timeStart = 0;
	private static long timeEnd = 0;
	private static float totalWeight;
	private static float totalSurface;
	private static float totalAge;
	private static float nbPMR;
	private static float nbStudent;
	private static float nbTeacher;
	private static float nbAdmin;

	public static String getStats() {
		float totalUsers = nbStudent + nbTeacher + nbAdmin;
		StringBuilder sb = new StringBuilder();

		sb.append("         INFORMATIONS USERS\n************************************");
		sb.append("\nTOTAL USERS : " + totalUsers);
		sb.append("\nNumber of PMR  : " + nbPMR);
		sb.append("\nProportion of PMR  : " + nbPMR / totalUsers * 100 + "%");
		sb.append("\nNumber of Students  : " + nbStudent);
		sb.append("\nProportion of Students  : " + nbStudent / totalUsers * 100 + "%");
		sb.append("\nNumber of Teacher  : " + nbTeacher);
		sb.append("\nProportion of Teacher  : " + nbTeacher / totalUsers * 100 + "%");
		sb.append("\nNumber of Administrative  : " + nbTeacher);
		sb.append("\nProportion of Administrative  : " + nbTeacher / totalUsers * 100 + "%");
		sb.append("\nMedium Weight  : " + totalWeight / totalUsers);
		sb.append("\nMedium Surface  : " + totalSurface / totalUsers);
		sb.append("\nMedium Age  : " + totalAge / totalUsers);

		sb.append("\n\n         INFORMATIONS SYSTEM\n************************************");
		sb.append("\nSystem Duration  : " + (timeEnd - timeStart) + "ms");
		sb.append("\nSystem Iterations  : " + nbSequenceIterations);
		sb.append("\nSystem Sucess  : " + (totalUserReachDestination) + " users");
		sb.append("\nSystem failures  : " + (int) (totalUsers - totalUserReachDestination) + " users");

		sb.append("\n\n         INFORMATIONS ELEVATORS\n************************************");
		Elevator mostDemanded = null;
		String bestColor = "Aucune";
		int nbDemandsPerColors;
		int nbDemandsPerColorsMax = 0;
		for (String color : Dispatcher.getListElevator().keySet()) {
			nbDemandsPerColors = 0;
			sb.append("\n              " + color + "\n        -----------------");
			for (Elevator el : Dispatcher.getListElevator().get(color)) {
				nbDemandsPerColors++;
				sb.append(
						"\n" + el.getElevatorNumber() + " - Number of demands treated  : " + el.getNbDemandsTreated());
				if (mostDemanded == null || el.getNbDemandsTreated() > mostDemanded.getNbDemandsTreated()) {
					mostDemanded = el;
				}
				
			}
			if(nbDemandsPerColorsMax < nbDemandsPerColors) {
				nbDemandsPerColorsMax = nbDemandsPerColors;
				bestColor = color;
			}
			sb.append("\n\n   Most demanded : Elevator n°" + mostDemanded.getElevatorNumber());
			
		}
		sb.append("\n\n   Most demanded color : " + bestColor);

		return sb.toString();

	}

	public static void addPMR() {
		nbPMR++;
	}

	public static void addAmdin() {
		nbAdmin++;
	}

	public static void addStudent() {
		nbStudent++;
	}

	public static void addTeacher() {
		nbTeacher++;
	}

	public static void addAge(float a) {
		totalAge += a;

	}

	public static void addWeight(float w) {
		totalWeight += w;
	}

	public static void addSurface(float s) {
		totalSurface += s;
		
	}
	public static void addSequenceIteration() {
		nbSequenceIterations++;
	}

	public static void setTimeStart() {
		timeStart = System.currentTimeMillis();

	}

	public static void setTimeEnd() {
		timeEnd = System.currentTimeMillis();
	}

	public static void addUserReachDestination() {
		totalUserReachDestination++;
	}

}
