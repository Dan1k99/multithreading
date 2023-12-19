public class Prescription {
	private int Medicine; // help field, number of medicine
	private boolean recieved; // help field, to know if the pharmacist give the patient the prescription


	public Prescription(int medicine) {
		Medicine = medicine;
		this.recieved=false;
	}

	public int getMedicine() { // getter method
		return Medicine;
	}
	public void setRecieved(boolean recieved) { // setter method
		this.recieved = recieved;
	}


}
