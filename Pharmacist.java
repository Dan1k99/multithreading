import java.util.concurrent.ThreadLocalRandom;

public class Pharmacist extends Workers{
	private QueuesList queuesList; //aggregation



	public Pharmacist(int id, QueuesList q) {
		super(id);
		this.queuesList = q;
	}


	@Override
	public void run() {
		while(!Termination.isTerminate()){
			Prescription pre;
			pre =  queuesList.getPharmacyQ().extract().getPre(); // extract from Unbounded Queue for Pharmacist
			pre.setRecieved(true); // update that the patient took the prescription
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(2, 5));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			queuesList.getM().addePre(pre);; //Send for manager list the prescription
			DebugPrinter.PrintDebug("The pharmacists" + " " + this.getId() + " has sent the preception " +pre.getMedicine()  + " to " + " the manager");


		}
	}
}
