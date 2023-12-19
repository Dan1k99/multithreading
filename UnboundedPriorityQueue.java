import java.util.Comparator;
import java.util.Vector;

public class UnboundedPriorityQueue implements HospitalQueue<Note> {
	private MyComperator comperator = new MyComperator();
	private Vector<Note> buffer = new Vector<>();

	public synchronized void insert(Note n) { // insert to tail of queue
		int i = 0; // help variable
		boolean done = false; // help variable
		while ( i < buffer.size() && !done) { // arrange according to patient severity
			if (comperator.compare(n.getSeverity(),buffer.elementAt(i).getSeverity()) <= 0) 
				done = true;
			else
				i++;
		}
		buffer.add(i,n);
		this.notifyAll();
	}

	public synchronized Note extract() { // return element from head of queue
		while (buffer.isEmpty()) // Queue is empty
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		Note t = buffer.elementAt(0);
		buffer.remove(t);
		return t;
	}

	public static class MyComperator implements Comparator<Integer> { // comparator to arrange
		@Override
		public int compare(Integer i, Integer j) {
			return i - j;
		}
	}
}
