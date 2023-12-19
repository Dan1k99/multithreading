package pack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;

public class GUI extends JFrame implements ActionListener {


	JLabel header;
	JLabel NursesTL;
	JLabel CloseTL;
	JButton start;
	JButton exit;

	TextField NursesT;
	TextField CloseT;


	public GUI() {
		header = new JLabel("Welcome to hospital!");
		CloseTL = new JLabel("Closing time");
		NursesTL = new JLabel("Nurses Number");
		start = new JButton();
		exit = new JButton();
		NursesT = new TextField("0"); // the default number of nurses is 0
		CloseT = new TextField("8"); // the default time of Closing Emergency Medical Center is 8




		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(720, 480);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(42, 42, 45));



		header.setForeground(new Color(255, 255, 255));
		header.setFont(new Font("Comic Sans", Font.BOLD, 18));
		header.setHorizontalTextPosition(JLabel.CENTER);
		header.setVerticalTextPosition(JLabel.BOTTOM);
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setVerticalAlignment(JLabel.TOP);
		header.setBounds(260, 5, 250, 150);

		NursesT.setBounds(310, 200, 100, 20);
		NursesT.setForeground(new Color(225, 225, 225));
		NursesT.setBackground(new Color(65, 65, 68));
		NursesTL.setFont(new Font("Comic Sans", Font.BOLD, 11));
		NursesTL.setForeground(new Color(225, 225, 225));
		NursesTL.setBounds(310, 180, 100, 20);

		CloseT.setBounds(310, 270, 100, 20);
		CloseT.setForeground(new Color(225, 225, 225));
		CloseT.setBackground(new Color(65, 65, 68));
		CloseTL.setFont(new Font("Comic Sans", Font.BOLD, 11));
		CloseTL.setForeground(new Color(225, 225, 225));
		CloseTL.setBounds(310, 250, 100, 20);

		start.setBounds(540, 340, 100, 50);
		start.addActionListener(e -> actionPerformed(e));
		start.setText("Start");
		start.setFocusable(false);
		start.setForeground(new Color(225, 225, 225));
		start.setBackground(new Color(65, 65, 68));

		exit.setBounds(80, 340, 100, 50);
		exit.addActionListener(e -> System.exit(0));
		exit.setText("exit");
		exit.setFocusable(false);
		exit.setForeground(new Color(225, 225, 225));
		exit.setBackground(new Color(65, 65, 68));


		this.add(NursesT);
		this.add(NursesTL);
		this.add(CloseTL);
		this.add(CloseT);
		this.add(start);
		this.add(exit);
		this.add(header);
		this.setLayout(null);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) { 
		double check1;
		int check2 = 0;
		boolean input = false; // help variable
		boolean NotInt = false; // help variable
		if (actionEvent.getSource() == start) {
			while (!input) {
				check1 = Double.parseDouble(CloseT.getText());
				for (int i = 0; i < NursesT.getText().length(); i++) {
					if (NursesT.getText().charAt(i) == '.') { // double
						NotInt = true;
					}
				}
				if (!NotInt) { // integer
					check2 = Integer.parseInt(NursesT.getText());
				}
				if ((check1 > 0 && check1 < 24) && (1 <= check2 && check2 <=4) ) { // valid input
					startapp(check1, check2); 
					input = true;
				} else { // invalid input
					JOptionPane.showMessageDialog(null, "Please make sure to enter check time between 0-5 (double) and 1 manager at least (int)", "Error!", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}

		} else if (actionEvent.getSource() == exit) {
			System.exit(0);
		}
	}



	public static void startapp(double CloseTime, int NursesNumber) { //starts the program
		Database db = new Database();
		db.createTables("Patients");
		QueuesList a = new QueuesList(new Manager(db));

		Data d = new Data("src/patients.txt");


		// create threads
		Vector<Thread> threads = new Vector<Thread>();

		// Patients
		for (Patient p : d.getPatients()) {
			if(p.getArrival_time() < CloseTime){
				Thread t = new Thread(new PatientThread(p, a));
				threads.add(t);
			}
		}

		// get from GUI nurses number
		int totalNurses = NursesNumber;
		for (int i = 0; i < totalNurses; i++) { // Create nurse Thread instances
			Thread t = new Thread(new Nurse(i + 1, a));  // Define it as Thread
			threads.add(t); // add to threads vector
		}

		// Junior doctor
		final int totalJuniors = 3;
		for (int i = 0; i < totalJuniors; i++) { // Create Junior doctor Thread instances
			Thread t = new Thread(new JuniorDoctor(i + 1, a));  // Define it as Thread
			threads.add(t); // add to threads vector
		}

		final int totalSeniors = 1;
		for (int i = 0; i < totalSeniors; i++) { // Create senior doctor Thread instances
			Thread t = new Thread(new SeniorDoctor(i + 1, a));  // Define it as Thread
			threads.add(t); // add to threads vector
		}
		final int totalpharmacists = 2;
		for (int i = 0; i < totalpharmacists; i++) { //// Create pharmacist Thread instances
			Thread t = new Thread(new Pharmacist(i + 1, a));  // Define it as Thread
			threads.add(t); // add to threads vector
		}
		final int totalMangers = 1;
		for (int i = 0; i < totalMangers; i++) { // Create manager Thread instances
			Thread t = new Thread(a.getM());  // Define it as Thread
			threads.add(t); // add to threads vector
		}
		// Start all the threads
		for (Thread t : threads) {
			t.start();
		}

		try {

			Thread.sleep((long) (CloseTime) * 1000); // simulate end of day
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Termination.setTerminate(true); // simulate end of day

		DebugPrinter.PrintDebug("*****Time is up the remaining threads will finish their existing job and exist*****");
	}
}

