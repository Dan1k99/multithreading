public class Termination { // this class help to know if the day is ended or not
	private static boolean terminate = false; // the day isn't ended

	public static boolean isTerminate() { // getter method
		return terminate;
	}

	public static void setTerminate(boolean terminate) { // setter method
		Termination.terminate = terminate;
	}
}
