public class Patient {
	private String first_name;
	private String last_name;
	private int age;
	private int height;
	private int weight;
	private int id;
	public int arrival_time;

	public Patient(String first_name, String last_name, int age, int height, int weight, int id, int arrival_time) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.id = id;
		this.arrival_time = arrival_time;
	}

	public int getId() { // getter method
		return id;
	}

	public String getFirst_name() { // getter method
		return first_name;
	}


	public String getLast_name() { // getter method
		return last_name;
	}


	public int getAge() { // getter method
		return age;
	}


	public int getHeight() { // getter method
		return height;
	}

	public int getWeight() { // getter method
		return weight;
	}


	public int getArrival_time() { // getter method
		return arrival_time;
	}


}
