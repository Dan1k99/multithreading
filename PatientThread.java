public class PatientThread implements Runnable{
	private Patient paiet; //aggregation
	private QueuesList queuesList; //aggregation

	public PatientThread(Patient paiet, QueuesList queuesList) {
		this.paiet = paiet;
		this.queuesList = queuesList;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(paiet.arrival_time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // sleep until its time to arrive to the emergency medical center

		queuesList.InsertToNurseQ(new Note(paiet)); // insert to Unbounded Queue for Nurses
		DebugPrinter.PrintDebug(paiet.getFirst_name() + " " +  paiet.getLast_name() + " Arrived at " + paiet.getArrival_time());
	}
}
