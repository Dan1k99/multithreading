import java.util.concurrent.ThreadLocalRandom;

abstract public class Workers implements Runnable {
	private int id;


	public void DoANote(Note CurrNote, QueuesList queuesList, int seconds,String doctorType) { // the treatment process for the senior and junior doctors
		if (CurrNote.getSeverity() == -1) { //error registering for note
			queuesList.InsertToNurseQ(new Note(CurrNote.getPatient())); // insert to Unbounded Queue for Nurses to re-measurement
			return;
		}
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CurrNote.setDoctorid(getId()); // the doctor id in the note of the patient
		CurrNote.setJunior(doctorType); // the doctor type in the note of the patient
		CurrNote.setTreatment(ThreadLocalRandom.current().nextInt(1, 6) + CurrNote.getSeverity()); // the treatment for the patient



		int r1 = (int) (Math.random() * 100);
		if (r1 < 50) { // 50%, if the patient required to prescription or not
			Prescription pre = new Prescription(CurrNote.getTreatment() + ThreadLocalRandom.current().nextInt(1, 6));
			CurrNote.setPer(pre);
			queuesList.getPharmacyQ().insert(CurrNote); // insert to Unbounded Queue for Pharmacist
			DebugPrinter.PrintDebug("The doctor" + " " + this.getId() + " has sent the patient" +CurrNote.getPatient().getFirst_name() + " " + CurrNote.getPatient().getLast_name() + " to " + " the pharmacy");

		} // else the patient is already extracted from the queue which means he is already sent home.
		queuesList.getM().addNote(CurrNote); // Send to manger list the Note
		DebugPrinter.PrintDebug("The doctor" + " " + this.getId() + " has sent the patient" +CurrNote.getPatient().getFirst_name() + " " + CurrNote.getPatient().getLast_name() + " to " + " the Manager");

	}
	public Workers(int id) { // setter method
		this.id = id;
	}

	public int getId() { // getter method
		return id;
	}
}
