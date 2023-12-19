public class Note {
	private int noteIndix;
	private double temperature;
	private int severity;
	private int BloodPressure;
	private int Doctorid;
	private int Nurseid;
	private String Junior = ""; // help field, to know the type doctor
	private Patient patient; //aggregation
	private int Treatment;
	private Prescription pre; //aggregation


	public Note(Patient p) {
		patient = p;
		Doctorid = 0;
		severity = 0;
	}

	public void setJunior(String junior) { // setter method
		this.Junior = this.Junior + junior;
	}

	public Prescription getPre() { // getter method
		return pre;
	}

	public void setPer(Prescription pre) { // setter method
		this.pre = pre;
	}

	public String getJunior() { // getter method
		return Junior;
	}


	public Patient getPatient() { // getter method
		return patient;
	}

	public double getTemperature() { // getter method
		return temperature;
	}

	public int getSeverity() { // getter method
		return severity;
	}


	public int getTreatment() { // getter method
		return Treatment;
	}


	public void setTemperature(double temperature) { // setter method
		this.temperature = temperature;
	}

	public void setSeverity(int severity) { // setter method
		this.severity = severity;
	}

	public void setBloodPressure(int bloodPressure) { // setter method
		BloodPressure = bloodPressure;
	}

	public void setDoctorid(int doctorid) { // setter method
		Doctorid = doctorid;
	}

	public void setNurseid(int nurseid) { // setter method
		Nurseid = nurseid;
	}


	public void setTreatment(int treatment) { // setter method
		Treatment = treatment;
	}

}
