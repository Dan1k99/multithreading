import java.util.concurrent.ThreadLocalRandom;

public class Nurse extends Workers {
	private QueuesList q; //aggregation


	public Nurse(int id,QueuesList queuesList) {
		super(id);
		q =queuesList;
	}

	@Override
	public void run() {
		while (!Termination.isTerminate()) {
			Note CurrNote = q.getNurseQ().extract(); // extract from Unbounded  Queue for Nurses
			CurrNote.setNurseid(getId());
			DebugPrinter.PrintDebug("The Nurse" + " " + this.getId() + " Retrieved " + CurrNote.getPatient().getFirst_name() + " " + CurrNote.getPatient().getLast_name() + " for diagonsis");
			int Sev = 0; // help variable, severity
			int r1 = (int) (Math.random() * 100);
			if (r1 < 80) { // 0-0.8
				CurrNote.setBloodPressure(ThreadLocalRandom.current().nextInt(90, 141));
				Sev = 4;
			} else if (r1 < 90) { // 0.8-0.9
				CurrNote.setBloodPressure(ThreadLocalRandom.current().nextInt(0, 90));
				Sev = 2;
			} else { // 0.9<
				CurrNote.setBloodPressure(ThreadLocalRandom.current().nextInt(140, 201));
				Sev = 2;
			}

			int r2 = (int) (Math.random() * 100);
			if (r2 < 70) { // 0-0.7
				CurrNote.setTemperature(ThreadLocalRandom.current().nextDouble(38, 40));
			} else if (r2 < 90) { // 0.7-0.9
				CurrNote.setTemperature(ThreadLocalRandom.current().nextInt(39, 41));
				Sev += 3;
			} else { // 0.9<
				CurrNote.setTemperature(ThreadLocalRandom.current().nextInt(0, 39));
				Sev += 3;
			}

			Sev += (CurrNote.getPatient().getWeight() / CurrNote.getPatient().getHeight()) * 4; // the severity
			if (Sev > 10) Sev = 10;
			CurrNote.setSeverity(Sev);

			if (Sev >= 6) { // send to the senior doctor
				try {
					Thread.sleep(ThreadLocalRandom.current().nextInt(1, 4));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				q.getSeniorQ().insert(CurrNote); // insert to Priority Unbounded Queue for Senior Doctor
				DebugPrinter.PrintDebug("The Nurse" + " " + this.getId() + " sent " +CurrNote.getPatient().getFirst_name() + " " + CurrNote.getPatient().getLast_name() + " to " + "Senior Queue");

			} else { // send to the junior doctor
				int r3 = (int) (Math.random() * 100);
				if (r3 > 90) { // 10%, error registering for note
					CurrNote.setSeverity(-1);
				} else { // correct registration
					try {
						Thread.sleep(ThreadLocalRandom.current().nextInt(1, 4));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					q.getJuniorQ().insert(CurrNote); // insert to Bounded Queue for Junior Doctor
					DebugPrinter.PrintDebug("The Nurse" + " " + this.getId() + " sent " +CurrNote.getPatient().getFirst_name() + " " + CurrNote.getPatient().getLast_name() + " to " + "Junior Queue");


				}
			}


		}
	}
}
