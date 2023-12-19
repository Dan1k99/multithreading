import java.io.*;
import java.util.Scanner;
import java.util.Vector;


public class Data {
	private String Filepath;
	private Vector<Patient> patients; // help field, the list of the patients

	public Data(String FilePath) {
		this.Filepath=FilePath;
		patients = new Vector<>(); 
		fileReader(FilePath);
	}


	public Vector<Patient> getPatients() {
		return patients;
	}
	// reading from a file
	public void fileReader(String data) { // help method, to read from a patients.txt file

		try {
			int i = 0; // help variable
			File f = new File(data);
			Scanner scan = new Scanner(f);
			while (scan.hasNextLine()) {
				if (i == 0) { // skip first line(the titles)
					i++;
					String line = scan.nextLine();
					continue;
				}

				String line = scan.nextLine();
				String[] splitted = line.split("\t");
				Patient p1 = new Patient(splitted[0], splitted[1], Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]),
						Integer.parseInt(splitted[5]), Integer.parseInt(splitted[6]));
				patients.add(p1);
			}
		} catch (FileNotFoundException exception) {
			System.out.println("The file was not found.");
		}
	}
}
