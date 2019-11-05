public class User implements Comparable<User>{

	private String firstName;
	private String lastName;
	private int age;
	private int weight;
	private String status;
	private Boolean PMR;
	private int priority;
	private Floor source;
	private Floor destination;

	public User(String firstName, int age, int weight, String status, Boolean PMR, Floor source, Floor destination,
			String lastName) {
		this.firstName = firstName;
		this.age = age;
		this.weight = weight;
		this.status = status;
		this.PMR = PMR;
		this.source = source;
		this.destination = destination;
		this.lastName = lastName;
		this.setPriority();
	}

	private void callElevator(Elevator el, String direction) {
		
	}
	
	public void getIn() {
		//demanderEtage
	}
	
	public void getOut() {
		
	}
	
	
	
	public int getAge() {
		return age;
	}

	public int getWeight() {
		return weight;
	}

	public void setPriority() {
		this.priority = 0;
		switch (this.status) {

		case "professor":
			this.priority += 3;
		case "admin":
			this.priority += 2;
		case "student":
			this.priority += 1;
		}

		if (this.PMR) {
			this.priority += 3;
		}
	}
	
	public int getPriority() {
		return this.priority;
	}

	public Floor getSource() {
		return source;
	}

	public void setSource(Floor source) {
		this.source = source;
	}

	public Floor getDestination() {
		return destination;
	}

	public void setDestination(Floor destination) {
		this.destination = destination;
	}

	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
