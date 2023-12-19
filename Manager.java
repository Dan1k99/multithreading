import java.sql.SQLException;
import java.util.ArrayList;

public class Manager implements Runnable {
	private ArrayList<Note> NotesList; // getting the note from the doctors
	private ArrayList<Prescription> PreList; // getting the prescription from the pharmacist
	private Database db;



	public Manager(Database db) {
		this.NotesList = new ArrayList<Note>();
		this.PreList = new ArrayList<Prescription>();
		this.db=db;
	}

	public synchronized void addNote(Note n){ // add the note to the sql
		NotesList.add(n);
		try {
			db.insertIntoTable("Patients",n);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DebugPrinter.PrintDebug(">>>>>>>>>> The Manager has sent the note to SQL <<<<<<");

	}
	public synchronized void addePre(Prescription p){ // add the prescription to the sql
		PreList.add(p);
	}

	@Override
	public void run() {
		while (!Termination.isTerminate()){

		}
	}

	public ArrayList<Note> getNotesList() { // getter method
		return NotesList;
	}

	public ArrayList<Prescription> getPreList() { // getter method
		return PreList;
	}




}
