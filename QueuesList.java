public class QueuesList {
	private HospitalQueue<Note> NurseQ; //aggregation
	private HospitalQueue<Note> JuniorQ; //aggregation
	private HospitalQueue<Note> SeniorQ; //aggregation
	private HospitalQueue<Note> pharmacyQ; //aggregation
	final int juniorQsize = 8; // the max number of patients in junior's doctor queue is 8
	private Manager m; // aggregation


	public QueuesList(Manager m) {
		this.m = m;
		NurseQ = new UnboundedQueue<>();// Unbounded Queue for Nurses
		JuniorQ = new BoundedQueue<>(juniorQsize); //Bounded Queue for Junior Doctor
		SeniorQ = new UnboundedPriorityQueue(); // Priority  Unbounded Queue for Senior Doctor
		pharmacyQ = new UnboundedQueue<>();     // Unbounded Queue for Pharmacist

	}

	public void InsertToNurseQ(Note n) { // insert to Unbounded Queue for Nurses
		NurseQ.insert(n);
	}

	public HospitalQueue<Note> getPharmacyQ() { // getter method
		return pharmacyQ;
	}

	public Manager getM() { // getter method
		return m;
	}


	public HospitalQueue<Note> getNurseQ() { // getter method
		return NurseQ;
	}


	public HospitalQueue<Note> getJuniorQ() { // getter method
		return JuniorQ;
	}


	public HospitalQueue<Note> getSeniorQ() { // getter method
		return SeniorQ;
	}

}
