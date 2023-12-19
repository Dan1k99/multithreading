public class JuniorDoctor extends Workers {
	private HospitalQueue<Note> MyQ; //aggregation
	private QueuesList queuesList; //aggregation

	public JuniorDoctor(int id, QueuesList q) {
		super(id);
		queuesList = q;
		MyQ = q.getJuniorQ();
	}

	@Override
	public void run() {
		while (!Termination.isTerminate()) {
			Note CurrNote = MyQ.extract(); // extract from Bounded Queue for Junior Doctor
			DebugPrinter.PrintDebug("The jonior Doctor" + " " + this.getId() + " Retrieved " + CurrNote.getPatient().getFirst_name() + " " + CurrNote.getPatient().getLast_name() + " for treatment");
			DoANote(CurrNote, queuesList, CurrNote.getSeverity(),"Junior");
		}
	}


}
