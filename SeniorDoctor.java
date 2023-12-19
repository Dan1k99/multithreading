public class SeniorDoctor extends Workers {
	private HospitalQueue<Note> MyQ; //aggregation
	private QueuesList queuesList; //aggregation


	public SeniorDoctor(int id, QueuesList q) {
		super(id);
		queuesList = q;
		MyQ = q.getSeniorQ();
	}


	@Override
	public void run() {
		while(!Termination.isTerminate()) {
			Note CurrNote = MyQ.extract(); // extract from Priority Unbounded Queue for Senior Doctor
			DebugPrinter.PrintDebug("The Senior Doctor" + " " + this.getId() + " Retrieved " + CurrNote.getPatient().getFirst_name() + " " + CurrNote.getPatient().getLast_name() + " for treatment");
			DoANote(CurrNote,queuesList,6,"Senior");
		}
	}
}
